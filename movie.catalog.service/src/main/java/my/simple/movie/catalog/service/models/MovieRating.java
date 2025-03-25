package my.simple.movie.catalog.service.models;

public class MovieRating {
	private String movieID;
	private int movieRating;
		
	public MovieRating(String movieID, int movieRating) {
		super();
		this.movieID = movieID;
		this.movieRating = movieRating;
	}
	public String getMovieID() {
		return movieID;
	}
	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}
	public int getMovieRating() {
		return movieRating;
	}
	public void setMovieRating(int movieRating) {
		this.movieRating = movieRating;
	}
	
	
}
