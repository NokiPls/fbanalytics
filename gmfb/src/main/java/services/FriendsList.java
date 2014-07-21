package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.stereotype.Service;

import domain.Friend;

@Service
public class FriendsList implements FriendsListInterface {
	private List<String> id;
	private List<String> name;
	private List<FacebookProfile> fbFriends;
	private List<Friend> friends;
	private String timeStamp;

	public FriendsList() {
		id = new ArrayList<String>();
		name = new ArrayList<String>();
		friends = new ArrayList<Friend>();
	}

	@Override
	public void createFbList(Facebook facebook, Friend user) {
		fbFriends = facebook.friendOperations().getFriendProfiles();

		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		for (int i = 0; i < fbFriends.size(); i++) {
			// creo lista di nomi e id da passare alla pagina con le chekboxes
			friends.add(new Friend(Long.parseLong(fbFriends.get(i).getId()),
					fbFriends.get(i).getName(), null, user.getFbId(), user.getLoginDate(), timeStamp));
			id.add(fbFriends.get(i).getId());
			name.add(fbFriends.get(i).getName());
		}
	}

	@Override
	public List<String> getListOfId() {
		return id;
	}

	@Override
	public List<String> getListOfName() {
		return name;
	}

	@Override
	public List<Friend> getFriends() {
		return friends;
	}
}
