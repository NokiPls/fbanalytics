package gmfb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private static Map<String, List> mapMutual = new HashMap<String, List>();

	@Inject
	public FormFriendsController(Facebook facebook) {
		this.facebook = facebook;
	}

	@RequestMapping(value = "/List", method = RequestMethod.GET)
	public String greetingForm(Model model) {

		List<String> id, name;
		model.addAttribute(facebook.userOperations().getUserProfile());
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
		}

		model.addAttribute("names", name).addAttribute("id", id);
		return "friendsList";
	}

	@RequestMapping(value = "/checkboxes", method = RequestMethod.POST)
	public String greetingSubmit(
			@RequestParam(value = "id[]", required = false) String[] id, Model model) {
		if (id.length == 0)
			return "redirect:/friendsList";
		if (!facebook.isAuthorized()) {
			return "redirect:/connect/facebook";
		}
		model.addAttribute(facebook.userOperations().getUserProfile());

		List<String> mutId = new ArrayList<String>();
		List<String> mutName = new ArrayList<String>();

		// nel ciclo esterno prendo i mutual, nell'interno id e nome
		for (int i = 0; i < id.length; i++) {
			PagedList<Reference> mutual = facebook.friendOperations()
					.getMutualFriends(id[i]);
			for (int k = 0; k < mutual.size(); k++) {
				mutId.add(k, mutual.get(k).getId());
				mutName.add(k, mutual.get(k).getName());
			}

			mapMutual.put(id[i], mutName);
			mutName.clear();
			mutId.clear();

		}
		model.addAttribute("mapMutual", mapMutual);
		return "hierarchicallist";
	}

}