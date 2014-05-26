package gmfb;

import java.util.ArrayList;

import bean.Friends;

public class CreateJson {
	private String json;

	CreateJson(ArrayList<Friends> friend) {
		// creazione nodi
		json = "{\"node\":[";
		for (int i = 0; i < friend.size(); i++) {
			json += "{\"name\":\"" + friend.get(i).getName()
					+ "\",\"group\":1},";
			ArrayList<Friends> common = friend.get(i).getCommonFriends();
			for (int j = 0; j < common.size(); j++) {
				json += "{\"name\":\"" + common.get(j).getName()
						+ "\",\"group\":1},";
			}
		}
		json= (String) json.subSequence(0, json.length()-1);
		json += "],\"links\":[";
		
		json += "]}";
	}

//:[{\"source\":1,\"target\":0,\"value\":1}]}";
	
	public String getJson() {
		return json;
	}

}
