package caesarcipher;

public class  CaesarCipher{

    private String alphabet;
    private String shiftedAlphabet;
    private int key;

    public CaesarCipher(int key){
        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.shiftedAlphabet = (alphabet.substring(key)+alphabet.substring(0,key));
        this.key = key;
    }
    
    public String encrypt(String input){
       System.out.println("start encrypt");
       StringBuilder encrypted = new StringBuilder(input);
        for(int i=0; i < encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            if(Character.isAlphabetic(currChar)){
                char newChar = transformLetter(currChar, alphabet, shiftedAlphabet);
                encrypted.setCharAt(i, newChar);
            }
        }
        System.out.println("finish encrypt");
        return encrypted.toString();
    }

    private char transformLetter(char c, String from, String to) {
        boolean isLowerCase = false;
        if(Character.isLowerCase(c)){
            c = Character.toUpperCase(c);
            isLowerCase = true;
        }

        int idx = from.indexOf(c);
        if (idx != -1) {
            if(isLowerCase){
                return Character.toLowerCase(to.charAt(idx));
            }
            return to.charAt(idx);
        }
        return c;
    }
    public char encryptLetter(char c) {
        return transformLetter(c, alphabet, shiftedAlphabet);
    }

    public char decryptLetter(char c) {
        return transformLetter(c, shiftedAlphabet, alphabet);
    }

    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(alphabet.length() - key); 
        return cc.encrypt(input);
    }
}
