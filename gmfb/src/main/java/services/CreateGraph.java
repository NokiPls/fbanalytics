package services;

import java.util.ArrayList;

import org.graphstream.graph.implementations.SingleGraph;

import bean.Friend;

public class CreateGraph {
	public SingleGraph graphF = new SingleGraph("graph");

	public SingleGraph getGraphF() {
		return graphF;
	}

	public CreateGraph(ArrayList<Friend> commonFriendsList, String myId) {
		graphF = new SingleGraph("graph");
		graphF.addNode(myId);
		for (int i = 0; i < commonFriendsList.size(); i++) {
			String nodeId = Long.toString(commonFriendsList.get(i).getId());

			if (graphF.getNode(nodeId) == null) {
				graphF.addNode(nodeId);
				graphF.addEdge(myId + nodeId, myId, nodeId);
			}
			for (int k = 0; k < commonFriendsList.get(i).getCommonFriends()
					.size(); k++) {
				String s_nodeId = Long.toString(commonFriendsList.get(i)
						.getCommonFriends().get(k).getId());

				if (graphF.getNode(s_nodeId) == null) {
					graphF.addNode(s_nodeId);
					graphF.addEdge(nodeId + s_nodeId, nodeId, s_nodeId);
					graphF.addEdge(myId + s_nodeId, myId, s_nodeId);
				} else if ((graphF.getEdge(nodeId + s_nodeId) == null)
						&& (graphF.getEdge(s_nodeId + nodeId) == null)) {
					graphF.addEdge(nodeId + s_nodeId, nodeId, s_nodeId);
				}
			}

		}

	}
}
