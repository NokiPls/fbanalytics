/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.quickstart;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.graphstream.graph.implementations.SingleGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Friend;
import services.CommonFriendsList;
import services.CreateJson;
import services.FriendsList;
import services.FriendsServiceInterface;
import services.Graph;
import services.UserInit;

/**
 * Simple little @Controller that invokes Facebook and renders the result.
 * The injected {@link Facebook} reference is configured with the required authorization credentials for the current user behind the scenes.
 * @author Keith Donald
 */
@Controller
public class HomeController {

	private final Facebook facebook;
	private FriendsList friends;
	public List<Friend> commonFriendsList = new ArrayList<Friend>();
	public SingleGraph graphF = new SingleGraph("graph");
	private CommonFriendsList common;
	private Graph graph;
	private CreateJson json;
	private Friend user;
	private UserInit userInit;
	private int alreadyPersisted = 0;
	String myId = "";

	@Autowired
	public FriendsServiceInterface fs;
	
	@Inject
	public HomeController(Facebook facebook, FriendsList friends,
			CommonFriendsList common, Graph graph, CreateJson json,
			UserInit userInit) {
		this.facebook = facebook;
		this.friends = friends;
		this.common = common;
		this.graph = graph;
		this.json = json;
		this.userInit = userInit;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		UserInit.loginNumber ++;
		model.addAttribute(facebook.userOperations().getUserProfile());
		return "home";
	}
	
	@RequestMapping(value = "/List", method = RequestMethod.GET)
	public String friendsCheckboxes(Model model) {

		if (!facebook.isAuthorized()) {
			alreadyPersisted = 0;
			return "redirect:/connect/facebook";
		}

		// the service create list of friends to display in the checkboxes
		if (alreadyPersisted == 0) {
			user = userInit.initialize(facebook);
			friends.createFbList(facebook, user);
			fs.addUser(user);
			fs.addFriends(friends.getFriends());
			alreadyPersisted = 1;
		}
		model.addAttribute(facebook.userOperations().getUserProfile())
				.addAttribute("names", friends.getListOfName())
				.addAttribute("id", friends.getListOfId());
		return "friendsList";
	}

	@RequestMapping(value = "/checkboxes", method = RequestMethod.POST)
	public String friendsCheckboxesSubmit(
			@RequestParam(value = "id[]", required = false) String[] idSelected,
			Model model) {

		if (idSelected == null)
			return "redirect:/List";
		if (!facebook.isAuthorized()) {
			return "redirect:/connect/facebook";
		}

		UserInit.searchCommonNumber++;

		// service crea Lista di amici selezionati e per ognuno di essi la lista
		// degli amici in comune
		common.createCommonList(facebook, idSelected, user);
		commonFriendsList = common.getCommonFriends();
		// creo grafo per statistica
		myId = facebook.userOperations().getUserProfile().getId();
		graph.makeGraph(commonFriendsList, myId);
		graphF = graph.calcMetrics(commonFriendsList, user);

		fs.addFriends(commonFriendsList);

		model.addAttribute(facebook.userOperations().getUserProfile());
		model.addAttribute("name", facebook.userOperations().getUserProfile()
				.getName());
		model.addAttribute("friends", commonFriendsList);
		return "hierarchicalList";
	}

	@RequestMapping(value = "/checkboxes", method = RequestMethod.GET)
	public String friendsCheckboxesSubmit(Model model) {

		if (!facebook.isAuthorized()) {
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

	@RequestMapping(value = "/GraphNode", method = RequestMethod.GET)
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
