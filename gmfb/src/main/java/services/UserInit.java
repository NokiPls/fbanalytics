package services;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;

import domain.Friend;

@Service
public class UserInit implements UserInitInterface {
	
	String timeStamp;
	
	@Override
	public Friend initialize(Facebook facebook) {
		
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		Friend user = new Friend(Long.parseLong(facebook.userOperations()
				.getUserProfile().getId()), facebook.userOperations()
				.getUserProfile().getName(), null, Long.parseLong(facebook
				.userOperations().getUserProfile().getId()), timeStamp , timeStamp);
		return user;
	}

}
