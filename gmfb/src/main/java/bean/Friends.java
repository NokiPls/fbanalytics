package bean;

import java.util.ArrayList;

public class Friends {
	private String name;
	private String id;
	private ArrayList<Friends> commonFriends = new ArrayList<Friends>();

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
