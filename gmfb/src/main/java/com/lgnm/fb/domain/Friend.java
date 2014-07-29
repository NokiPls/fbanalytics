package com.lgnm.fb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * This is the core for our representation of the gathered data.
 * The "appUserId" stores the id of the current user.
 * Direct friends of the user are those with no "parent_oid" in the
 * one-to-many many-to-one relationship, while common friends 
 * will have such value set accordingly.
 * 
 */

@Entity
@Table(name = "friend")
public class Friend implements Serializable {

	private static final long serialVersionUID = 8828388186922307614L;
	private String name;
	private Long appUserId;
	private double degreeCentrality;
	private double normalizedDegreeCentrality;
	private double betweennessCentrality;
	private double closenessCentrality;
	private double normalizedClosenessCentrality;
	private Long fbId;
	private String loginDate;
	private String searchDate;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long oid;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
	private List<Friend> commonFriends = new ArrayList<Friend>();

	@ManyToOne(cascade = CascadeType.ALL)
	private Friend parent;

	public Friend() {
	};

	public Friend(Long newId, String newName, Friend parent, Long appUserId,
			String loginDate, String searchDate) {
		this.setAppUserId(appUserId);
		this.fbId = newId;
		this.name = newName;
		this.parent = parent;
		this.setLoginDate(loginDate);
		this.setSearchDate(searchDate);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getFbId() {
		return fbId;
	}

	public void setFbId(Long id) {
		this.fbId = id;
	}

	public List<Friend> getCommonFriends() {
		return commonFriends;
	}

	public void setCommonFriends(List<Friend> commonFriends) {
		this.commonFriends = commonFriends;
	}

	public void setParent(Friend parent) {
		this.parent = parent;
	}

	public Long getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(Long appUserId) {
		this.appUserId = appUserId;
	}

	public double getDegreeCentrality() {
		return degreeCentrality;
	}

	public void setDegreeCentrality(double degreeCentrality) {
		this.degreeCentrality = degreeCentrality;
	}

	public double getNormalizedDegreeCentrality() {
		return normalizedDegreeCentrality;
	}

	public void setNormalizedDegreeCentrality(double normalizedDegreeCentrality) {
		this.normalizedDegreeCentrality = normalizedDegreeCentrality;
	}

	public double getBetweennessCentrality() {
		return betweennessCentrality;
	}

	public void setBetweennessCentrality(double betweennessCentrality) {
		this.betweennessCentrality = betweennessCentrality;
	}

	public double getClosenessCentrality() {
		return closenessCentrality;
	}

	public void setClosenessCentrality(double closenessCentrality) {
		this.closenessCentrality = closenessCentrality;
	}

	public double getNormalizedClosenessCentrality() {
		return normalizedClosenessCentrality;
	}

	public void setNormalizedClosenessCentrality(
			double normalizedClosenessCentrality) {
		this.normalizedClosenessCentrality = normalizedClosenessCentrality;
	}

	public String getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

}