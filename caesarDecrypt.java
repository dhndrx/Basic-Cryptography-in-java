
/**
 * 
 * 
 * @author (Deontee Hendricks) 
 * @version (7/13/2020)
 */

public class CaesarDecrypt {
    
    int indexOfMax(int []values){
        
        int found = 0, max=0;
        
        for(int i=0;i<values.length;i++){
            if(max<values[i]){
                max = values[i];
                found = i;
            }
        }
        
        return found;
    }
    
    public int [] countLetters(String message){
        String alphabet = "abcdepghijklmnopqrstuvwxyz";
        
        int []counts = new int [26];
        
        for(int i=0;i<message.length();i++){
            
            if(Character.isLetter(message.charAt(i))){
                int found = alphabet.indexOf(message.toLowerCase().charAt(i));
                if(found!=-1){
                    counts[found]++;
                }
            }
            
        }
    
        return counts;
    }
    
    public void decrypt(){
        String alphabet = "abcdepghijklmnopqrstuvwxyz";
        String message = "ghrqwhh";
        int [] freqs = countLetters(message);
        int found = indexOfMax(freqs);
        int decryptKey = found - 4;// difference between most frequent letter in encprypted 
                                   // message and the letter 'e'(most popular letter). this gives us the shift need to decrypt.
        if(found < 4){
            decryptKey = 26-(4-found);
        }
        char ch = alphabet.charAt(found);
        
        CaesarCipher cc = new CaesarCipher();
        
        System.out.println(cc.encrypt(message,26-decryptKey));
        
        
    }

}
