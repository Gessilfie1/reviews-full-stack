package com.reviewsfullstack.jpa;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
@Entity
public class RTag {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToMany
	private Collection<Review> reviews;
	
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
