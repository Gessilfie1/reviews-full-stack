package com.reviewsfullstack.jpa;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReviewPopulator implements CommandLineRunner {

	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private CategoryRepository categoryRepo;

	@Override
	public void run(String... args) throws Exception {

		// Categories
		Category drama = new Category("Drama");
		drama = categoryRepo.save(drama);
		Category comedy = new Category("Comedy");
		comedy = categoryRepo.save(comedy);
		Category action = new Category("Action");
		action = categoryRepo.save(action);
		Category family = new Category("Family");
		family = categoryRepo.save(family);
		Category thriller = new Category("Thriller");
		thriller = categoryRepo.save(thriller);

		// Movies and their Reviews

		Review forrestGump = new Review("Forrest Gump", "Forrest Gump Review Content Here", "Images//fgump.jpg",
				comedy);
		forrestGump = reviewRepo.save(forrestGump);

		Review unbreakable = new Review("Unbreakable", "Unbreakable Review Content Here", "Images//unbreakable.jpg",
				thriller);
		unbreakable = reviewRepo.save(unbreakable);

		Review toyStory = new Review("Toy Story", "Toy Story Review Content Here", "Images//toystory.jpg", family);
		toyStory = reviewRepo.save(toyStory);

		Review dieHard = new Review("Die Hard", "Die Hard Review Content Here", "Images//diehard.jpg", action);
		dieHard = reviewRepo.save(dieHard);

		Review theMatrix = new Review("The Matrix", "The Matrix Review Content Here", "Images//matrix.jpg", action);
		theMatrix = reviewRepo.save(theMatrix);

		Review theDeparted = new Review("The Departed", "The Departed Review Content Here", "Images//departed.jpg",
				thriller);
		theDeparted = reviewRepo.save(theDeparted);

	}

}
