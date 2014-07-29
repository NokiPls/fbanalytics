package com.lgnm.fb.services;

import java.util.List;

import org.springframework.social.facebook.api.Facebook;

import com.lgnm.fb.domain.Friend;

/**
 * Interface for providing the list of all the friends
 * 
 */

public interface FriendsListInterface {
	public void createFbList(Facebook facebook, Friend user);

	public List<String> getListOfId();

	public List<String> getListOfName();

	public List<Friend> getFriends();

}
