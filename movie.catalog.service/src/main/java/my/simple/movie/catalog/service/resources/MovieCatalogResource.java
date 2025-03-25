package my.simple.movie.catalog.service.resources;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import my.simple.movie.catalog.service.models.MovieCatalog;
import my.simple.movie.catalog.service.models.MovieInfo;
import my.simple.movie.catalog.service.models.MovieRatingList;

@RestController
@RequestMapping("/moviecatalog")
@Service
public class MovieCatalogResource{
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/{userID}")
	@HystrixCommand(fallbackMethod = "getCatalogFallback")
	public List<MovieCatalog> getCatalog(@PathVariable String userID){
		
//	**********	Using RestTemplate
		/*
		 * MovieInfo movieInfo =
		 * restTemplate.getForObject("http://localhost:8082/movieinfo/foo",
		 * MovieInfo.class);
		 * 
		 * System.out.println("MovieInfo: "+ movieInfo.getMovieDescription());
		 */
//		List<MovieRating> movieRatingList = Arrays.asList(new MovieRating("Zotopia", 2), new MovieRating("Migrating Birds", 5));
		MovieRatingList movieRatingList = restTemplate.getForObject("http://movie.rating.service/movierating/user/"+userID, MovieRatingList.class);
		
		System.out.println("MovieID at 1: "+ movieRatingList.getMovieRatingList().get(1).getMovieID()+
				"  "+"MovieRating at 1: "+ movieRatingList.getMovieRatingList().get(1).getMovieRating());

		
//	**********	Using WebClient
/*
 * here in bodyToMono represents that Asynchronous way of getting object.since it follows React web package 
 * MovieInfo movieInfo; try {
 * 
 * movieInfo =
 * (WebClientCustomizer.create().get().uri("http://localhost:8082/movieinfo/foo"
 * ).retrieve().bodyToMono(MovieInfo.class)).subscribe(System.out::println);
 * System.out.println("MOvieInfo: "+ movieInfo.getMovieDescription()); } catch
 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
 */		
		
//	*********	Using RestClient
/*
 * MovieInfo movieInfo; MovieRatingList movieRatingList; try {
 * 
 * movieInfo =
 * (RestClient.create().get().uri("http://localhost:8082/movieinfo/foo").
 * retrieve().toEntity(MovieInfo.class)).getBody();
 * System.out.println("MovieInfo: "+ movieInfo.getMovieDescription());
 * } catch (Exception e) { // TODO Auto-generated catch block
 * e.printStackTrace(); }
 */

		return movieRatingList.getMovieRatingList().stream().map(movieRatingIte -> new MovieCatalog(movieRatingIte.getMovieID(), 
				(restTemplate.getForObject("http://movie.info.service/movieinfo/"+movieRatingIte.getMovieID(), MovieInfo.class)).getMovieDescription(), 
				movieRatingIte.getMovieRating())).collect(Collectors.toList());
		
		
		
//		return movieRatingList.stream().map(movieCatalog -> new MovieCatalog("Zotopia" ,"Childerns Movie", 5)).collect(Collectors.toList());
		
		
//		return Collections.singletonList(new MovieCatalog("Zotopia" ,"Childerns Movie", 5));
	}

//	it wont be called When you change the signature or return type of the method
	public List<MovieCatalog> getCatalogFallback(@PathVariable String userID){
		return Arrays.asList(new MovieCatalog("100", "FallBack", 0));
	}
	
    @GetMapping("/hystrixService")
    @HystrixCommand(fallbackMethod = "reliable")
    public String hystrixService() {
        if (new Random().nextBoolean()) {
		    throw new RuntimeException("Service Failure!");
		}
		return "Successfully called service!";        
        
    }
    
    @HystrixCommand(fallbackMethod = "reliable")
    public String callService() throws InterruptedException {
        if (new Random().nextBoolean()) {
            throw new RuntimeException("Service Failure!");
        }
        return "Successfully called service!";
    }

    public String reliable() {
        return "Default Response";
    }

}
