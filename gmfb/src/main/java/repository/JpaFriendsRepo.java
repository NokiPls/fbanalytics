package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Repository;

import domain.Friend;

@ImportResource("classpath:dataSource-context.xml")
@Repository
public class JpaFriendsRepo implements FriendsRepository {

	@PersistenceContext
	public EntityManager em;

	// @Inject
	// private void FriendRepo(EntityManager em) {
	// this.em = em;
	// }

	@Override
	@Transactional
	public void addFriendList(List<Friend> commonFriendsList) {
		int i;
		for (i = 0; i < commonFriendsList.size(); i++) {
			em.persist(commonFriendsList.get(i));
			
		}
	}

	@Override
	@Transactional
	public void addUser(Friend user) {
		em.persist(user);
	}
}