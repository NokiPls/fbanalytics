package services;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;

import domain.Friend;

@Service
public class UserInit implements UserInitInterface {

	public static int searchCommonNumber;
	public static int loginNumber = 0;
	
	@Override
	public Friend initialize(Facebook facebook) {
		searchCommonNumber = 0;
		Friend user = new Friend(Long.parseLong(facebook.userOperations()
				.getUserProfile().getId()), facebook.userOperations()
				.getUserProfile().getName(), null, Long.parseLong(facebook
				.userOperations().getUserProfile().getId()), searchCommonNumber, loginNumber);
		return user;
	}

}
