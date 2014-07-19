package services;

import java.util.List;

import domain.Friend;

public interface CreateJsonInterface {

	public void makeJson(List<Friend> friend, String self, Long fid);
	public String getJson();
}
