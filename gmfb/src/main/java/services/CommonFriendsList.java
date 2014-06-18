package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Reference;
import org.springframework.stereotype.Service;

import domain.Friend;

@Service
public class CommonFriendsList {

	private List<Friend> CommonFriendsList = new ArrayList<Friend>();

	public CommonFriendsList() {
	};

	public void createCommonList(Facebook facebook, String[] idSelected) {
		CommonFriendsList = new ArrayList<Friend>();
		for (int i = 0; i < idSelected.length; i++) {
			PagedList<Reference> mutual = facebook.friendOperations()
					.getMutualFriends(idSelected[i]);
			FacebookProfile friend = facebook.userOperations().getUserProfile(
					idSelected[i]);
			CommonFriendsList.add(i, new Friend(Long.parseLong(friend.getId()),
					friend.getName()));
			for (int k = 0; k < mutual.size(); k++) {
				CommonFriendsList
						.get(i)
						.getCommonFriends()
						.add(new Friend(Long.parseLong(mutual.get(k).getId()),
								mutual.get(k).getName()));

			}
		}

	}

	public List<Friend> getCommonFriends() {
		return CommonFriendsList;
	}
}
