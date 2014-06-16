package services;

import java.util.ArrayList;

import org.graphstream.algorithm.BetweennessCentrality;
import org.graphstream.algorithm.measure.AbstractCentrality;
import org.graphstream.algorithm.measure.ClosenessCentrality;
import org.graphstream.algorithm.measure.DegreeCentrality;
import org.graphstream.graph.implementations.SingleGraph;
import org.springframework.stereotype.Service;

import domain.Friend;

@Service
public class Graph {
	public SingleGraph graphF = new SingleGraph("graph");

	public SingleGraph getGraphF() {
		return graphF;
	}

	public Graph() {
	};

	public void makeGraph(ArrayList<Friend> commonFriendsList,
			String myId) {
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

	public SingleGraph calcMetrics() {
		/* compute all the metrics */

		// compute DegreeCentrality centrality for each node
		DegreeCentrality dc = new DegreeCentrality();
		dc.init(graphF);
		dc.setCentralityAttribute("degree");
		dc.compute();

		// compute normalized DegreeCentrality centrality for each node
		DegreeCentrality ndc = new DegreeCentrality("norm_degree",
				AbstractCentrality.NormalizationMode.SUM_IS_1);
		ndc.init(graphF);
		ndc.compute();

		// compute betweenness centrality for each node
		BetweennessCentrality bc = new BetweennessCentrality("betweenness");
		bc.init(graphF);
		bc.compute();

		// compute ClosenessCentrality centrality for each node
		ClosenessCentrality cc = new ClosenessCentrality("closeness");
		cc.init(graphF);
		cc.compute();

		// compute normalized ClosenessCentrality centrality for each node
		ClosenessCentrality ncc = new ClosenessCentrality("norm_closeness",
				AbstractCentrality.NormalizationMode.SUM_IS_1, true, false);
		ncc.init(graphF);
		ncc.compute();
		return graphF;

	}
}
