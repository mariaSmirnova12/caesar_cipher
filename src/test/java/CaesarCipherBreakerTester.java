import caesarcipher.CaesarCipherBreaker;
import org.junit.Test;
import vigenere.CaesarCracker;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CaesarCipherBreakerTester {

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
    public void testCaesarCracker() {
        String fileName = "titus-small_key5.txt";
        String fileNameDecr = "titus-small.txt";
        try {
            String message = readMessageFromFile(fileName);
            System.out.println("message: "+message);
            CaesarCracker ccr = new CaesarCracker();
            String decryptedMessage = ccr.decrypt(message);
            System.out.println("decrypted message: "+decryptedMessage);
            String messageDecr = readMessageFromFile(fileNameDecr);
            assertEquals("decrypted is correct", decryptedMessage, messageDecr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCipherBreak() {
        String input = "aMtmKb mfxmZqmvKM";
        CaesarCipherBreaker cc = new CaesarCipherBreaker();
        String decrypted = cc.breakCaesarCipher(input);
        System.out.println("encrypted string is "+decrypted);
        assertEquals("decrypted message ", decrypted, "sEleCt expeRienCE");
    }

    @Test
    public void testCipherTwoKeysBreak() {
        String input = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        CaesarCipherBreaker cc = new CaesarCipherBreaker();
        String decrypted = cc.breakCaesarCipherTwoKeys(input);
        System.out.println("encrypted string is "+decrypted);
        assertEquals("decrypted is correct", decrypted, "The name of the Java Mascot is Duke. Woeoeee!");
    }

    @Test
    public void testCaesarCrackerPortug() {
        String fileName = "oslusiadas_key17.txt";
        try {
            String message = readMessageFromFile(fileName);
            System.out.println("message: "+message);
            CaesarCracker ccr = new CaesarCracker('a');
            String decrypted = ccr.decrypt(message);
            System.out.println("decrypted message: "+decrypted);
            assertTrue("decrypted in Portuguese", decrypted.contains("da ocidental praia lusitana"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
