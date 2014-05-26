package gmfb;

import java.util.ArrayList;

import bean.Friends;

public class CreateJson {
	private String json;

	CreateJson(ArrayList<Friends> friend) {
		//creazione nodi 
		json = "{\"node\":[";
		for(int i = 0; i<friend.size(); i++){
//			 "[{\"name\":" "}] ";	
		}
	}

	public String getJson() {
		return json;
	}

}
