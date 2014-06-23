package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "friend")
public class Friend implements Serializable {

	private static final long serialVersionUID = 8828388186922307614L;
	private String name;
	//To separate persons which are friends with different app users
	private Long appUserId;
	//To distinguish between same person, same app user, but different graph requests
	private int graphNumber;
	private int degreeCentrality;
	private int normalizedDegreeCentrality;
	private int betweennessCentrality;
	private int closenessCentrality;
	private int normalizedClosenessCentrality;
	private Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long oid;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
	private List<Friend> commonFriends = new ArrayList<Friend>();

	@ManyToOne(cascade = CascadeType.ALL)
	private Friend parent;

	public Friend() {
	};

	public Friend(Long newId, String newName, Friend parent, Long appUserId) {
		this.setAppUserId(appUserId);
		this.id = newId;
		this.name = newName;
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Friend> getCommonFriends() {
		return commonFriends;
	}

	public void setCommonFriends(List<Friend> commonFriends) {
		this.commonFriends = commonFriends;
	}

	public void setParent(Friend parent) {
		this.parent = parent;
	}

	public int getGraphNumber() {
		return graphNumber;
	}

	public void setGraphNumber(int graphNumber) {
		this.graphNumber = graphNumber;
	}

	public Long getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(Long appUserId) {
		this.appUserId = appUserId;
	}

	public int getDegreeCentrality() {
		return degreeCentrality;
	}

	public void setDegreeCentrality(int degreeCentrality) {
		this.degreeCentrality = degreeCentrality;
	}

	public int getNormalizedDegreeCentrality() {
		return normalizedDegreeCentrality;
	}

	public void setNormalizedDegreeCentrality(int normalizedDegreeCentrality) {
		this.normalizedDegreeCentrality = normalizedDegreeCentrality;
	}

	public int getBetweennessCentrality() {
		return betweennessCentrality;
	}

	public void setBetweennessCentrality(int betweennessCentrality) {
		this.betweennessCentrality = betweennessCentrality;
	}

	public int getClosenessCentrality() {
		return closenessCentrality;
	}

	public void setClosenessCentrality(int closenessCentrality) {
		this.closenessCentrality = closenessCentrality;
	}

	public int getNormalizedClosenessCentrality() {
		return normalizedClosenessCentrality;
	}

	public void setNormalizedClosenessCentrality(
			int normalizedClosenessCentrality) {
		this.normalizedClosenessCentrality = normalizedClosenessCentrality;
	}

}