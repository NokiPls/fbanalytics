package com.lgnm.fb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.stereotype.Service;

import com.lgnm.fb.domain.Friend;

/**
 * Here the creation of the list of "Friend" objects representing the friends
 * retrieved from Facebook is handled.
 * We provide such list has three ways:
 * 1. List<String> id
 * 2. List<String> name
 * 3. List<Friend> friends
 */

@Service
public class FriendsList implements FriendsListInterface {
	private List<String> id;
	private List<String> name;
	private List<Friend> friends;

	public FriendsList() {
		id = new ArrayList<String>();
		name = new ArrayList<String>();
		friends = new ArrayList<Friend>();
	}

	@Override
	public void createFbList(Facebook facebook, Friend user) {
		List<FacebookProfile> fbFriends;
		id = new ArrayList<String>();
		name = new ArrayList<String>();
		friends = new ArrayList<Friend>();

		// Get the friends.
		fbFriends = facebook.friendOperations().getFriendProfiles();

		// Populate the three lists accordingly.
		for (int i = 0; i < fbFriends.size(); i++) {
			friends.add(new Friend(Long.parseLong(fbFriends.get(i).getId()),
					fbFriends.get(i).getName(), null, user.getFbId(), user
							.getLoginDate(), null));
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
