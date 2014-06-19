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
	private Long appUserId;
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
		this.appUserId = appUserId;
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

}