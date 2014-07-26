package com.lgnm.fb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lgnm.fb.domain.Friend;

@Service
public class CreateJson implements CreateJsonInterface {
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
	@Override
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
			if (!idPos.contains(friend.get(i).getFbId())) {
				idPos.add(friend.get(i).getFbId());
				String nameA = friend.get(i).getName();
				if (nameA.contains("\'"))
					nameA = nameA.replaceAll("\'", "`");
				json += "{" + NAME + "\"" + nameA + "\"," + ID + "\""
						+ friend.get(i).getFbId() + "\"},";
				// fpos keeps track of the position of the direct friend in the
				// json
				// link them to the user
				links += "{" + SOURCE + "0," + TARGET + ""
						+ idPos.indexOf(friend.get(i).getFbId()) + "},";
			}
			List<Friend> common = friend.get(i).getCommonFriends();
			for (j = 0; j < common.size(); j++) {
				if (!idPos.contains(common.get(j).getFbId())) {
					idPos.add(common.get(j).getFbId());
					// "j" are the common friends between me and "i"
					String nameB = common.get(j).getName();
					if (nameB.contains("'"))
						nameB = nameB.replace("'", "`");
					json += "{" + NAME + "\"" + nameB + "\"," + ID + "\""
							+ common.get(j).getFbId() + "\"},";
					// link them to the user
					links += "{" + SOURCE + "0," + TARGET + ""
							+ idPos.indexOf(common.get(j).getFbId()) + "},";
					// link them to the "i" friend
					links += "{" + SOURCE + ""
							+ idPos.indexOf(common.get(j).getFbId()) + ","
							+ TARGET + ""
							+ idPos.indexOf(friend.get(i).getFbId()) + "},";
				} else {
					links += "{" + SOURCE + ""
							+ idPos.indexOf(common.get(j).getFbId()) + ","
							+ TARGET + ""
							+ idPos.indexOf(friend.get(i).getFbId()) + "},";
				}
			}
		}
		// finalize the json
		links = (String) links.subSequence(0, links.length() - 1);
		json = (String) json.subSequence(0, json.length() - 1);
		json += "]," + LINKS + links + "]}";
	}

	@Override
	public String getJson() {
		return json;
	}

}