package my.simple.movie.info.service.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import my.simple.movie.info.service.models.MovieDBSummary;
import my.simple.movie.info.service.models.MovieInfo;

@RestController
@RequestMapping("/movieinfo")
public class MovieInfoResourse {

	@Value("${movieDB.api.key}")
	public String moviedb_api_key;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/test")
	public String justTest() {
		return "Yes MovieInfo Resource working";
	}

	@GetMapping("/{movieID}")
	public MovieInfo getMovieInfo(@PathVariable String movieID) {
		String moviedb_URl = "https://api.themoviedb.org/3/movie/" + movieID + "?api_key=" + moviedb_api_key;
//		String moviedb_URl = "https://api.themoviedb.org/3/movie/" + 11 + "?api_key=" + moviedb_api_key;
		System.out.println(moviedb_URl);
		MovieDBSummary movieSummary = restTemplate.getForObject(
				moviedb_URl,
				MovieDBSummary.class);
		return new MovieInfo(movieSummary.getTitle(), movieSummary.getOverview());
//		before implement movieDB api
//		return new MovieInfo(movieID, "Zotopia is children movie");
	}

}
