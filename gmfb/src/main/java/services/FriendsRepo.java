package services;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import bean.Friend;

@Service
@Repository
public class FriendsRepo {

	//@Autowired
	private EntityManager em;

	@Inject
	private void FriendRepo(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public void addFriendList(ArrayList<Friend> CommonFriendsList) {
		int i;
		for (i = 0; i < CommonFriendsList.size(); i++) {
			em.persist(CommonFriendsList.get(i));
		}
	}

	@Transactional
	public void addFriend(Friend friend) {
		// friend è l'user, quello che fa la query
		em.persist(friend);
		;
	}
}