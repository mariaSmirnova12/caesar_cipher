package caesarcipher;

public class CaesarBreaker {
    char mostCommon;
    
    public CaesarBreaker() {
        mostCommon = 'e';
    }
    
    public CaesarBreaker(char c) {
        mostCommon = c;
    }
    
    public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k=0; k < message.length(); k++){
            int dex = alph.indexOf(Character.toLowerCase(message.charAt(k)));
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k=0; k < vals.length; k++){
            if (vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }

    public int getKey(String encrypted){
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int mostCommonPos = mostCommon - 'a';
        int dkey = maxDex - mostCommonPos;
        if (maxDex < mostCommonPos) {
            dkey = 26 - (mostCommonPos-maxDex);
        }
        return dkey;
    }
    
    public String decrypt(String encrypted){
        int key = getKey(encrypted);
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(encrypted);
        
    }

    public String halfOfString(String message, int start){
        StringBuilder halfStr = new StringBuilder();
        for(int i=start; i < message.length(); i+=2){
            halfStr.append(message.charAt(i));
        }
        return halfStr.toString();
    }

    public String breakCaesarCipherTwoKeys(String input){
        String str1 = halfOfString(input, 0);
        String str2 = halfOfString(input, 1);
        int key1 = getKey(str1);
        int key2 = getKey(str2);
        CaesarCipherTwoKeys cc = new CaesarCipherTwoKeys(key1,key2);
        String result = cc.decrypt(input);
        System.out.println(result);
        return result;
    }
   
}
