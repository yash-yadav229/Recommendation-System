
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        ThirdRatings sr= new ThirdRatings("data/ratings.csv");
        System.out.println("Total number of raters : " + sr.getRaterSize());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Total number of movies : " + MovieDatabase.size());
        
        int MinNumOfRatings=35;
        ArrayList<Rating> averageRatings = sr.getAverageRatings(MinNumOfRatings);
        Collections.sort(averageRatings);
        for(Rating rating :averageRatings){
            String title=MovieDatabase.getTitle(rating.getItem());
            System.out.println(rating.getValue() + " " + title);
        }
        System.out.println("There are " + averageRatings.size() + " movies with " +
        MinNumOfRatings + " or more ratings");
    }
    
    public void printAverageRatingsByDirectors(){
         ThirdRatings sr= new ThirdRatings("data/ratings.csv");
        System.out.println("Total number of raters : " + sr.getRaterSize());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        int year=2010;
        Filter f= new YearAfterFilter(2000);
        System.out.println("Total number of movies : " + MovieDatabase.size());
        
        int MinNumOfRatings=20;
        ArrayList<Rating> averageRatings = sr.getAverageRatingsByFilter(MinNumOfRatings,f);
        Collections.sort(averageRatings);
        for(Rating rating :averageRatings){
            String title=MovieDatabase.getTitle(rating.getItem());
            System.out.println(rating.getValue() + " " + title);
        }
        System.out.println("There are " + averageRatings.size() + " movies with " +
        MinNumOfRatings + " or more ratings");
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings sr= new ThirdRatings("data/ratings.csv");
        System.out.println("Total number of raters : " + sr.getRaterSize());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Total number of movies : " + MovieDatabase.size());
        
        AllFilters af= new AllFilters();
        
        int year=1990;
        Filter f= new YearAfterFilter(year);
        af.addFilter(f);
        
        Filter f1= new GenreFilter("Drama");
        af.addFilter(f1);
        
        int MinNumOfRatings=8;
        ArrayList<Rating> averageRatings = sr.getAverageRatingsByFilter(MinNumOfRatings,af);
        Collections.sort(averageRatings);
        for(Rating rating :averageRatings){
            String title=MovieDatabase.getTitle(rating.getItem());
            System.out.println(rating.getValue() + " " + title + " "+MovieDatabase.getYear(rating.getItem()));
            System.out.println("      "+MovieDatabase.getGenres(rating.getItem()));
        }
        System.out.println("There are " + averageRatings.size() + " movies with " +
        MinNumOfRatings + " or more ratings");
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        ThirdRatings sr= new ThirdRatings("data/ratings.csv");
        System.out.println("Total number of raters : " + sr.getRaterSize());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Total number of movies : " + MovieDatabase.size());
        
        AllFilters af= new AllFilters();
        
        Filter f= new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        af.addFilter(f);
        
        Filter f1= new MinutesFilter(90,180);
        af.addFilter(f1);
        
        int MinNumOfRatings=3;
        ArrayList<Rating> averageRatings = sr.getAverageRatingsByFilter(MinNumOfRatings,af);
        Collections.sort(averageRatings);
        for(Rating rating :averageRatings){
            String title=MovieDatabase.getTitle(rating.getItem());
            System.out.println(rating.getValue() + " " + title + " Minutes:"+MovieDatabase.getMinutes(rating.getItem()));
            System.out.println("      "+MovieDatabase.getDirector(rating.getItem()));
        }
        System.out.println("There are " + averageRatings.size() + " movies with " +
        MinNumOfRatings + " or more ratings");
    }
}
