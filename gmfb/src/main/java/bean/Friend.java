package bean;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;

//@Entity deprecated!?
@DynamicUpdate
@Table(appliesTo = "")
public class Friend {
	private String name;
	@Id
	private Long id;
	private ArrayList<Friend> commonFriends = new ArrayList<Friend>();

	public Friend(){};
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

	public ArrayList<Friend> getCommonFriends() {
		return commonFriends;
	}

	public void setCommonFriends(ArrayList<Friend> commonFriends) {
		this.commonFriends = commonFriends;
	}

}