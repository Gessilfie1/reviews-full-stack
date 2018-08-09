package com.reviewsfullstack.jpa;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;







public class ReviewControllerTest {
	
	@InjectMocks
	private ReviewController underTest;
	

	
	@Mock
	private Review review;
	
	@Mock
	private Category category;
	
	@Mock
	private Review anotherReview;
	
	@Mock
	private Category anotherCategory;
	
	@Mock
	private ReviewRepository reviewRepo;
	
	@Mock
	private CategoryRepository categoryRepo;
	
	@Mock
	private Model model;
	
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleReviewToModel() throws ReviewNotFoundException {
		long arbitraryReviewId = 1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		
		underTest.findOneReview(arbitraryReviewId, model);
		verify(model).addAttribute("review", review);	
	}
	
	@Test
	public void shouldAddAllReviewsToModel() {
		Collection<Review> allReviews = Arrays.asList(review, anotherReview);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		
		underTest.findAllReviews(model);
		verify(model).addAttribute("reviews", allReviews);
	}
	
	
	@Test
	public void shouldAddsingleCategoryToModel() throws CategoryNotFoundException {
		
		long arbitraryCategoryId = 1;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		
		underTest.findOneCategory(arbitraryCategoryId, model);
		verify(model).addAttribute("category", category);		
	}
	
	@Test
	public void shouldAddAllCategoriesToModel() {
		Collection<Category> allCategories = Arrays.asList(category, anotherCategory);
		when(categoryRepo.findAll()).thenReturn(allCategories);
		
		underTest.findAllCategories(model);
		verify(model).addAttribute("categories", allCategories);
	}
	
	@Test
	public void shouldAddAdditionalReviewsToModel() {
		String categoryName = "category name";
		when(categoryRepo.findByName(categoryName)).thenReturn(Optional.of(category));
		//Category newCategory = categoryRepo.findByName(categoryName).get();
		
		String reviewTitle= "new review";
		String reviewContent = "new review content";
		String imageUrl = "new review image";
		underTest.addReview(reviewTitle, reviewContent, imageUrl, categoryName);
		//Review newReview = new Review(reviewTitle, reviewContent, imageUrl, newCategory);
		//when(reviewRepo.save(newReview)).thenReturn(newReview);
		verify(reviewRepo).save(Mockito.any(Review.class));
	}
	

}
