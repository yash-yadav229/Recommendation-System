
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings {
    
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        FirstRatings fr= new FirstRatings();
        myRaters=fr.loadRaters(ratingsfile);
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters){
        double avgRating=0.0;
        double totalRating=0.0;
        int count=0;
        for(int i=0;i<myRaters.size();i++){
            Rater currRater=myRaters.get(i);
            if(currRater.hasRating(id)){
                totalRating+=currRater.getRating(id);
                count+=1;
            }
        }
        
        if(count>=minimalRaters){
            avgRating=totalRating/count;
        }
        
        return avgRating;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> rl=new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(int i=0;i<movies.size();i++){
            double currRating =getAverageByID(movies.get(i),minimalRaters);
            Rating m= new Rating(movies.get(i),currRating);
            if(currRating!=0.0){
               rl.add(m);
            }
        }
        return rl;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> avgRatingListByFilter = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String id:movies){
            double avg = getAverageByID(id, minimalRaters);
            if(avg>0.0){
                Rating currRating = new Rating(id,avg);
                avgRatingListByFilter.add(currRating);
            }
        }
        return avgRatingListByFilter;
    }

}
