
/**
 * Encryps string with a caeser cipher, decrypts strings with a caesar cipher
 * 
 * @author (Deontee Hendricks) 
 * 
 * @version (7/15/2020)
 */
public class CaesarCipher {
    
    private int mainKey;
    private String alphabet;
    private String shiftedAlphabet;
    
    public CaesarCipher(int key){
        mainKey = key;
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key)+alphabet.substring(0,key);
    }
    
    String encrypt(String input){
    
        StringBuilder str1 = new StringBuilder();
        
        for(int i=0;i<input.length();i++){
            
            if(Character.isLetter(input.charAt(i))){
                if(Character.isUpperCase(input.charAt(i))){
                    int found = alphabet.indexOf(input.toLowerCase().charAt(i));
                    str1.append(shiftedAlphabet.toUpperCase().charAt(found));
                }
                else{
                    int found = alphabet.indexOf(input.charAt(i));
                    str1.append(shiftedAlphabet.charAt(found));
                }
                
            }
            else{
                str1.append(input.charAt(i));
            }
 
        }
        
        return str1.toString();
    
    }
    
    public String decrypt(String encryptedMessage){
        String alphabet = "abcdepghijklmnopqrstuvwxyz";
        
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        
        return cc.encrypt(encryptedMessage);
    }
}
