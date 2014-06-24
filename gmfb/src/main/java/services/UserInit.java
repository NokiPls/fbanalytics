package services;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;

import domain.Friend;

@Service
public class UserInit {

	public Friend initialize(Facebook facebook) {
		Friend user = new Friend(Long.parseLong(facebook.userOperations()
				.getUserProfile().getId()), facebook.userOperations()
				.getUserProfile().getName(), null, Long.parseLong(facebook
				.userOperations().getUserProfile().getId()));
		return user;
	}

}
