package com.lgnm.fb.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Repository;

import com.lgnm.fb.domain.Friend;

/**
 * Implementation of the DAO.
 * The configuration for the entity manager is located in
 * the dataSource-context.xml file.
 * 
 */

@ImportResource("classpath:dataSource-context.xml")
@Repository
public class JpaFriendsRepo implements FriendsRepository {

	@PersistenceContext
	public EntityManager em;

	@Override
	@Transactional
	public void addFriendList(List<Friend> commonFriendsList) {
		int i;
		for (i = 0; i < commonFriendsList.size(); i++) {
			em.merge(commonFriendsList.get(i));
		}
	}
	
	@Override
	@Transactional
	public void addCommonFriendList(List<Friend> commonFriendsList) {
		int i;
		for (i = 0; i < commonFriendsList.size(); i++) {
			em.persist(commonFriendsList.get(i));
		}
	}

	@Override
	@Transactional
	public void addUser(Friend user) {
		em.merge(user);
	}
}