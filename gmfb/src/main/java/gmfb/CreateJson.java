package gmfb;

import java.util.ArrayList;

import bean.Friends;

public class CreateJson {
	private String json, links = "";

	// The json is like
	// {"nodes":[{"name":"John Bonham","group":1},{..}],"links":[{"source":"int","target":"int","value":"int"}]}
	// in which source and target are the position in the array "nodes" of the
	// elements to be linked
	CreateJson(ArrayList<Friends> friend, String self, String id) {
		int j = 0;
		int i = 0;
		ArrayList<String> idPos = new ArrayList<String>();
		// Adding the user as position 0
		idPos.add(0, id);
		json = "{\"nodes\":[{\"name\":\"" + self + "\",\"id\":\"" + id
				+ "\",\"group\":1},";
		for (i = 0; i < friend.size(); i++) {
			// "i" are the selected friends
			if (!idPos.contains(friend.get(i).getId())) {
				idPos.add(friend.get(i).getId());
				json += "{\"name\":\"" + friend.get(i).getName()
						+ "\",\"id\":\"" + friend.get(i).getId()
						+ "\",\"group\":1},";
				// fpos keeps track of the position of the direct friend in the
				// json
				// link them to the user
				links += "{\"source\":0,\"target\":"
						+ idPos.indexOf(friend.get(i).getId())
						+ ",\"value\":1},";
			}
			ArrayList<Friends> common = friend.get(i).getCommonFriends();
			for (j = 0; j < common.size(); j++) {
				if (!idPos.contains(common.get(j).getId())) {
					idPos.add(common.get(j).getId());
					// "j" are the common friends between me and "i"
					json += "{\"name\":\"" + common.get(j).getName()
							+ "\",\"id\":\"" + common.get(j).getId()
							+ "\",\"group\":1},";
					// link them to the user
					links += "{\"source\":0,\"target\":"
							+ idPos.indexOf(common.get(j).getId())
							+ ",\"value\":1},";
					// link them to the "i" friend
					links += "{\"source\":"
							+ idPos.indexOf(common.get(j).getId())
							+ ",\"target\":"
							+ idPos.indexOf(friend.get(i).getId())
							+ ",\"value\":1},";
				} else {
					links += "{\"source\":"
							+ idPos.indexOf(common.get(j).getId())
							+ ",\"target\":"
							+ idPos.indexOf(friend.get(i).getId())
							+ ",\"value\":1},";
				}
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
