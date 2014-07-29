package com.lgnm.fb.services;

import java.util.List;

import org.springframework.social.facebook.api.Facebook;

import com.lgnm.fb.domain.Friend;

/**
 * Interface to retrieve the common friends list.
 * 
 */

public interface CommonFriendsListInterface {

	public List<Friend> createCommonList(Facebook facebook,
			String[] idSelected, Friend user);

	public List<Friend> getCommonFriends();
}
