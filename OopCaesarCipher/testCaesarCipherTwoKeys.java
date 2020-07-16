
/**
 * Test the CaesarCipherTwoKeys class
 * 
 * @author (Deontee Hendricks) 
 * @version (7/16/2020)
 */

import edu.duke.*;

public class TestCaesarCipherTwoKeys {
    
    private String halfOfString(String message, int start){
        
        StringBuilder halfMessage = new StringBuilder();
        
        for(int i=start;i<message.length();i+=2){
            halfMessage.append(message.charAt(i));
        }
        
        return halfMessage.toString();
    }
    
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
    
    private int getKey(String s){
        
        int [] freqs = countLetters(s);
        int mfl = indexOfMax(freqs);
        
        int popularLetterIndex = 4;
        if(mfl<popularLetterIndex){
            return 26-(popularLetterIndex-mfl);
        }
        else{
            return mfl-popularLetterIndex;
        }
    }
    
    public void simpleTests(){
    
        FileResource fr = new FileResource();
        String message = fr.asString();
        
        System.out.println(message);
        
        CaesarCipherTwoKeys cc = new CaesarCipherTwoKeys(14,24);
        
        String encryptedMessage = cc.encrypt(message);
        
        System.out.println(encryptedMessage);
        System.out.println(cc.decrypt(encryptedMessage));
        System.out.println(breakCaesarTwoKeys(message));
        

    }
    
    public String breakCaesarTwoKeys(String encryptedMessage){
    
        String encrMessOne = halfOfString(encryptedMessage,0);
        String encrMessTwo = halfOfString(encryptedMessage,1);
       
        int decryptKeyOne = getKey(encrMessOne);
        int decryptKeyTwo = getKey(encrMessTwo);
        
        System.out.println("Key 1: "+decryptKeyOne+"\nKey 2: "+decryptKeyTwo);
        
        CaesarCipherTwoKeys cc = new CaesarCipherTwoKeys(26-decryptKeyOne, 26-decryptKeyTwo);
        
        return cc.encrypt(encryptedMessage); 
       
    }
}
