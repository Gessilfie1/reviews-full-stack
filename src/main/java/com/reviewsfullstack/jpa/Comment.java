package com.reviewsfullstack.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	private long id;
	
	@Lob
	private String commentText;
	
	@ManyToOne
	private Review review;

	//no args constructor
	protected Comment() {
		
	}
	//constructor
	
	public Comment(String commentText) {
		this.commentText = commentText;
	}
	
	public Comment(String commentText, Review review) {
		this.commentText = commentText;
		this.review = review;
	}

	public long getId() {
		return id;
	}

	public String getCommentText() {		
		return commentText;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (id != other.id)
			return false;
		return true;
	}	
	

}
