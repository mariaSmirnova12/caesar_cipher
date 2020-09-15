package caesarcipher;

public class CaesarCipherBreaker {

    String alphabet;
    char mostCommon;

    public CaesarCipherBreaker(){
        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        mostCommon = 'E';
    }

    public CaesarCipherBreaker(char c){
        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        mostCommon = Character.toUpperCase(c);
    }

    public int[] countLetters(String message){
        int[] lettersOccur = new int[alphabet.length()];
        for(int i = 0; i<message.length(); i++){
            char ch = message.charAt(i);
            int ind = alphabet.indexOf(Character.toUpperCase(ch));
            if(ind != -1){
                lettersOccur[ind]++;
            }
        }
        return lettersOccur;
    }

    public int indexOfMax(int[] values){
        int maxOccur = 0;
        int indOccur = 0;
        for(int i = 0; i < values.length; i++){
            if(values[i]>=maxOccur){
                maxOccur = values[i];
                indOccur = i;
            }
        }
        return indOccur;
    }

    public int getKey(String message){
        int idx = alphabet.indexOf(mostCommon);
        System.out.println("position of the most common is "+idx);
        int[] lettersOccur = countLetters(message);
        int indOccur = indexOfMax(lettersOccur);
        System.out.println(alphabet.charAt(indOccur)+" position is "+indOccur);
        int key = indOccur - idx;
        if(indOccur<idx){
            key = 26-(idx-indOccur);
        }
        System.out.println("key probably is = "+ key);
        return key;
    }

    public String breakCaesarCipher(String input){
        int key = getKey(input);
        CaesarCipher cc = new CaesarCipher(key);
        String result = cc.decrypt(input);
        System.out.println(result);
        return result;
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
