package bean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//@Repository per salvare 



@Entity
@Table(name = "friend")
public class Friend implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8828388186922307614L;
	private String name;
	@Id
	private Long id;
	@OneToMany(mappedBy="nomeattributo") //attento lazy
	private ArrayList<Friend> commonFriends = new ArrayList<Friend>();

	@ManyToOne
	private Friend nomeattributo;
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