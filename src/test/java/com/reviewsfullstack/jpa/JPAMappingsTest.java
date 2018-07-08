package com.reviewsfullstack.jpa;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingsTest {
	
	@Resource
	private TestEntityManager entityManager;
	
	@Resource
	private CategoryRepository categoryRepo;
	
	@Resource
	private ReviewRepository reviewRepo;
	
	
	
	
	@Test
	public void shouldSaveAndLoadCategory() {
		Category category = categoryRepo.save(new Category("category"));
		long categoryId = category.getId();
		
		entityManager.flush(); //forces jpa to hit database when we try to find it
		entityManager.clear();
		
		Optional<Category> result = categoryRepo.findById(categoryId);
		category = result.get();
		assertThat(category.getName(), is ("category"));
	}
	
	@Test
	public void shouldGenerateCategoryId() {
		Category category = categoryRepo.save(new Category("category"));
		long categoryId = category.getId();
		
		entityManager.flush(); 
		entityManager.clear();
		
		assertThat(categoryId, is(greaterThan(0L)));
	}
	
	@Test
	public void shouldSaveAndLoadReview() {
		Review review = new Review("review title", "review content");
		review = reviewRepo.save(review);
		long reviewId = review.getId();
		
		entityManager.flush(); 
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getTitle(), is ("review title"));
	}
	
	@Test
	public void shouldEstablishReviewtoCategoryRelationships() {
		
		Category drama = categoryRepo.save(new Category("Drama"));
		Category comedy = categoryRepo.save(new Category("Comedy"));
		
		Review review = new Review("Forrest Gump", "review content", drama, comedy);
		review = reviewRepo.save(review);
		long reviewId = review.getId();
		
		entityManager.flush(); 
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		
		assertThat(review.getCategories(), containsInAnyOrder(drama, comedy));
	}
	
	@Test
	public void shouldFindReviewsForCategory() {
		Category comedy = categoryRepo.save(new Category("comedy"));
		
		Review forrestGump = reviewRepo.save(new Review("Forrest Gump", "review content", comedy));
		Review toyStory = reviewRepo.save(new Review("Toy Story", "review content", comedy));
		
		entityManager.flush(); 
		entityManager.clear();
		
		Collection<Review> reviewsForCategory = reviewRepo.findByCategoriesContains(comedy);
		
		assertThat(reviewsForCategory, containsInAnyOrder(forrestGump, toyStory));
	}
	
	@Test
	public void shouldFindReviewsForCategoryId() {
		Category drama = categoryRepo.save(new Category("Drama"));
		long categoryId = drama.getId();
		
		Review forrestGump = reviewRepo.save(new Review("Forrest Gump", "review content", drama));
		Review unbreakable = reviewRepo.save(new Review("Unbreakable", "review content", drama));
		
		entityManager.flush(); 
		entityManager.clear();
		
		Collection<Review> reviewsForCategory = reviewRepo.findByCategoriesId(categoryId);
		assertThat(reviewsForCategory, containsInAnyOrder(forrestGump, unbreakable));		
		
	}
	
	

}
