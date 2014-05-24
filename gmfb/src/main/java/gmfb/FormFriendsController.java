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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormFriendsController {

	private Facebook facebook;
	public ArrayList<Friends> CommonFriendsList=new ArrayList<Friends>();

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
			id.add(friends.get(i).getId());
			name.add(friends.get(i).getName());
			// creo lista di amici in comune
			// CommonFriendsList.add(i, new Friends(id.get(i), name.get(i)));

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
		model.addAttribute(facebook.userOperations().getUserProfile());

		// nel ciclo esterno prendo i mutual, nell'interno id e nome
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
		model.addAttribute("mapMutual", CommonFriendsList);
		return "hierarchicallist";
	}
}