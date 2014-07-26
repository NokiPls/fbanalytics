package com.lgnm.fb.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Reference;
import org.springframework.stereotype.Service;

import com.lgnm.fb.domain.Friend;

@Service
public class CommonFriendsList implements CommonFriendsListInterface {

	private List<Friend> commonFriendsList;
	private String timeStamp;

	public CommonFriendsList() {
	};

	@Override
	public List<Friend> createCommonList(Facebook facebook,
			String[] idSelected, Friend user) {
		commonFriendsList = new ArrayList<Friend>();
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar
				.getInstance().getTime());
		for (int i = 0; i < idSelected.length; i++) {
			PagedList<Reference> mutual = facebook.friendOperations()
					.getMutualFriends(idSelected[i]);
			FacebookProfile friend = facebook.userOperations().getUserProfile(
					idSelected[i]);
			commonFriendsList.add(i, new Friend(Long.parseLong(friend.getId()),
					friend.getName(), null, user.getFbId(), user.getLoginDate(),
					timeStamp)); // null è l'user
			for (int k = 0; k < mutual.size(); k++) {
				commonFriendsList
						.get(i)
						.getCommonFriends()
						.add(new Friend(Long.parseLong(mutual.get(k).getId()),
								mutual.get(k).getName(), commonFriendsList
										.get(i), user.getFbId(), user
										.getLoginDate(), timeStamp));

			}
		}
		return commonFriendsList;
	}

	@Override
	public List<Friend> getCommonFriends() {
		return commonFriendsList;
	}

}
