package repository;

import java.util.List;

import domain.Friend;

public interface FriendsRepository {
	public void addFriendList(List<Friend> CommonFriendsList);
	public void addUser(Friend user);
}
