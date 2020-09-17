
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter{
    
    private int minMinutes;
    private int maxMinutes;
    
    public MinutesFilter(int min, int max){
        minMinutes=min;
        maxMinutes=max;
    }
    
    public boolean satisfies(String id){
        int runTime=MovieDatabase.getMinutes(id);
        if(runTime>= minMinutes && runTime<=maxMinutes){
            return true;
        }
        
        return false;
    }

}
