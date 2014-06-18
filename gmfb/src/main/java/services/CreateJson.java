package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import domain.Friend;

@Service
public class CreateJson {
	private String json, links = "";
	private final String NODES = "\"nodes\":[";
	private final String NAME = "\"name\":";
	private final String ID = "\"id\":";
	private final String LINKS = "\"links\":[";
	private final String SOURCE = "\"source\":";
	private final String TARGET = "\"target\":";

	public CreateJson() {
	}

	// The json is like
	// {"nodes":[{"name":"John Bonham","group":1},{..}],"links":[{"source":"int","target":"int","value":"int"}]}
	// in which source and target are the position in the array "nodes" of the
	// elements to be linked
	public void makeJson(List<Friend> friend, String self, Long fid) {
		json= "";
		links = "";
		int j = 0;
		int i = 0;
		ArrayList<Long> idPos = new ArrayList<Long>();
		// Adding the user as position 0
		idPos.add(0, fid);
		if (self.contains("\'"))
			self = self.replaceAll("\'", "`");
		json = "{" + NODES + "{" + NAME + "\"" + self + "\"," + ID + "\"" + fid
				+ "\"},";
		for (i = 0; i < friend.size(); i++) {
			// "i" are the selected friends
			if (!idPos.contains(friend.get(i).getId())) {
				idPos.add(friend.get(i).getId());
				String nameA = friend.get(i).getName();
				if (nameA.contains("\'"))
					nameA = nameA.replaceAll("\'", "`");
				json += "{" + NAME + "\"" + nameA + "\"," + ID + "\""
						+ friend.get(i).getId() + "\"},";
				// fpos keeps track of the position of the direct friend in the
				// json
				// link them to the user
				links += "{" + SOURCE + "0," + TARGET + ""
						+ idPos.indexOf(friend.get(i).getId()) + "},";
			}
			List<Friend> common = friend.get(i).getCommonFriends();
			for (j = 0; j < common.size(); j++) {
				if (!idPos.contains(common.get(j).getId())) {
					idPos.add(common.get(j).getId());
					// "j" are the common friends between me and "i"
					String nameB = common.get(j).getName();
					if (nameB.contains("'"))
						nameB = nameB.replace("'", "`");
					json += "{" + NAME + "\"" + nameB + "\"," + ID + "\""
							+ common.get(j).getId() + "\"},";
					// link them to the user
					links += "{" + SOURCE + "0," + TARGET + ""
							+ idPos.indexOf(common.get(j).getId()) + "},";
					// link them to the "i" friend
					links += "{" + SOURCE + ""
							+ idPos.indexOf(common.get(j).getId()) + ","
							+ TARGET + ""
							+ idPos.indexOf(friend.get(i).getId()) + "},";
				} else {
					links += "{" + SOURCE + ""
							+ idPos.indexOf(common.get(j).getId()) + ","
							+ TARGET + ""
							+ idPos.indexOf(friend.get(i).getId()) + "},";
				}
			}
		}
		// finalize the json
		links = (String) links.subSequence(0, links.length() - 1);
		json = (String) json.subSequence(0, json.length() - 1);
		json += "]," + LINKS + links + "]}";
	}

	public String getJson() {
		return json;
	}

}