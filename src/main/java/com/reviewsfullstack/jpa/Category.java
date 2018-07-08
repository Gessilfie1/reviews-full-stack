package com.reviewsfullstack.jpa;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Category {
	
	@Id
	@GeneratedValue
	//don't have to worry about bringing id into constructor
	private long id;
	private String name;
	
	@ManyToMany(mappedBy = "categories")
	private Collection<Review> reviews;
	

	//default no args constructor required by JPA

	public long getId() {		
		return id;
	}
	
	public Collection<Review> getReviews() {
		return reviews;
	}

	public String getName() {		
		return name;
	}
	
	public Category() {
		
	}
	
	public Category(String name) {
		this.name = name;
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
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
