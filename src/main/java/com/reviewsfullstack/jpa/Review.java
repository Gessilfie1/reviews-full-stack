package com.reviewsfullstack.jpa;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private long id;

	private String title;
	private String content;
	private String imageUrl;

	// owning side of relationship does not need mapped by
	@ManyToMany
	private Collection<Category> categories;

	@ManyToMany
	private Collection<RTag> tags;

	@JsonIgnore
	@OneToMany(mappedBy = "review")
	private Collection<Comment> comments;

	// default no args constructor required by jpa
	public Review() {

	}

	public Review(String title, String content, String imageUrl, Category categories, RTag... tags) {
		this.title = title;
		this.content = content;
		this.imageUrl = imageUrl;
		// use HashSet to avoid duplicates
		this.categories = new HashSet<>(Arrays.asList(categories));
		this.tags = new HashSet<>(Arrays.asList(tags));
	}
	
	public void setComments(Comment...comments) {
		this.comments = new HashSet<>(Arrays.asList(comments));
	}
	
	//new constructor with Tags?

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Collection<Category> getCategories() {
		return categories;
	}

	public Collection<Comment> getComments() {

		return comments;
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
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
