package services;

import java.util.ArrayList;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Reference;

import bean.Friend;

public class CommonFriendsList {

	ArrayList<Friend> CommonFriendsList = new ArrayList<Friend>();
	
	public CommonFriendsList(Facebook facebook, String[] idSelected) {
		for (int i = 0; i < idSelected.length; i++) {
			PagedList<Reference> mutual = facebook.friendOperations()
					.getMutualFriends(idSelected[i]);

			FacebookProfile friend = facebook.userOperations().getUserProfile(
					idSelected[i]);
			CommonFriendsList.add(i,
					new Friend(Long.parseLong(friend.getId()), friend.getName()));
			for (int k = 0; k < mutual.size(); k++) {
				CommonFriendsList
						.get(i)
						.getCommonFriends()
						.add(new Friend(Long.parseLong(mutual.get(k).getId()), mutual.get(k)
								.getName()));

			}
		}
		
	}

	public ArrayList<Friend> getCommonFriends()
	{
		return CommonFriendsList;
	}
}
