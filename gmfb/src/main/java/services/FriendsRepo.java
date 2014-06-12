package services;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import bean.Friend;

@Service
@Repository
public class FriendsRepo{
	@Autowired
	private EntityManager em;
	
	public void addFriend(Friend friend) {
		//friend è l'user, quello che fa la query
	em.persist(friend);;
	}
}