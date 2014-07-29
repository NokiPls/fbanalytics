package com.lgnm.fb.services;

import java.util.List;
import com.lgnm.fb.domain.Friend;

/**
 * Interface for creating the JSON parsed client side by "d3.js"
 * 
 */

public interface CreateJsonInterface {

	public void makeJson(List<Friend> friend, String self, Long fid);

	public String getJson();

}
