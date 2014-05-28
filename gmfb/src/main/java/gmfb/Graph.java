package gmfb;

import bean.Friends;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Graph {

	private Facebook facebook;
	public ArrayList<Friends> CommonFriendsList = new ArrayList<Friends>();

	@Inject
	public Graph(Facebook facebook) {
		this.facebook = facebook;
	}
	@RequestMapping(value = "/GraphNode", method = RequestMethod.GET)
	public String GraphNode(Model model,@RequestParam(value="id")  String id) {
		if (!facebook.isAuthorized()) {
			return "redirect:/connect/facebook";
		}
		FacebookProfile profile = facebook.userOperations().getUserProfile(id);
		model.addAttribute("profile", profile);
		return "nodePopUp";
	}
	


}
