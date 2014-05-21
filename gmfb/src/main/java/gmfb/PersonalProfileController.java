package gmfb;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bean.Friends;

@Controller
@RequestMapping("/")
public class PersonalProfileController {

    private Facebook facebook;

    @Inject
    public PersonalProfileController(Facebook facebook) {
        this.facebook = facebook;
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String helloFacebook(Model model) {
        if (!facebook.isAuthorized()) {
            return "redirect:/connect/facebook";
        }

        model.addAttribute(facebook.userOperations().getUserProfile());

        return "personalProfile";
    }
/*
    //controller per la lista di amici
     @RequestMapping("/List")
    public String listFriends(Model model) {
        if (!facebook.isAuthorized()) {
            return "redirect:/connect/facebook";
        }
        model.addAttribute(facebook.userOperations().getUserProfile());
        PagedList<FacebookProfile> friends = facebook.friendOperations().getFriendProfiles();
        model.addAttribute("friends", friends);
    

        return "friendsList";
    }
  */

     //form lista amici
     @RequestMapping("/hierarchical")
     public String savePerson(@ModelAttribute Friends friendForm, Model model) {
         model.addAttribute(facebook.userOperations().getUserProfile());
    	 model.addAttribute("friendForm", friendForm);
         
         return "hierarchicallist";
     }
     
     
       
   /*  @RequestMapping(value="/hierarchical", method=RequestMethod.POST)
     public String savePersonPost(@ModelAttribute SelectedFriends friendForm) {
         System.out.println(friendForm.getName() );
         return "person";
     }*/
}







