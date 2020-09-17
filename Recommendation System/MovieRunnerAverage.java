
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
public class MovieRunnerAverage {
    public void printAverageRatings(){
        SecondRatings sr= new SecondRatings("data/ratedmoviesfull.csv","data/ratings.csv");
         System.out.println("Total number of movies : " + sr.getMovieSize());
        System.out.println("Total number of raters : " + sr.getRaterSize());
        
        int MinNumOfRatings=50;
        ArrayList<Rating> averageRatings = sr.getAverageRatings(MinNumOfRatings);
        Collections.sort(averageRatings);
        for(Rating rating :averageRatings){
            System.out.println(rating.getValue() + " " + sr.getTitle(rating.getItem()));
        }
        System.out.println("There are " + averageRatings.size() + " movies with " +
        MinNumOfRatings + " or more ratings");
    }
    
     public void getAverageRatingOneMovie () {
        SecondRatings secondRatings = new SecondRatings ("data/ratedmoviesfull.csv", "data/ratings.csv");
        
        String title = "The Maze Runner";
        int MinNumOfRatings = 1; 
        
        String movieID = secondRatings.getID(title);
        ArrayList<Rating> averageRatings = secondRatings.getAverageRatings(MinNumOfRatings);
        for (Rating rating : averageRatings) {
            if (rating.getItem().equals(movieID)) {
                System.out.println("For movie \"" + title + "\" the average rating is " 
                + rating.getValue());
            }
        }
    }
}
