
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
import java.io.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        FileResource fr= new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        
        for(CSVRecord record : parser){
            String id= record.get("id");
            String title= record.get("title");
            String year=record.get("year");
            String genres=record.get("genre");
            String director=record.get("director");
            String country=record.get("country");
            String poster=record.get("poster");
            int minutes=Integer.parseInt(record.get("minutes"));
            
            Movie mv= new Movie(id,title,year,genres,director,country,poster,minutes);
            movieList.add(mv);
        }
        
        return movieList;
    }
    
    public ArrayList<Rater> loadRaters (String filename){
        ArrayList<Rater> raterList= new ArrayList<Rater>();
        FileResource fr= new FileResource(filename);
        
        ArrayList<String> ids= new ArrayList<String>();
        
        for(CSVRecord record : fr.getCSVParser()){
            String id=record.get("rater_id");
            String movieId=record.get("movie_id");
            double rating= Double.parseDouble(record.get("rating"));
            if(!ids.contains(id)){
                Rater r= new EfficientRater(id);
                r.addRating(movieId,rating);
                raterList.add(r);
            }
            else{
                for(int i=0;i<raterList.size();i++){
                    if(raterList.get(i).getID().equals(id)){
                        Rater r1=raterList.get(i);
                        r1.addRating(movieId,rating);                        
                    }
                }
            }
            ids.add(id);
        }
        
        return raterList;
    }
    
    public void testLoadRaters(){
        ArrayList<Rater> raterList= loadRaters("data/ratings.csv");
        
        System.out.println("There are total of "+ raterList.size()+" raters");
        for(int i=0;i<raterList.size();i++){
            Rater currRater=raterList.get(i);
            String currId= currRater.getID();
            int numRatings= currRater.numRatings();
            System.out.println("Rater ID: "+currId+" ; no. of Ratings: "+numRatings);
            /*
            ArrayList<String> itemRated= currRater.getItemsRated();
            for(int j=0; j<itemRated.size();j++){
                System.out.println("MovieId : " +itemRated.get(j)+" Rating: "+ currRater.getRating(itemRated.get(j)));
            }
            */
        }
        
        String raterId="193";
        for(int i=0;i<raterList.size();i++){
            if(raterList.get(i).getID().equals(raterId)){
                System.out.println("The rater with  raterId "+raterId+" rated "+raterList.get(i).getItemsRated().size()+" movies");
            }
        }
        
        int maxRatings=0;
        for(int i=0;i<raterList.size();i++){
            if(raterList.get(i).getItemsRated().size()>maxRatings){
                maxRatings=raterList.get(i).getItemsRated().size();
            }
        }
        System.out.println("Max no, Ratings are "+maxRatings+". And the Raters are:");
        for(int i=0;i<raterList.size();i++){
            if(raterList.get(i).getItemsRated().size()==maxRatings){
                System.out.println(raterList.get(i).getID());
            }
        }
        
        String movieId="1798709";
        int count=0;
        for(int i=0;i<raterList.size();i++){
            Rater r=raterList.get(i);
            if(r.hasRating(movieId)){
                count+=1;
            }
        }
        System.out.println("Movie with "+movieId+" has "+count+" ratings.");
        
        ArrayList<String> movieList= new ArrayList<String>();
        for(int i=0;i<raterList.size();i++){
            Rater r=raterList.get(i);
            ArrayList<String> itemsRated = r.getItemsRated();
            for(int j=0;j<itemsRated.size();j++){
                if(!movieList.contains(itemsRated.get(j))){
                    movieList.add(itemsRated.get(j));
                }
            }
        }
        System.out.println("Total movies rated are "+ movieList.size());
    }
    
    public void testLoadMovies(){
        ArrayList<Movie> movieList= loadMovies("data/ratedmoviesfull.csv");
        System.out.println("There are total of "+ movieList.size()+" movies");
        
        for(int i=0;i<movieList.size();i++){
            System.out.println(movieList.get(i));
        }
        
        int comedyCount=0;
        for(int i =0;i<movieList.size();i++){
            if(movieList.get(i).getGenres().contains("Comedy")){
                comedyCount+=1;
            }
        }
        System.out.println("Movies of Comedy Genre are " +comedyCount);
        
        int minutesCount=0;
        for(int i =0;i<movieList.size();i++){
            if(movieList.get(i).getMinutes()> 150){
                minutesCount+=1;
            }
        }
        System.out.println("Movies greater than 150 Minutes are " +minutesCount);
        
        HashMap<String,ArrayList<Movie>> map = new HashMap<String,ArrayList<Movie>>();
        for(int i =0;i<movieList.size();i++){
            String director=movieList.get(i).getDirector();
            director=director.trim();
            
            if(!map.containsKey(director)){
                map.put(director,new ArrayList<Movie>());                    
            }
                
            ArrayList<Movie> ar=map.get(director);
            ar.add(movieList.get(i));
            map.put(director,ar);
                
            
        }
        
        String dirMax="";
        int maxSize=0;
        
        for(ArrayList<Movie> ar : map.values()){
            
            if( ar.size() > maxSize ){
                dirMax=ar.get(0).getDirector();
                maxSize=ar.size();
               
            }
             
        }
        System.out.println("The Director with most movies is "+dirMax+" with "+ maxSize+" movies");
       
    }

}
