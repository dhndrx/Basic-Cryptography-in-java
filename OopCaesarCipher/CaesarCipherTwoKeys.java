
/**
 * Encrypts string with a caeser cipher using two keys, 
 * decrypts strings with a caesar cipher using two keys,
 * 
 * @author (Deontee Hendricks) 
 * @version (7/16/2020)
 */
 
public class CaesarCipherTwoKeys {
    
    private String alphabet, shiftedAlphabetOne, shiftedAlphabetTwo;
    private int mainKeyOne, mainKeyTwo;
    
    public CaesarCipherTwoKeys(int keyOne, int keyTwo){
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabetOne = alphabet.substring(keyOne)+alphabet.substring(0,keyOne);
        shiftedAlphabetTwo = alphabet.substring(keyTwo)+alphabet.substring(0,keyTwo);
        mainKeyOne = keyOne; 
        mainKeyTwo = keyTwo;
    }
    
    public String encrypt(String input){
        
        StringBuilder str1 = new StringBuilder();
        
        for(int i=0;i<input.length();i++){
            
            if(Character.isLetter(input.charAt(i))){
                if(Character.isUpperCase(input.charAt(i))){
                    int found = alphabet.indexOf(input.toLowerCase().charAt(i));
                    if(i%2==0){
                        str1.append(shiftedAlphabetOne.toUpperCase().charAt(found));
                    }
                    else{
                        str1.append(shiftedAlphabetTwo.toUpperCase().charAt(found));
                    }
                    
                }
                else{
                    int found = alphabet.indexOf(input.charAt(i));
                    if(i%2==0){
                        str1.append(shiftedAlphabetOne.charAt(found));
                    }
                    else{
                        str1.append(shiftedAlphabetTwo.charAt(found));
                    }
                    
                }
                
            }
            else{
                str1.append(input.charAt(i));
            }
 
        }
        
        return str1.toString();
    }
    
    public String decrypt(String encryptedMessage){
    
        CaesarCipherTwoKeys cc = new CaesarCipherTwoKeys(26-mainKeyOne,26-mainKeyTwo);
        
        return cc.encrypt(encryptedMessage);
    
    }
    
}
