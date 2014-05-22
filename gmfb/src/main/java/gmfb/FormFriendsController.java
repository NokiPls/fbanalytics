package gmfb;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bean.Friends;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormFriendsController {

	private Facebook facebook;

	@Inject
	public FormFriendsController(Facebook facebook) {
		this.facebook = facebook;
	}

	@RequestMapping(value = "/List", method = RequestMethod.GET)
	public String greetingForm(Model model) {

		List<String> id, name;
		Friends fbFriends;

		if (!facebook.isAuthorized()) {
			return "redirect:/connect/facebook";
		}

		// bean with name and id of friends
		PagedList<FacebookProfile> friends = facebook.friendOperations()
				.getFriendProfiles();
		id = new ArrayList<String>();
		name = new ArrayList<String>();

		for (int i = 0; i < friends.size(); i++) {
			id.add(friends.get(i).getId());
			name.add(friends.get(i).getName());
		}

		fbFriends = new Friends();
		fbFriends.setId(id);
		fbFriends.setName(name);
		model.addAttribute("names", fbFriends.getName()).addAttribute("id",
				fbFriends.getId());
		return "friendsList";
	}

	@RequestMapping(value = "/checkboxes", method = RequestMethod.POST)
	public String greetingSubmit(
			@RequestParam(value = "names[]", required = false) String[] name) {

		// model.addAttribute("friends", friends);
		return "hierarchicallist";
	}

}