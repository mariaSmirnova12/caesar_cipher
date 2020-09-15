package vigenere;

import caesarcipher.CaesarBreaker;
import java.io.*;
import java.util.*;

public class VigenereBreaker {
    private static String dataSourceDirectory = "dictionaries";

    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for(int i = whichSlice; i<message.length(); i+=totalSlices){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for(int i = 0; i<klength; i++){
          String str = sliceString(encrypted, i, klength);
          CaesarBreaker cc = new CaesarBreaker(mostCommon);
          key[i] = cc.getKey(str);
        }
        return key;
    }
    public HashMap <String, HashSet<String>> readLanguage(String fileName, HashMap <String, HashSet<String>> languages){ 
        System.out.println("start reading "+fileName);
        HashSet <String> set = readDictionary(dataSourceDirectory+"/"+fileName);
        languages.put(fileName, set);
        System.out.println("finish reading "+fileName);
        return languages;
    }
    public String breakVigenere (String message) {
        HashMap <String, HashSet<String>> languages = new HashMap <String, HashSet<String>>();
        //Danish, Dutch, English, French, German, Italian, Portuguese, and Spanish
        languages = readLanguage("Danish", languages);
        languages = readLanguage("Dutch", languages);
        languages = readLanguage("English", languages);
        languages = readLanguage("French", languages);
        languages = readLanguage("German", languages);
        languages = readLanguage("Italian", languages);
        languages = readLanguage("Portuguese", languages);
        languages = readLanguage("Spanish", languages);
        String res = breakForAllLangs(message, languages);
        return res.substring(0, 100);
    }
    public HashSet readDictionary (String source) {
        HashSet <String> set = new HashSet();
        FileInputStream stream = null;
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            File fileName = new File(classLoader.getResource( source).getFile());
            stream = new FileInputStream(fileName);
            BufferedReader reader = null;
            String nextLine;
            reader = new BufferedReader(new InputStreamReader(stream));
            while ((nextLine = reader.readLine()) != null) {
                set.add(nextLine.toLowerCase());

            }
        } catch (IOException e) {
            System.err.println("Problem looking for dictionary file: " + source);
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return set;
    }
    public int countWords(String message, HashSet <String> dictionary){
        int amount = 0;
        for(String word: message.split("\\W+")){
            if(dictionary.contains(word.toLowerCase())){
                amount++;
            }
        }
        return amount;
    }
    public String breakForLanguage(String encrypted, HashSet <String> dictionary, char mostCommon){
        int[] keys;
        int[] keysRight = {};
        int tmpAmount = 0;
        int maxAmount = 0;
        String res = "";
        int length = 0;
        for(int i = 1; i < 100; i++){
            keys = tryKeyLength(encrypted, i, mostCommon); 
            VigenereCipher cc = new VigenereCipher(keys);
            String answer = cc.decrypt(encrypted);
            tmpAmount = countWords(answer, dictionary);
            if(tmpAmount > maxAmount){
                maxAmount = tmpAmount;
                res = answer;
                length = i;
                keysRight = keys;
            }
        }
        System.out.println("words - "+maxAmount+ " length - "+length);
        return res;
    }
    public char mostCommonCharIn(HashSet <String> dictionary){
        ArrayList list = new ArrayList();
        char symbol = 'a';
        HashMap <Character, Integer> map = new HashMap <Character, Integer>();
        for(String word: dictionary){
            for(Character ch : word.toCharArray()){
                if(map.containsKey(Character.toLowerCase(ch))){
                    map.put(ch, map.get(ch)+1);
                }
                else
                    map.put(ch, 1);
            }
        }
        int maxOccur = 0;
        for(char ch: map.keySet()){
            if(map.get(ch) > maxOccur){
                maxOccur = map.get(ch);
                symbol = ch;
            }
        }
        return symbol;
    }
    public String breakForAllLangs(String encrypted, HashMap <String, HashSet<String>> languages){
        String langResult = "";
        String messageResult = "";
        int counts = 0;
        for(String lang: languages.keySet()){
            HashSet<String> set = languages.get(lang);
            char ch = mostCommonCharIn(set);
            String answer = breakForLanguage(encrypted, set, ch);
            int tmpAmount = countWords(answer, set);
            System.out.println("language - "+ lang+" str - "+answer.substring(0, 60)+ " amount - "+tmpAmount);
            if(tmpAmount > counts){
                counts = tmpAmount;
                langResult = lang;
                messageResult = answer;
            }
        }
        System.out.println("language - "+ langResult+" str - "+messageResult.substring(0, 60)+ " amount - "+counts);
        return messageResult;
    }
}
