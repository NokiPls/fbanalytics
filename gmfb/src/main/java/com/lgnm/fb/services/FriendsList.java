package com.lgnm.fb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.stereotype.Service;

import com.lgnm.fb.domain.Friend;

@Service
public class FriendsList implements FriendsListInterface {
	private List<String> id;
	private List<String> name;
	private List<FacebookProfile> fbFriends;
	private List<Friend> friends;

	public FriendsList() {
		id = new ArrayList<String>();
		name = new ArrayList<String>();
		friends = new ArrayList<Friend>();
	}

	@Override
	public void createFbList(Facebook facebook, Friend user) {
		fbFriends = facebook.friendOperations().getFriendProfiles();

		for (int i = 0; i < fbFriends.size(); i++) {
			// creo lista di nomi e id da passare alla pagina con le chekboxes
			friends.add(new Friend(Long.parseLong(fbFriends.get(i).getId()),
					fbFriends.get(i).getName(), null, user.getFbId(), user.getLoginDate(), null));
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
