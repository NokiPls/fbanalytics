package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.graphstream.algorithm.BetweennessCentrality;
import org.graphstream.algorithm.measure.AbstractCentrality;
import org.graphstream.algorithm.measure.ClosenessCentrality;
import org.graphstream.algorithm.measure.DegreeCentrality;
import org.graphstream.graph.implementations.SingleGraph;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import services.CommonFriendsList;
import services.CreateJson;
import services.ListOfFriends;
import bean.Friend;

@Controller
public class FormFriendsController {

	private Facebook facebook;
	public ArrayList<Friend> commonFriendsList = new ArrayList<Friend>();
	public	SingleGraph graphF = new SingleGraph("graph");

	@Inject
	public FormFriendsController(Facebook facebook) {
		this.facebook = facebook;
	}

	@RequestMapping(value = "/List", method = RequestMethod.GET)
	public String friendsCheckboxes(Model model) {

		if (!facebook.isAuthorized()) {
			return "redirect:/connect/facebook";
		}
		//the service create list of friends to display in the checkboxes
		ListOfFriends friends= new ListOfFriends(facebook);

		model.addAttribute(facebook.userOperations().getUserProfile());
		model.addAttribute("names", friends.getListOfName()).addAttribute("id", friends.getListOfId());
		return "friendsList";
	}

	@RequestMapping(value = "/checkboxes", method = RequestMethod.POST)
	public String friendsCheckboxesSubmit(
			@RequestParam(value = "id[]", required = false) String[] idSelected,
			Model model) {

		if (idSelected==null)
			return "redirect:/List";
		if (!facebook.isAuthorized()) {
			return "redirect:/connect/facebook";
		}
		
		// service crea Lista di amici selezionati e per ognuno di essi la lista degli
		// amici in comune
		CommonFriendsList common=new CommonFriendsList(facebook,idSelected );
		commonFriendsList=common.getCommonFriends();

		model.addAttribute(facebook.userOperations().getUserProfile());
		model.addAttribute("Friends", commonFriendsList);
		return "hierarchicallist";
	}

	@RequestMapping(value = "/openGraph", method = RequestMethod.POST)
	public String newGraph(Model model) {
		if (!facebook.isAuthorized()) {
			return "redirect:/connect/facebook";
		}

		CreateJson json = new CreateJson(commonFriendsList, facebook
				.userOperations().getUserProfile().getName(), Long.parseLong(facebook
				.userOperations().getUserProfile().getId()));
		//TODO service che crei grafo per statistica
		//creo grafo per statistica
		String myId = facebook.userOperations().getUserProfile().getId();
		graphF=  new SingleGraph("graph");
		graphF.addNode(myId);
		for(int i=0; i<commonFriendsList.size();i++)
		{
			String nodeId=Long.toString(commonFriendsList.get(i).getId());
			
			if(graphF.getNode(nodeId)==null)
			{
				graphF.addNode(nodeId);
				graphF.addEdge(myId+nodeId,myId ,nodeId );
			}
			for(int k=0; k<commonFriendsList.get(i).getCommonFriends().size();k++)
			{
				String s_nodeId=Long.toString(commonFriendsList.get(i).getCommonFriends().get(k).getId());
				
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
		/* compute all the metrics*/

		//compute DegreeCentrality centrality for each node
		DegreeCentrality dc=new	DegreeCentrality();
		dc.init(graphF);
		dc.setCentralityAttribute("degree");
		dc.compute();
		
		//compute normalized DegreeCentrality centrality for each node
		DegreeCentrality ndc=new DegreeCentrality("norm_degree", AbstractCentrality.NormalizationMode.SUM_IS_1);
		ndc.init(graphF);
		ndc.compute();
	
		//compute betweenness centrality for each node
			BetweennessCentrality bc = new BetweennessCentrality("betweenness");
	        bc.init(graphF);
	        bc.compute();
	
	      //compute ClosenessCentrality centrality for each node
	        ClosenessCentrality cc= new ClosenessCentrality("closeness");
	        cc.init(graphF);
	        cc.compute();
	        
	      //compute normalized ClosenessCentrality centrality for each node
	        ClosenessCentrality ncc= new ClosenessCentrality("norm_closeness", AbstractCentrality.NormalizationMode.SUM_IS_1, true, false);
	        ncc.init(graphF);
	        ncc.compute();
	      
	        
		model.addAttribute("graph", json.getJson());
		model.addAttribute(facebook.userOperations().getUserProfile());
		model.addAttribute("Friends", commonFriendsList);

		return "graphPage";
	}
	
	
	@RequestMapping(value = "/GraphNode", method = RequestMethod.GET)
	public String GraphNode(Model model,@RequestParam(value="id")  String id) {
		if (!facebook.isAuthorized()) {
			return "redirect:/connect/facebook";
		}
		
		
		FacebookProfile profile = facebook.userOperations().getUserProfile(id);

		model.addAttribute("betweenness", graphF.getNode(id).getAttribute("betweenness"));
		model.addAttribute("closeness", graphF.getNode(id).getAttribute("closeness"));
		model.addAttribute("norm_closeness", graphF.getNode(id).getAttribute("norm_closeness"));
		model.addAttribute("degree", graphF.getNode(id).getAttribute("degree"));
		model.addAttribute("norm_degree", graphF.getNode(id).getAttribute("norm_degree"));
		
		model.addAttribute("profile", profile);
		return "nodePopUp";
	}
	
}