package my.simple.movie.rating.service.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.simple.movie.rating.service.models.MovieRating;

@RestController
@RequestMapping("/movierating")
public class MovieRatingResource {
	@GetMapping("/{movieID}")
	public MovieRating getMovieRating(@PathVariable String movieID) {
		return new MovieRating(movieID, 3);
	}

	/*
	 * Problem of returning the list as Rest APIs output as We need some
	 * parameterizedTypeReference cascading in RestTemplate call so We are creating
	 * a wrapper class for the list so the below code changes as follows
	 */

	/*
	 * @GetMapping("user/{userID}") public List<MovieRating>
	 * getMovieUserRating(@PathVariable String userID) { List<MovieRating>
	 * movieRatingList = Arrays.asList(new MovieRating("Zotopia", 2), new
	 * MovieRating("Migrating Birds", 5)); return movieRatingList; }
	 */

	@GetMapping("user/{userID}")
	public MovieRatingList getMovieUserRating(@PathVariable String userID) {
		MovieRatingList ratingList = new MovieRatingList();
		List<MovieRating> movieRatingList = Arrays.asList(new MovieRating("11", 10), new MovieRating("100", 10));
		ratingList.setMovieRatingList(movieRatingList);
		return ratingList;
	}

	public class MovieRatingList {
		List<MovieRating> MovieRatingList;

		public List<MovieRating> getMovieRatingList() {
			return MovieRatingList;
		}

		public void setMovieRatingList(List<MovieRating> movieRatingList) {
			MovieRatingList = movieRatingList;
		}
	}

}
