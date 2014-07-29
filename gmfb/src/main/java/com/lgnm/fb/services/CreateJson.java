package com.lgnm.fb.services;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.lgnm.fb.domain.Friend;

@Service
public class CreateJson implements CreateJsonInterface {
	private String json = "";
	private final String NODES = "nodes";
	private final String NAME = "name";
	private final String ID = "id";
	private final String LINKS = "links";
	private final String SOURCE = "source";
	private final String TARGET = "target";

	public CreateJson() {
	}

	// The json is like
	// {"nodes":[{"name":"John Bonham","group":1},{..}],"links":[{"source": int,"target": int,"value": int}]}
	// in which source and target are the position in the array "nodes" of the
	// elements to be linked
	@Override
	public void makeJson(List<Friend> friend, String self, Long fid) {
		json = "";
		int j = 0;
		int i = 0;

		JSONObject mainJson = new JSONObject();
		JSONArray nodes = new JSONArray();
		JSONObject node = new JSONObject();
		JSONArray links = new JSONArray();
		JSONObject link = new JSONObject();

		ArrayList<Long> idPos = new ArrayList<Long>();
		// Adding the user as position 0
		idPos.add(0, fid);
		if (self.contains("\'")) {
			self = self.replaceAll("\'", "`");
		}
		
		try {
			node.put(NAME, self).put(ID, fid.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		nodes.put(node);
		node = new JSONObject();

		for (i = 0; i < friend.size(); i++) {
			// "i" are the selected friends
			if (!idPos.contains(friend.get(i).getFbId())) {
				idPos.add(friend.get(i).getFbId());
				String nameA = friend.get(i).getName();
				if (nameA.contains("\'")) {
					nameA = nameA.replaceAll("\'", "`");
				}
		
				try {
					node.put(NAME, nameA).put(ID,
							friend.get(i).getFbId().toString());
					nodes.put(node);
					link.put(SOURCE, 0).put(TARGET,
							idPos.indexOf(friend.get(i).getFbId()));
					links.put(link);
				} catch (JSONException e) {
					e.printStackTrace();
				}

				// fpos keeps track of the position of the direct friend in the
				// json
				// link them to the user
				node = new JSONObject();
				link = new JSONObject();
			}
			List<Friend> common = friend.get(i).getCommonFriends();
			for (j = 0; j < common.size(); j++) {
				if (!idPos.contains(common.get(j).getFbId())) {
					idPos.add(common.get(j).getFbId());
					// "j" are the common friends between me and "i"
					String nameB = common.get(j).getName();
					if (nameB.contains("'")) {
						nameB = nameB.replace("'", "`");
					}
					
					try {
						node.put(NAME, nameB).put(ID,
								common.get(j).getFbId().toString());
						nodes.put(node);
						// link them to the user
						link.put(SOURCE, 0).put(TARGET,
								idPos.indexOf(common.get(j).getFbId()));
						links.put(link);
					} catch (JSONException e) {
						e.printStackTrace();
					}
	
					node = new JSONObject();
					link = new JSONObject();

					// link them to the "i" friend
					try {
						link.put(SOURCE, idPos.indexOf(common.get(j).getFbId()))
								.put(TARGET,
										idPos.indexOf(friend.get(i).getFbId()));
						links.put(link);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					link = new JSONObject();

				} else {
					try {
						link.put(SOURCE, idPos.indexOf(common.get(j).getFbId()))
								.put(TARGET,
										idPos.indexOf(friend.get(i).getFbId()));
						links.put(link);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					link = new JSONObject();
				}
			}
		}
		// finalize the json
	
		try {
			mainJson.put(NODES, nodes).put(LINKS, links);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		json = mainJson.toString();

	}

	@Override
	public String getJson() {
		return json;
	}

}