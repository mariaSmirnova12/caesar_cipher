package caesarcipher;

public class CaesarCipherTwoKeys {
    
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int key1;
    private int key2;
    
    public CaesarCipherTwoKeys(int key1, int key2){
        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.shiftedAlphabet1 = (alphabet.substring(key1)+alphabet.substring(0,key1));
        this.shiftedAlphabet2 = (alphabet.substring(key2)+alphabet.substring(0,key2));
        this.key1 = key1;
        this.key2 = key2;
    }
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        boolean isLowerCase = false;
        for(int i=0; i < encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            if(Character.isAlphabetic(currChar)){
               if(Character.isLowerCase(currChar)){
                    currChar = Character.toUpperCase(currChar);
                    isLowerCase = true;
                }
                int idx = alphabet.indexOf(currChar);
                if(idx!=-1){
                    char newChar;//'';
                    if(i%2==0){
                        newChar = shiftedAlphabet1.charAt(idx);
                    }
                    else{
                        newChar = shiftedAlphabet2.charAt(idx);
                    }
                    if(isLowerCase){
                       newChar = Character.toLowerCase(newChar);
                    }
                    encrypted.setCharAt(i, newChar);
                }
            }
            isLowerCase = false;
        }
        return encrypted.toString();
    }
    public String decrypt(String input){
        CaesarCipherTwoKeys cc = new CaesarCipherTwoKeys(alphabet.length() - key1,alphabet.length() - key2);
        return cc.encrypt(input);
    }
}
