package bean;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Entity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;

//@Entity deprecated!?
@DynamicUpdate
@Table(appliesTo = "")
public class Friend {
	private Long facebook_id;
	private String name;
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "friend_id")//(fetch=FetchType.LAZY)
	private ArrayList<Friend> commonFriends = new ArrayList<Friend>();
	
	public Friend(){};
	public Friend(Long newId, String newName) {
		setFacebook_id(newId);
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
	public Long getFacebook_id() {
		return facebook_id;
	}
	public void setFacebook_id(Long facebook_id) {
		this.facebook_id = facebook_id;
	}

}
