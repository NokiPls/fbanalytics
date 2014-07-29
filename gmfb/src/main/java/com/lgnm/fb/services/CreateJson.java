package com.lgnm.fb.services;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.lgnm.fb.domain.Friend;

/**
 * Class to create a JSON suitable for using d3.js client side. The JSON is like
 * {"nodes":[{"name":"John","id":"facebookID"},{..}],"links":[{"source":
 * int,"target": int},{...}]}, in which "source" and "target" are the position
 * in the array "nodes" of the elements to be linked.
 */

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

		/*
		 * The index of a friend in idPos is the same as the position of a
		 * friend in the "nodes" array. This way it is quick to check if a
		 * person is already in the JSON and retrieve its position for the
		 * linking process.
		 */

		// Adding the user as position 0
		idPos.add(0, fid);

		if (self.contains("\'")) {
			self = self.replaceAll("\'", "`");
		}

		try {
			node.put(NAME, self).put(ID, fid.toString());
			nodes.put(node);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		node = new JSONObject();

		// "i" are the selected friends.
		for (i = 0; i < friend.size(); i++) {

			// In case a direct friend was already found as common friend.
			if (!idPos.contains(friend.get(i).getFbId())) {

				idPos.add(friend.get(i).getFbId());
				String nameA = friend.get(i).getName();

				if (nameA.contains("\'")) {
					nameA = nameA.replaceAll("\'", "`");
				}

				try {
					// Put the new node and link it to the user.
					node.put(NAME, nameA).put(ID,
							friend.get(i).getFbId().toString());
					nodes.put(node);
					link.put(SOURCE, 0).put(TARGET,
							idPos.indexOf(friend.get(i).getFbId()));
					links.put(link);
				} catch (JSONException e) {
					e.printStackTrace();
				}

				node = new JSONObject();
				link = new JSONObject();
			}

			// Proceed with the common friends.
			List<Friend> common = friend.get(i).getCommonFriends();
			// "j" are the common friends between me and "i"
			for (j = 0; j < common.size(); j++) {

				// Check if a friend has already been encountered.
				if (!idPos.contains(common.get(j).getFbId())) {
					idPos.add(common.get(j).getFbId());
					String nameB = common.get(j).getName();

					if (nameB.contains("'")) {
						nameB = nameB.replace("'", "`");
					}

					try {
						node.put(NAME, nameB).put(ID,
								common.get(j).getFbId().toString());
						nodes.put(node);
						// link to the user
						link.put(SOURCE, 0).put(TARGET,
								idPos.indexOf(common.get(j).getFbId()));
						links.put(link);
					} catch (JSONException e) {
						e.printStackTrace();
					}

					node = new JSONObject();
					link = new JSONObject();

					// link to the "i" friend
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

		// Finalize the json.
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