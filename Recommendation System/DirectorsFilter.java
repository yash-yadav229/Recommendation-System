
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    
    private String dir;
    
    public DirectorsFilter(String directors){
        dir= directors;
    }
    
    public boolean satisfies(String id){
        String director= MovieDatabase.getDirector(id);
        if(dir.contains(director)){
            return true;
        }
        return false;
    }

}
