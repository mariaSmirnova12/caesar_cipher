package gladlibs;

import java.io.*;
import java.util.*;

public class GladLib {

    private HashMap<String, ArrayList<String>> map;

    private ArrayList<String> usedWordsList;
    private ArrayList<String> categoriesList;
    private Random myRandom;
    private static String dataSourceDirectory = "data";

    public GladLib() {
        map = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedWordsList = new ArrayList<String>();
        categoriesList = new ArrayList<String>();
    }

    private void initializeFromSource(String source) {
        String[] labels = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit", "number"};
        for (String label : labels) {
            String res = source+"/"+label + ".txt";
            ArrayList<String> list = readIt(res);
            map.put(label, list);
        }
    }

    private String randomFrom(String word) {
        if (map.containsKey(word)) {
            ArrayList<String> names = map.get(word);
            int index = myRandom.nextInt(names.size());
            if (!categoriesList.contains(word))
                categoriesList.add(word);
            return names.get(index);
        }
        return "**UNKNOWN**";
    }

    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1) {
            return w;
        }
        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);
        String sub = randomFrom(w.substring(first + 1, last));
        if (!usedWordsList.contains(sub))
            usedWordsList.add(sub);
        return prefix + sub + suffix;
    }

    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for (String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source) {
        String story = "";

        Scanner sc2 = null;
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            File fileName = new File(classLoader.getResource( source).getFile());
            sc2 = new Scanner(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc2.hasNextLine()) {
            Scanner s2 = new Scanner(sc2.nextLine());
            while (s2.hasNext()) {
                String word = s2.next();
                System.out.println(word);
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        FileInputStream stream = null;
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            File fileName = new File(classLoader.getResource( source).getFile());
            stream = new FileInputStream(fileName);
            BufferedReader reader = null;
            String nextLine;
            reader = new BufferedReader(new InputStreamReader(stream));
            while ((nextLine = reader.readLine()) != null) {
                list.add(nextLine);

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
        return list;
    }

    public int totalWordsInMap() {
        int count = 0;
        for (String s : map.keySet()) {
            ArrayList<String> names = map.get(s);
            count += names.size();
        }
        return count;
    }

    public int totalWordsConsidered() {
        int count = 0;
        for (String s : map.keySet()) {
            if (categoriesList.contains(s)) {
                 ArrayList<String> names = map.get(s);
               count += names.size();
            }
        }
        return count;
    }

    public void makeStory() {
        String story = fromTemplate(dataSourceDirectory+"/"+"madtemplate.txt");
        printOut(story, 60);
        System.out.println("total words: " + totalWordsInMap());
        System.out.println("used words: " + usedWordsList.size());
        for (String word : usedWordsList)
            System.out.println(word);

        System.out.println("categoriesList: ");
        for (String word : categoriesList)
            System.out.println(word);
        System.out.println("considered words: " + totalWordsConsidered());

    }

    public static void main(String[] args) {
        GladLib gl = new GladLib();
        gl.makeStory();
    }
}
