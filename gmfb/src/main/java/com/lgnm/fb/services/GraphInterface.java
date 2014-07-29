package com.lgnm.fb.services;

import java.util.List;

import org.graphstream.graph.implementations.SingleGraph;

import com.lgnm.fb.domain.Friend;

/**
 * Interface for the creation of the graph.
 * 
 */

public interface GraphInterface {

	public void makeGraph(List<Friend> commonFriendsList, String myId);

	public SingleGraph calcMetrics(List<Friend> commonFriendsList, Friend user);

	public SingleGraph getGraphF();

}
