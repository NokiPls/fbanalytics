package com.lgnm.fb.services;

import java.util.List;

import com.lgnm.fb.domain.Friend;

public interface FriendsServiceInterface {
	public void addFriends(List<Friend> FriendsList);
	public void addCommonFriends(List<Friend> CommonFriendsList);
	public void addUser(Friend user);
}
