package gmfb;

import java.util.ArrayList;

import bean.Friends;

public class CreateJson {
	private String json;

	CreateJson(ArrayList<Friends> friend) {
		// creazione nodi
		// {"nodes":[{"name":"Myriel","group":1},{"name":"Napoleon","group":1}],"links":[{"source":73,"target":48,"value":2},{"source":74,"target":48,"value":2},{"source":74,"target":73,"value":3}]}
		json = "{\"node\":[";
		for (int i = 0; i < friend.size(); i++) {
			json += "{\"name\":\"" + friend.get(i).getName()
					+ "\",\"group\":1},";
		}
		json += "],\"links\":[";
	}

	public String getJson() {
		return json;
	}

}
