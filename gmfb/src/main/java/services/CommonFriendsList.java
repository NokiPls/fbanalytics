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

	private List<Friend> commonFriendsList;
//	private List<Friend> commonOnly;

	public CommonFriendsList() {
	};

	public void createCommonList(Facebook facebook, String[] idSelected,
			Friend user) {
		commonFriendsList = new ArrayList<Friend>();
//		commonOnly = new ArrayList<Friend>();
		for (int i = 0; i < idSelected.length; i++) {
			PagedList<Reference> mutual = facebook.friendOperations()
					.getMutualFriends(idSelected[i]);
			FacebookProfile friend = facebook.userOperations().getUserProfile(
					idSelected[i]);
			commonFriendsList.add(i, new Friend(Long.parseLong(friend.getId()),
					friend.getName(), null, user.getId())); // null è l'user
			for (int k = 0; k < mutual.size(); k++) {
				commonFriendsList
						.get(i)
						.getCommonFriends()
						.add(new Friend(Long.parseLong(mutual.get(k).getId()),
								mutual.get(k).getName(), commonFriendsList
										.get(i), user.getId()));

			}
		}

	}

	public List<Friend> getCommonFriends() {
		return commonFriendsList;
	}
	
//	public List<Friend> getCommonOnly() {
//		return commonOnly;
//	}
}
