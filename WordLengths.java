
/**
 * Counts the amount of words of each length in a file
 * 
 * @author (Deontee Hendricks) 
 * @version (1.0)
 */
import edu.duke.*;

public class WordLengths {
    
    public void countWordLengths(FileResource resource, int [] counts){
        
        int length = 0;
        for(String s : resource.words()){
            length = s.length();
            
            if(!Character.isLetter(s.charAt(0))){
                length--;
            }
            if(!Character.isLetter(s.charAt(s.length()-1))){
                length--;
            }
            if(length>=counts.length-1){
                counts[counts.length-1]++;
            }
            else{
                counts[length]++;
            }
        }
    }
    
    public void testCountWordLengths(){
        
        FileResource fr = new FileResource();
        int [] counts = new int [31];
        
        countWordLengths(fr, counts);
        
        for(int i=0; i<counts.length;i++){
            System.out.println(i+" : "+counts[i]);
        }
    }
}
