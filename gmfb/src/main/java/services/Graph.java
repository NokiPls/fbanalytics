package services;

import java.util.List;

import org.graphstream.algorithm.BetweennessCentrality;
import org.graphstream.algorithm.measure.AbstractCentrality;
import org.graphstream.algorithm.measure.ClosenessCentrality;
import org.graphstream.algorithm.measure.DegreeCentrality;
import org.graphstream.graph.implementations.SingleGraph;
import org.springframework.stereotype.Service;

import domain.Friend;

@Service
public class Graph implements GraphInterface {

	public SingleGraph graphF = new SingleGraph("graph");

	public Graph() {
	};

	@Override
	public void makeGraph(List<Friend> commonFriendsList, String myId) {
		graphF = new SingleGraph("graph");
		graphF.addNode(myId);
		for (int i = 0; i < commonFriendsList.size(); i++) {
			String nodeId = Long.toString(commonFriendsList.get(i).getFbId());

			if (graphF.getNode(nodeId) == null) {
				graphF.addNode(nodeId);
				graphF.addEdge(myId + nodeId, myId, nodeId);
			}
			for (int k = 0; k < commonFriendsList.get(i).getCommonFriends()
					.size(); k++) {
				String s_nodeId = Long.toString(commonFriendsList.get(i)
						.getCommonFriends().get(k).getFbId());

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
	
	@Override
	public SingleGraph calcMetrics(List<Friend> commonFriendsList, Friend user) {
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
		
		
		//set metrics in friends
		//setto metriche utente
		Long user_Id = user.getFbId();
		String userId = user_Id.toString();
		user.setBetweennessCentrality( (Double) graphF.getNode(userId).getAttribute("betweenness"));
		user.setClosenessCentrality( (Double) graphF.getNode(userId).getAttribute("closeness"));
		user.setDegreeCentrality( (Double) graphF.getNode(userId).getAttribute("degree"));
		user.setNormalizedClosenessCentrality( (Double) graphF.getNode(userId).getAttribute("norm_closeness"));
		user.setNormalizedDegreeCentrality( (Double) graphF.getNode(userId).getAttribute("norm_degree"));
		
		//setto metriche della sua lista di amici in comune
		
		for (int i = 0; i < commonFriendsList.size(); i++) {
			
			Long _id = commonFriendsList.get(i).getFbId();
			String id = _id.toString();
			commonFriendsList.get(i).setBetweennessCentrality( (Double) graphF.getNode(id).getAttribute("betweenness"));
			commonFriendsList.get(i).setClosenessCentrality( (Double) graphF.getNode(id).getAttribute("closeness"));
			commonFriendsList.get(i).setDegreeCentrality( (Double) graphF.getNode(id).getAttribute("degree"));
			commonFriendsList.get(i).setNormalizedClosenessCentrality( (Double) graphF.getNode(id).getAttribute("norm_closeness"));
			commonFriendsList.get(i).setNormalizedDegreeCentrality( (Double) graphF.getNode(id).getAttribute("norm_degree"));

			
			for (int k = 0; k < commonFriendsList.get(i).getCommonFriends().size(); k++) {
				Long _idk = commonFriendsList.get(i).getFbId();
				String idk = _id.toString();
				commonFriendsList.get(i).getCommonFriends().get(k).setBetweennessCentrality( (Double) graphF.getNode(idk).getAttribute("betweenness"));
				commonFriendsList.get(i).getCommonFriends().get(k).setClosenessCentrality( (Double) graphF.getNode(idk).getAttribute("closeness"));
				commonFriendsList.get(i).getCommonFriends().get(k).setDegreeCentrality( (Double) graphF.getNode(idk).getAttribute("degree"));
				commonFriendsList.get(i).getCommonFriends().get(k).setNormalizedClosenessCentrality( (Double) graphF.getNode(idk).getAttribute("norm_closeness"));
				commonFriendsList.get(i).getCommonFriends().get(k).setNormalizedDegreeCentrality( (Double) graphF.getNode(idk).getAttribute("norm_degree"));
			}

		}
	
		return graphF;

	}

	@Override
	public SingleGraph getGraphF() {
		return graphF;
	}
}
