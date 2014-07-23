package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import services.UserInitInterface;

@Controller
@RequestMapping("/")
public class PersonalProfileController {

	private Facebook facebook;
	private UserInitInterface userInit;

	@Autowired
	public PersonalProfileController(Facebook facebook, UserInitInterface userInit) {
		this.facebook = facebook;
		this.userInit = userInit;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String helloFacebook(Model model) {
		if (!facebook.isAuthorized()) {
			return "redirect:/connect/facebook";
		}

		userInit.setDone(0);
		model.addAttribute(facebook.userOperations().getUserProfile());
		return "personalProfile";
	}

}
