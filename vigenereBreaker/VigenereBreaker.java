import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
 
        StringBuilder sb = new StringBuilder();
        
        for(int i=whichSlice;i<message.length();i+=totalSlices){
            sb.append(message.charAt(i));
        }
        
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        
        for(int i=0;i<klength;i++){
            String s = sliceString(encrypted,i,klength);
            CaesarCracker ccr = new CaesarCracker();
            key[i]= ccr.getKey(s);
        }
        return key;
    }

    public void breakVigenere () {
        
        FileResource fr = new FileResource();
        
        String s = fr.asString();
        
        DirectoryResource dictFiles = new DirectoryResource();
        
        HashMap <String, HashSet<String>> langToDict = new HashMap <String, HashSet<String>>(); 
        
        for(File f : dictFiles.selectedFiles()){
            FileResource langFile = new FileResource(f);
            System.out.println("Starting "+f.getName()+"...");
            HashSet<String> set = readDictionary(langFile);
            langToDict.put(f.getName(),set);
        }
        System.out.println("\nDone loading dictionaries");
        
        breakForAllLangs(s,langToDict);        
    }
    
    public HashSet<String> readDictionary(FileResource fr){
    
        HashSet<String> set = new HashSet<String>();
        
        for(String s : fr.lines()){
            String newS = s.toLowerCase();
            set.add(newS);
        }
        
        return set;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
    
        String [] words = message.split("\\W+");
        
        int count = 0;
        
        for(String s : words){
            
            if(dictionary.contains(s.toLowerCase())){
                count++;
            }
        
        }
        
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
    
        int maxCount = 0;
        
        String correctS = "";
        
        for(int i=1;i<=100;i++){
            
            char c = mostCommonCharIn(dictionary);
            
            int [] key = tryKeyLength(encrypted,i,c);
            VigenereCipher vc = new VigenereCipher(key);
            String pts = vc.decrypt(encrypted);
            
            int currVal = countWords(pts,dictionary);

            if(maxCount<currVal){
                maxCount = currVal;
                correctS = pts;
                //corrLen = i;
            }
        }
        return correctS;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int [] counts = new int [26];
        
        for(String s : dictionary){
            for(int i=0;i<s.length();i++){
                s = s.toLowerCase();
                char c = s.charAt(i);
                int idx = alpha.indexOf(c);
                if(idx>=0&&idx<=25){
                    counts[idx]++;
                }
            }
        }
        
        int maxIdx = 0, maxVal = 0;
        
        for(int i=0;i<counts.length;i++){
            if(maxVal<counts[i]){
                maxVal = counts[i];
                maxIdx = i;
            }
        }
        
        return alpha.charAt(maxIdx);
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
    
        int currentNumWords = 0, numCorrWords = 0, max = 0;
        String corrLang = "",currentMess = "", corrMess = "";

        for(String s : languages.keySet()){
            System.out.println("Starting Decryption for "+s+"...");
            currentMess = breakForLanguage(encrypted, languages.get(s));
            currentNumWords = countWords(currentMess, languages.get(s));
            if(max<currentNumWords){
                max=currentNumWords;
                corrLang = s;
                corrMess = currentMess;
            }
            System.out.println("Done");
        }
        
        System.out.println(corrMess);
        System.out.println(corrLang);
    }
}

