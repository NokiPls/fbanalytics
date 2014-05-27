package gmfb;

import java.util.ArrayList;

import bean.Friends;

public class CreateJson {
	private String json, links = "";

	// The json is like
	// {"nodes":[{"name":"John Bonham","group":1},{..}],"links":[{"source":"int","target":"int","value":"int"}]}
	// in which source and target are the position in the array "nodes" of the
	// elements to be linked
	CreateJson(ArrayList<Friends> friend, String self) {
		int j = 0;
		int i = 0;
		int fpos = 0;
		// Adding the user as position 0
		json = "{\"nodes\":[{\"name\":\"" + self + "\",\"group\":1},";
		for (i = 0; i < friend.size(); i++) {
			// "i" are the selected friends
			json += "{\"name\":\"" + friend.get(i).getName()
					+ "\",\"group\":1},";
			// fpos keeps track of the position of the direct friend in the json
			fpos += j + 1;
			// link them to the user
			links += "{\"source\":0,\"target\":" + fpos + ",\"value\":1},";
			ArrayList<Friends> common = friend.get(i).getCommonFriends();
			for (j = 0; j < common.size(); j++) {
				// "j" are the common friends between me and "i"
				json += "{\"name\":\"" + common.get(j).getName()
						+ "\",\"group\":1},";
				// link them to the user
				links += "{\"source\":0,\"target\":" + (fpos + j + 1)
						+ ",\"value\":1},";
				// link them to the "i" friend
				links += "{\"source\":" + fpos + ",\"target\":"
						+ (fpos + j + 1) + ",\"value\":1},";
			}
		}
		// finalize the json
		links = (String) links.subSequence(0, links.length() - 1);
		json = (String) json.subSequence(0, json.length() - 1);
		json += "],\"links\":[" + links + "]}";
	}

	public String getJson() {
		return json;
	}

}
