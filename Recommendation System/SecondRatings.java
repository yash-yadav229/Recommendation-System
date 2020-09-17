
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile,String ratingsfile){
        FirstRatings fr= new FirstRatings();
        myMovies=fr.loadMovies(moviefile);
        myRaters=fr.loadRaters(ratingsfile);
    }
    
    public int  getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters){
        double avgRating=0.0;
        double totalRating=0;
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
        for(int i=0;i<myMovies.size();i++){
            double currRating =getAverageByID(myMovies.get(i).getID(),minimalRaters);
            Rating m= new Rating(myMovies.get(i).getID(),currRating);
            rl.add(m);
        }
        return rl;
    }
    
    public String getTitle(String id){
        for(int i=0;i<myMovies.size();i++){
            if(myMovies.get(i).getID().equals(id)){
                return myMovies.get(i).getTitle();
            }
        }
        return "ID was not found.";
    }
    
    public String getID(String title){
        for(int i=0;i<myMovies.size();i++){
            if(myMovies.get(i).getTitle().equals(title)){
                return myMovies.get(i).getID();
            }
        }
        return "NO SUCH TITLE.";
    }
}

