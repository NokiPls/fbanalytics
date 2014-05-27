package gmfb;

import java.util.ArrayList;

import bean.Friends;

public class CreateJson {
	private String json, links = "";

	CreateJson(ArrayList<Friends> friend, String self) {
		// creazione nodi
		int j = 0;
		int i = 0;
		int fpos = 0;
		json = "{\"nodes\":[{\"name\":\"" + self + "\",\"group\":1},";
		for (i = 0; i < friend.size(); i++) {
			json += "{\"name\":\"" + friend.get(i).getName()
					+ "\",\"group\":1},";
			fpos += j + 1;
			links += "{\"source\":0,\"target\":" + fpos + ",\"value\":1},";
			ArrayList<Friends> common = friend.get(i).getCommonFriends();
			for (j = 0; j < common.size(); j++) {
				json += "{\"name\":\"" + common.get(j).getName()
						+ "\",\"group\":1},";
				links += "{\"source\":0,\"target\":" + (fpos + j + 1)
						+ ",\"value\":1},";
				links += "{\"source\":" + fpos + ",\"target\":"
						+ (fpos + j + 1) + ",\"value\":1},";
			}
		}
		links = (String) links.subSequence(0, links.length() - 1);
		json = (String) json.subSequence(0, json.length() - 1);
		json += "],\"links\":[" + links + "]}";
	}

	// :[{\"source\":1,\"target\":0,\"value\":1}]}";

	public String getJson() {
		return json;
	}

}
