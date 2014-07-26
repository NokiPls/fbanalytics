package com.lgnm.fb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lgnm.fb.domain.Friend;
import com.lgnm.fb.repository.FriendsRepository;

@Service
@Transactional
@ComponentScan("com.lgnm.fb.repository")
public class FriendsService implements FriendsServiceInterface {

	public FriendsService() {
	};

	@Autowired
	public FriendsRepository friendsRepo;

	@Override
	public void addFriends(List<Friend> CommonFriendsList) {
		friendsRepo.addFriendList(CommonFriendsList);
	}
	
	@Override
	public void addUser(Friend user){
		friendsRepo.addUser(user);
	}
}
