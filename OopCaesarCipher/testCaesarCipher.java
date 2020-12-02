import edu.duke.*;

public class TestCaesarCipher {
    private int indexOfMax(int []values){
        
        int found = 0, max=0;
        
        for(int i=0;i<values.length;i++){
            if(max<values[i]){
                max = values[i];
                found = i;
            }
        }
        
        return found;
    }
    
    private int [] countLetters(String message){
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
    
    public void simpleTests(){
        
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println(message);
        
        CaesarCipher cc = new CaesarCipher(18);

        String encryptedMessage = cc.encrypt(message);
        System.out.println(encryptedMessage);
        System.out.println(breakCaesarCipher(encryptedMessage));
    }
    
    public String breakCaesarCipher(String encryptedMessage){
        String alphabet = "abcdepghijklmnopqrstuvwxyz";
        
        int [] freqs = countLetters(encryptedMessage);
        int found = indexOfMax(freqs);
        int decryptKey = found - 4;// difference between most frequent letter in encprypted 
                                   // message and the letter 'e'(most popular letter). this gives us the shift need to decrypt.
        if(found < 4){
            decryptKey = 26-(4-found);
        }
        
        CaesarCipher cc = new CaesarCipher(decryptKey);
        
        return cc.decrypt(encryptedMessage);
    }
}
