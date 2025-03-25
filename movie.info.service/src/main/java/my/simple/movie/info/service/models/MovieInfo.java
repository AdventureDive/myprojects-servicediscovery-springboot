package my.simple.movie.info.service.models;

public class MovieInfo {
	 
	private String movieID;
	private String movieDescription;
	
	
	public MovieInfo(String movieID, String movieName) {
		super();
		this.movieID = movieID;
		this.movieDescription = movieName;
	}
	public String getMovieID() {
		return movieID;
	}
	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}
	public String getMovieDescription() {
		return movieDescription;
	}
	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}
	 

}
