import org.junit.Test;
import vigenere.VigenereBreaker;
import vigenere.VigenereCipher;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.*;

public class VigenereCipherBreakerTester {

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
    public void testKeys() {
        String fileName = "athens_keyflute.txt";
        try {
            VigenereBreaker vb = new VigenereBreaker();
            String message = readMessageFromFile(fileName);
            String key = "flute";
            char mostCommon = 'e';
            int[] keys = new int[key.length()];
            keys = vb.tryKeyLength(message, key.length(), mostCommon);
            for(int i=0; i<keys.length; i++){
                System.out.println(keys[i]);
            }
            assertArrayEquals("keys ", keys, new int[]{5, 11, 20, 19, 4});

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testVigenereCipher() {
        String fileName = "titus-small.txt";
        try {
            String message = readMessageFromFile(fileName);
            int[] keys = {17, 14, 12, 4};
            VigenereCipher vc = new VigenereCipher(keys);
            String res = vc.encrypt(message);
            System.out.println("decrypted message: "+res);
            assertTrue("decrypted message contains ", res.contains("Tcmp-pxety mj nikhqv htee mrfhtii tyv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSliceString() {
        VigenereBreaker vb = new VigenereBreaker();
        String res = vb.sliceString("abcdefghijklm", 0, 3);
        System.out.println(res);
        assertEquals("slice ", res, "adgjm");
        res = vb.sliceString("abcdefghijklm", 1, 3);
        assertEquals("slice ", res, "behk");
        System.out.println(res);
        res = vb.sliceString("abcdefghijklm", 2, 3);
        assertEquals("slice ", res, "cfil");
        System.out.println(res);
        res = vb.sliceString("abcdefghijklm", 0, 4);
        assertEquals("slice ", res, "aeim");
        System.out.println(res);
        res = vb.sliceString("abcdefghijklm", 1, 4);
        assertEquals("slice ", res, "bfj");
        System.out.println(res);
        res = vb.sliceString("abcdefghijklm", 2, 4);
        assertEquals("slice ", res, "cgk");
        System.out.println(res);
        res = vb.sliceString("abcdefghijklm", 3, 4);
        assertEquals("slice ", res, "dhl");
        System.out.println(res);
        res = vb.sliceString("abcdefghijklm", 0, 5);
        assertEquals("slice ", res, "afk");
        System.out.println(res);
        res = vb.sliceString("abcdefghijklm", 1, 5);
        assertEquals("slice ", res, "bgl");
        System.out.println(res);
        res = vb.sliceString("abcdefghijklm", 2, 5);
        assertEquals("slice ", res, "chm");
        System.out.println(res);
        res = vb.sliceString("abcdefghijklm", 3, 5);
        assertEquals("slice ", res, "di");
        System.out.println(res);
        res = vb.sliceString("abcdefghijklm", 4, 5);
        assertEquals("slice ", res, "ej");
        System.out.println(res);
    }

    @Test
    public void testBreakVigenere() {
        String fileName = "athens_keyflute.txt";
        try {
            String message = readMessageFromFile(fileName);
            VigenereBreaker vb = new VigenereBreaker();
            String res = vb.breakVigenere(message);
            System.out.println(res);
            assertTrue("decrypted message contains ", res.contains("SCENE II. Athens. QUINCE'S house."));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBreakVigenere2() {
        String fileName = "messages/secretmessage2.txt";
        try {
            VigenereBreaker vb = new VigenereBreaker();
            String message = readMessageFromFile(fileName);
            String res = vb.breakVigenere(message);
            System.out.println(res);
            assertTrue("decrypted message contains ", res.contains("The Tragedy of Hamlet, Prince of Denmark"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMostCommonCharIn() {
        VigenereBreaker vb = new VigenereBreaker();
        HashMap<String, HashSet<String>> languages = new HashMap <String, HashSet<String>>();
        languages = vb.readLanguage("English", languages);
        languages = vb.readLanguage("Portuguese", languages);
        HashSet <String> dictionary = languages.get("English");
        char ch = vb.mostCommonCharIn(dictionary);
        System.out.println("ch - "+ ch);
        assertEquals("decrypted message contains ", ch, 'e');
        dictionary = languages.get("Portuguese");
        ch = vb.mostCommonCharIn(dictionary);
        System.out.println("ch - "+ ch);
        assertEquals("decrypted message contains ", ch, 'a');
    }
}
