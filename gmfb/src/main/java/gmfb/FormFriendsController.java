package gmfb;

import bean.Friends;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.graphstream.algorithm.BetweennessCentrality;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormFriendsController {

	private Facebook facebook;
	public ArrayList<Friends> CommonFriendsList = new ArrayList<Friends>();
	public	SingleGraph graphF = new SingleGraph("graph");

	@Inject
	public FormFriendsController(Facebook facebook) {
		this.facebook = facebook;
	}

	@RequestMapping(value = "/List", method = RequestMethod.GET)
	public String friendsCheckboxes(Model model) {

		List<String> id, name;
		if (!facebook.isAuthorized()) {
			return "redirect:/connect/facebook";
		}

		PagedList<FacebookProfile> friends = facebook.friendOperations()
				.getFriendProfiles();
		id = new ArrayList<String>();
		name = new ArrayList<String>();

		for (int i = 0; i < friends.size(); i++) {
			// creo lista di nomi e id da passare alla pagina con le chekboxes
			id.add(friends.get(i).getId());
			name.add(friends.get(i).getName());
		}

		model.addAttribute(facebook.userOperations().getUserProfile());
		model.addAttribute("names", name).addAttribute("id", id);
		return "friendsList";
	}

	@RequestMapping(value = "/checkboxes", method = RequestMethod.POST)
	public String friendsCheckboxesSubmit(
			@RequestParam(value = "id[]", required = false) String[] idSelected,
			Model model) {
		if (idSelected.length == 0)
			return "redirect:/friendsList";
		if (!facebook.isAuthorized()) {
			return "redirect:/connect/facebook";
		}
		CommonFriendsList = new ArrayList<Friends>();
		// creo Lista di amici selezionati e per ognuno di essi la lista degli
		// amici in comune
		for (int i = 0; i < idSelected.length; i++) {
			PagedList<Reference> mutual = facebook.friendOperations()
					.getMutualFriends(idSelected[i]);

			FacebookProfile friend = facebook.userOperations().getUserProfile(
					idSelected[i]);
			CommonFriendsList.add(i,
					new Friends(friend.getId(), friend.getName()));
			for (int k = 0; k < mutual.size(); k++) {
				CommonFriendsList
						.get(i)
						.getCommonFriends()
						.add(new Friends(mutual.get(k).getId(), mutual.get(k)
								.getName()));

			}
		}

		model.addAttribute(facebook.userOperations().getUserProfile());
		model.addAttribute("Friends", CommonFriendsList);
		return "hierarchicallist";
	}

	@RequestMapping(value = "/openGraph", method = RequestMethod.POST)
	public String newGraph(Model model) {
		if (!facebook.isAuthorized()) {
			return "redirect:/connect/facebook";
		}

		CreateJson json = new CreateJson(CommonFriendsList, facebook
				.userOperations().getUserProfile().getName(), facebook
				.userOperations().getUserProfile().getId());
		
		//creo grafo per statistica
		String myId = facebook.userOperations().getUserProfile().getId();
		graphF=  new SingleGraph("graph");
		graphF.addNode(myId);
		for(int i=0; i<CommonFriendsList.size();i++)
		{
			String nodeId=CommonFriendsList.get(i).getId();
			
			if(graphF.getNode(nodeId)==null)
			{
				graphF.addNode(nodeId);
				graphF.addEdge(myId+nodeId,myId ,nodeId );
			}
			for(int k=0; k<CommonFriendsList.get(i).getCommonFriends().size();k++)
			{
				String s_nodeId=CommonFriendsList.get(i).getCommonFriends().get(k).getId();
				
				if(graphF.getNode(s_nodeId)==null)
				{					
					graphF.addNode(s_nodeId);
					graphF.addEdge(nodeId+s_nodeId,nodeId ,s_nodeId);
					graphF.addEdge(myId+s_nodeId,myId ,s_nodeId);
				}
				else if((graphF.getEdge(nodeId+s_nodeId)==null)&&(graphF.getEdge(s_nodeId+nodeId)==null))
				{
					graphF.addEdge(nodeId+s_nodeId, nodeId ,s_nodeId);
				}
			}
			
		}
		model.addAttribute("graph", json.getJson());
		model.addAttribute(facebook.userOperations().getUserProfile());
		model.addAttribute("Friends", CommonFriendsList);

		return "graphPage";
	}
	
	
	@RequestMapping(value = "/GraphNode", method = RequestMethod.GET)
	public String GraphNode(Model model,@RequestParam(value="id")  String id) {
		if (!facebook.isAuthorized()) {
			return "redirect:/connect/facebook";
		}
		
	
		int degree = graphF.getNode(id).getDegree();

		//compute betweenness centrality for each node
		 BetweennessCentrality bc = new BetweennessCentrality();
	        bc.init(graphF);
	        bc.compute();
		
		FacebookProfile profile = facebook.userOperations().getUserProfile(id);
		//return bc for node selected node
		model.addAttribute("betweenness", graphF.getNode(id).getAttribute("Cb"));
		model.addAttribute("degree", degree);
		
		model.addAttribute("profile", profile);
		return "nodePopUp";
	}
	
}