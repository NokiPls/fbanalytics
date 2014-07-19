package services;

import org.springframework.social.facebook.api.Facebook;

import domain.Friend;

public interface UserInitInterface {
	public Friend initialize(Facebook facebook);
}
