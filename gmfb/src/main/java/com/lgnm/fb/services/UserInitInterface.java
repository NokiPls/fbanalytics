package com.lgnm.fb.services;

import org.springframework.social.facebook.api.Facebook;

import com.lgnm.fb.domain.Friend;

/**
 * Interface for creating a "Friend" object with the values of the user.
 * 
 */

public interface UserInitInterface {

	public Friend initialize(Facebook facebook);
}
