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

	private Facebook facebook; //TODO

	@Inject
	public FormFriendsController(Facebook facebook) {
		this.facebook = facebook;
	}

	@RequestMapping(value = "/List", method = RequestMethod.GET)
	public String greetingForm(Model model) {
		PagedList<FacebookProfile> friends = facebook.friendOperations()
				.getFriendProfiles();
		List<String> id = new ArrayList<String>();
		List<String> name = new ArrayList<String>();
		for (int i = 0; i < friends.size(); i++) {
			id.add(friends.get(i).getId());
			name.add(friends.get(i).getName());
		}

		Friends fbFriends = new Friends();
		fbFriends.setId(id);
		fbFriends.setName(name);
		model.addAttribute("friends", fbFriends);
		return "friendsList";
	}
	/*
	 * @RequestMapping(value="/greeting", method=RequestMethod.POST) public
	 * String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
	 * model.addAttribute("greeting", greeting); return "result"; }
	 */
}