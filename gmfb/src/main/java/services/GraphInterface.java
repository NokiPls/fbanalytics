package services;

import java.util.List;

import org.graphstream.graph.implementations.SingleGraph;

import domain.Friend;

public interface GraphInterface {
	
	public void makeGraph(List<Friend> commonFriendsList, String myId);
	public SingleGraph calcMetrics(List<Friend> commonFriendsList, Friend user);
	public SingleGraph getGraphF();
	
}
