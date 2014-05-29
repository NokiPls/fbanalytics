package gmfb;

import bean.Friends;
import java.util.ArrayList;
import javax.inject.Inject;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;


@Controller
public class Graph {

	private Facebook facebook;
	public ArrayList<Friends> CommonFriendsList = new ArrayList<Friends>();

	@Inject
	public Graph(Facebook facebook) {
		this.facebook = facebook;
	}



}
