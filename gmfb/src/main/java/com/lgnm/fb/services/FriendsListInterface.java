package com.lgnm.fb.services;

import java.util.List;

import org.springframework.social.facebook.api.Facebook;

import com.lgnm.fb.domain.Friend;

public interface FriendsListInterface {
	public void createFbList(Facebook facebook, Friend user);

	public List<String> getListOfId();

	public List<String> getListOfName();

	public List<Friend> getFriends();

}
