package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	/**
	 * 
	 */
	private static final long serialVersionUID = 8828388186922307614L;
	private String name;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long oid;
	
	private Long id;
	@OneToMany(mappedBy = "key")
	// attento lazy
	private List<Friend> commonFriends = new ArrayList<Friend>();

	@ManyToOne
	private Friend key;

	public Friend() {
	};

	public Friend(Long newId, String newName) {
		id = newId;
		name = newName;
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

}