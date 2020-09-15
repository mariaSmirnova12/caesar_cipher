import caesarcipher.CaesarCipherTwoKeys;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CaesarCipherTwoKeysTester {

    @Test
    public void testCipher1() {
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipherTwoKeys cc = new CaesarCipherTwoKeys(21, 8);
        String output = cc.encrypt(input);
        System.out.println("encrypted string is "+output);
        String decrypted = cc.decrypt(output);
        System.out.println("decrypted string is "+decrypted);
        assertEquals("route is correct", decrypted, input);
    }

    @Test
    public void testCipher2() {
        String input = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        CaesarCipherTwoKeys cc = new CaesarCipherTwoKeys(14, 24);
        String decrypted = cc.decrypt(input);
        System.out.println("decrypted string is "+decrypted);
        assertEquals("route is correct", decrypted, "The original name of Java was Oak.");
    }

}
