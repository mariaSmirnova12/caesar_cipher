import caesarcipher.CaesarCipher;
import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

public class CaesarCipherTester {

    public String readMessageFromFile(String name) throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File fileName = new File(classLoader.getResource( name).getFile());
        FileInputStream fis = new FileInputStream(fileName);
        byte[] data = new byte[(int) fileName.length()];
        fis.read(data);
        fis.close();
        String str = new String(data, "UTF-8");
        return str;
    }

    @Test
    public void testCaesarCipherFromFile() {
        String fileName = "titus-small.txt";
        try {
            String message = readMessageFromFile(fileName);
            System.out.println("message: "+message);
            int key = 6;
            CaesarCipher cc = new CaesarCipher(key);
            String encryptedMessage = cc.encrypt(message);
            System.out.println("encrypted message: "+encryptedMessage);
            String decrMessage = cc.decrypt(encryptedMessage);
            System.out.println("decrypted message: "+decrMessage);
            assertEquals("route is correct", decrMessage, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCipher1() {
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipher cc = new CaesarCipher(15);
        String output = cc.encrypt(input);
        System.out.println("encrypted string is "+output);
        String decrypted = cc.decrypt(output);
        System.out.println("decrypted string is "+decrypted);
        assertEquals("route is correct", output, "Rpc ndj xbpvxct axut LXIWDJI iwt xcitgcti PCS rdbejitgh xc ndjg edrzti?");
        assertEquals("route is correct", decrypted, input);
    }

    @Test
    public void testCipher2() {
        String input = "FREE CAKE in CONFERENCE ROOM";
        CaesarCipher cc = new CaesarCipher(23);
        String output = cc.encrypt(input);
        System.out.println("encrypted string is "+output);
        String decrypted = cc.decrypt(output);
        System.out.println("decrypted string is "+decrypted);
        assertEquals("route is correct", output, "COBB ZXHB fk ZLKCBOBKZB OLLJ");
        assertEquals("route is correct", decrypted, input);
    }
}
