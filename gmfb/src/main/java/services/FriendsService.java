//package services;
//
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import repository.JpaFriendsRepo;
//import domain.Friend;
//
//@Service
//@Transactional
//public class FriendsService implements FriendsServiceInterface {
//
//	public FriendsService() {
//	};
//
//	@Autowired
//	private JpaFriendsRepo friendsRepo;
//
//	@Override
//	public void addFriends(ArrayList<Friend> CommonFriendsList) {
//		friendsRepo.addFriendList(CommonFriendsList);
//	}
//}
