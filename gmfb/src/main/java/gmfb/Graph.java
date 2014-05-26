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
public class Graph {

	private Facebook facebook;
	public ArrayList<Friends> CommonFriendsList = new ArrayList<Friends>();

	@Inject
	public Graph(Facebook facebook) {
		this.facebook = facebook;
	}

	

	@RequestMapping(value = "/openGraph", method = RequestMethod.POST)
	public String friendsCheckboxesSubmit(
			@RequestParam(value = "id[]", required = false) String[] idSelected,
			Model model) {
		if (idSelected.length == 0)
			return "redirect:/friendsList";
		if (!facebook.isAuthorized()) {
			return "redirect:/connect/facebook";
		}

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

}
