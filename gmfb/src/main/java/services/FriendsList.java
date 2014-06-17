package services;

import java.util.ArrayList;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.stereotype.Service;

@Service
public class FriendsList {
	private ArrayList<String> id;
	private ArrayList<String> name;

	public FriendsList() {
		id = new ArrayList<String>();
		name = new ArrayList<String>();
	}

	public void createFbList(Facebook facebook) {
		PagedList<FacebookProfile> friends = facebook.friendOperations()
				.getFriendProfiles();

		for (int i = 0; i < friends.size(); i++) {
			// creo lista di nomi e id da passare alla pagina con le chekboxes
			id.add(friends.get(i).getId());
			name.add(friends.get(i).getName());
		}
	}

	public ArrayList<String> getListOfId() {
		return id;
	}

	public ArrayList<String> getListOfName() {
		return name;
	}
}
