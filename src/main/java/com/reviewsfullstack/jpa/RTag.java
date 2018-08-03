package com.reviewsfullstack.jpa;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class RTag {
	
	@Id
	@GeneratedValue
	private long id;

	private String tagName;
	
	//no args constructor for JPA
	public RTag() {
		
	}

	public RTag(String tagName) {
		this.tagName = tagName;		
	}

	public long getId() {
		
		return id;
	}

	public Object getName() {		
		return tagName;
	}

}
