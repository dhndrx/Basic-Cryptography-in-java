
/**
 * Encrypts string withs a caesar cipherusing one or 2 keys
 * 
 * @author (Deontee Hendricks) 
 * @version (1.0)
 */
public class CaesarCipher {
    
    String encrypt(String input, int key){
    
        StringBuilder str1 = new StringBuilder();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabet = (alphabet.substring(key)+alphabet.substring(0,key));
        
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
    
    public void testCaesar(){
    
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",15));
    }
    
    String encryptTwoKeys(String input, int key, int keyTwo){
        
        StringBuilder str1 = new StringBuilder();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabetKeyOne = (alphabet.substring(key)+alphabet.substring(0,key));
        String shiftedAlphabetKeyTwo = (alphabet.substring(keyTwo)+alphabet.substring(0,keyTwo));
        
        for(int i=0;i<input.length();i++){
            
            if(Character.isLetter(input.charAt(i))){
                if(Character.isUpperCase(input.charAt(i))){
                    int found = alphabet.indexOf(input.toLowerCase().charAt(i));
                    if(i%2==0){
                        str1.append(shiftedAlphabetKeyOne.toUpperCase().charAt(found));
                    }
                    else{
                        str1.append(shiftedAlphabetKeyTwo.toUpperCase().charAt(found));
                    }
                    
                }
                else{
                    int found = alphabet.indexOf(input.charAt(i));
                    if(i%2==0){
                        str1.append(shiftedAlphabetKeyOne.charAt(found));
                    }
                    else{
                        str1.append(shiftedAlphabetKeyTwo.charAt(found));
                    }
                    
                }
                
            }
            else{
                str1.append(input.charAt(i));
            }
 
        }
        
        return str1.toString();
    }
    
    public void testEncryptTwoKeys(){
    
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",8,21)); 
    
    }
    
}
