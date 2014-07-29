package com.lgnm.fb.repository;

import java.util.List;

import com.lgnm.fb.domain.Friend;

/**
 * Interface for the DAO
 *
 */

public interface FriendsRepository {

	public void addFriendList(List<Friend> CommonFriendsList);

	public void addCommonFriendList(List<Friend> CommonFriendsList);

	public void addUser(Friend user);
}
