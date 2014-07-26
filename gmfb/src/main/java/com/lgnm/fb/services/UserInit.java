package com.lgnm.fb.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;

import com.lgnm.fb.domain.Friend;

@Service
public class UserInit implements UserInitInterface {

	private String timeStamp;
	//private int done = 0;


	@Override
	public Friend initialize(Facebook facebook) {

		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar
				.getInstance().getTime());
		Friend user = new Friend(Long.parseLong(facebook.userOperations()
				.getUserProfile().getId()), facebook.userOperations()
				.getUserProfile().getName(), null, Long.parseLong(facebook
				.userOperations().getUserProfile().getId()), timeStamp,
				null);
		return user;
	}
/*
	@Override
	public int getDone() {
		return done;
	}

	@Override
	public void setDone(int done) {
		this.done = done;
	}*/

}
