package org.springframework.social.quickstart;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.graphstream.graph.implementations.SingleGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import services.CommonFriendsListInterface;
import services.CreateJsonInterface;
import services.FriendsListInterface;
import services.FriendsServiceInterface;
import services.GraphInterface;
import services.UserInitInterface;
import domain.Friend;

@Controller
@ComponentScan("services")
public class HomeController {

	private final Facebook facebook;
	private FriendsListInterface friends;
	public List<Friend> commonFriendsList = new ArrayList<Friend>();
	public SingleGraph graphF = new SingleGraph("graph");
	private CommonFriendsListInterface common;
	private GraphInterface graph;
	private Friend user;
	private CreateJsonInterface json;
	private UserInitInterface userInit;
	private FriendsServiceInterface fs;

	@Autowired
	public HomeController(Facebook facebook, FriendsListInterface friends,
			CommonFriendsListInterface common, GraphInterface graph,
			CreateJsonInterface json, UserInitInterface userInit,
			FriendsServiceInterface fs, HttpSession session) {
		this.facebook = facebook;
		this.friends = friends;
		this.common = common;
		this.graph = graph;
		this.json = json;
		this.userInit = userInit;
		this.fs = fs;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute(facebook.userOperations().getUserProfile());
		return "home";
	}

	@RequestMapping(value = "/friendsList", method = RequestMethod.GET)
	public String friendsCheckboxes(Model model) {

		if (!facebook.isAuthorized()) {
			return "redirect:/connect/facebook";
		}

		// Initialize the user as a "friend" object
		user = userInit.initialize(facebook);

		// Create the list of direct friends
		friends.createFbList(facebook, user);

		// Persist
		fs.addFriends(friends.getFriends());

		model.addAttribute(facebook.userOperations().getUserProfile())
				.addAttribute("names", friends.getListOfName())
				.addAttribute("id", friends.getListOfId());
		return "friendsList";
	}

	@RequestMapping(value = "/checkboxes", method = RequestMethod.POST)
	public String friendsCheckboxesSubmit(
			@RequestParam(value = "id[]", required = false) String[] idSelected,
			Model model) {

		if (!facebook.isAuthorized()) {
			return "redirect:/connect/facebook";
		}
		if (idSelected == null)
			return "redirect:/friendsList";

		// service crea Lista di amici selezionati e per ognuno di essi la lista
		// degli amici in comune
		commonFriendsList = common.createCommonList(facebook, idSelected, user);
		// creo grafo per statistica
		graph.makeGraph(commonFriendsList, facebook.userOperations()
				.getUserProfile().getId());
		graphF = graph.calcMetrics(commonFriendsList, user);

		fs.addFriends(commonFriendsList);

		model.addAttribute(facebook.userOperations().getUserProfile());
		model.addAttribute("name", facebook.userOperations().getUserProfile()
				.getName());
		model.addAttribute("friends", commonFriendsList);
		return "commonFriendsList";
	}

	@RequestMapping(value = "/checkboxes", method = RequestMethod.GET)
	public String friendsCheckboxesSubmit(Model model) {

		if (!facebook.isAuthorized() || commonFriendsList.isEmpty()) {
			return "redirect:/connect/facebook";
		}

		model.addAttribute(facebook.userOperations().getUserProfile().getName());
		model.addAttribute(facebook.userOperations().getUserProfile());
		model.addAttribute("friends", commonFriendsList);
		return "hierarchicalList";
	}

	@RequestMapping(value = "/openGraph", method = RequestMethod.POST)
	public String newGraph(Model model) {

		if (!facebook.isAuthorized()) {
			return "redirect:/connect/facebook";
		}

		json.makeJson(
				commonFriendsList,
				facebook.userOperations().getUserProfile().getName(),
				Long.parseLong(facebook.userOperations().getUserProfile()
						.getId()));

		model.addAttribute("graph", json.getJson());
		model.addAttribute(facebook.userOperations().getUserProfile());
		model.addAttribute("name", facebook.userOperations().getUserProfile()
				.getName());
		model.addAttribute("friends", commonFriendsList);

		return "graphPage";
	}

	// TODO: se uno scrive sta url viene un 500
	@RequestMapping(value = "/graphNode", method = RequestMethod.GET)
	public String GraphNode(Model model, @RequestParam(value = "id") String id) {

		if (!facebook.isAuthorized()) {
			return "redirect:/connect/facebook";
		}

		FacebookProfile profile = facebook.userOperations().getUserProfile(id);

		model.addAttribute("betweenness",
				graphF.getNode(id).getAttribute("betweenness"));
		model.addAttribute("closeness",
				graphF.getNode(id).getAttribute("closeness"));
		model.addAttribute("norm_closeness",
				graphF.getNode(id).getAttribute("norm_closeness"));
		model.addAttribute("degree", graphF.getNode(id).getAttribute("degree"));
		model.addAttribute("norm_degree",
				graphF.getNode(id).getAttribute("norm_degree"));

		model.addAttribute("profile", profile);
		return "nodePopUp";
	}

}
