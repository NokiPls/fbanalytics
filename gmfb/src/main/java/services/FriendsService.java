package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repository.FriendsRepository;
import domain.Friend;

@Service
@Transactional
@ComponentScan("repository")
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
