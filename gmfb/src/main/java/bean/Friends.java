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
public class Friends {
	private String name;
	private String id;
	private ArrayList<Friends> commonFriends = new ArrayList<Friends>();
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer oid;

	public Friends(){};
	public Friends(String newId, String newName) {
		id = newId;
		name = newName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Friends> getCommonFriends() {
		return commonFriends;
	}

	public void setCommonFriends(ArrayList<Friends> commonFriends) {
		this.commonFriends = commonFriends;
	}

}
