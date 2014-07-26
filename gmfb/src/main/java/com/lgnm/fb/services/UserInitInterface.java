package com.lgnm.fb.services;

import org.springframework.social.facebook.api.Facebook;

import com.lgnm.fb.domain.Friend;

public interface UserInitInterface {

	public Friend initialize(Facebook facebook);
/*
	public int getDone();

	public void setDone(int persisted);
*/
}
