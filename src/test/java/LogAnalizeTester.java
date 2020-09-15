import org.junit.Before;
import org.junit.Test;
import weblogs.LogAnalyzer;
import weblogs.LogEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class LogAnalizeTester {

    LogAnalyzer analyzer;

    @Before
    public void setUp()
    {
        analyzer = new LogAnalyzer();
    }

    @Test
    public void testLogAnalyzer() {
        analyzer.readFile("logs/short-test_log");
        analyzer.printLogs();
        assertEquals("logs array size", analyzer.getLogRecords().size(), 7);
    }

    @Test
    public void testUniqueIps() {
        analyzer.readFile("logs/weblog2_log");
        System.out.println("amount of unique addresses: "+analyzer.uniqueIPAddresses());
        assertEquals("amount of unique addresses ", analyzer.uniqueIPAddresses(), 45);
    }

    @Test
    public void testAllHigherThanNum() {
        analyzer.readFile("logs/weblog1_log");
        int num = 400;
        System.out.println("all logs with status > "+num);
        ArrayList <LogEntry> res = analyzer.getAllHigherThanNum(num);
        assertEquals("amount of entries with  status more than "+ num, analyzer.getAllHigherThanNum(num).size(), 61);
    }

    @Test
    public void uniqueIPVisitsOnDay(){
        analyzer.readFile("logs/weblog2_log");
        analyzer.printLogs();
        String day = "Sep 27";
        ArrayList <String> res = analyzer.uniqueIPVisitsOnDay(day);
        System.out.println("ipaddresses for "+ day+" "+res.size());
        for(String ip: res){
            System.out.println("ip: "+ip);
        }
        assertEquals("amount of ip addresses for "+ day, res.size(), 8);

    }

    @Test
    public void countUniqueIPsInRange(){
        analyzer.readFile("logs/weblog2_log");
        int low = 200;
        int high = 299;
        assertEquals("amount of ip addresses between "+ low+ " and "+high, analyzer.countUniqueIPsInRange(low, high), 40);
    }

    @Test
    public void countVisitsPerIP(){
        analyzer.readFile("logs/short-test_log");
        HashMap<String, Integer> res = analyzer.countVisitsPerIP();
        System.out.println(res);
        int s = res.get("152.3.135.44");
        assertEquals("key 152.3.135.44 ", s, 3);
        s = res.get("152.3.135.63");
        assertEquals("key 152.3.135.63 ", s, 2);
        s = res.get("110.76.104.12");
        assertEquals("key 110.76.104.12 ", s, 1);

    }

    @Test
    public void mostNumberVisitsByIP(){
        analyzer.readFile("logs/weblog2_log");
        HashMap<String, Integer> res = analyzer.countVisitsPerIP();
        int amount = analyzer.mostNumberVisitsByIP(res);
        System.out.println("max amount is "+amount);
        assertEquals("max number visited by ip ", amount, 63);
    }

    @Test
    public void iPsMostVisits(){
        analyzer.readFile("logs/weblog2_log");
        HashMap<String, Integer> res = analyzer.countVisitsPerIP();
        ArrayList<String> str = analyzer.iPsMostVisits(res);
        System.out.println(str);
        assertEquals("ip visited max number ", str.size(), 1);
    }

    @Test
    public void iPsForDays(){
        analyzer.readFile("logs/weblog3-short_log");
        HashMap<String, ArrayList<String>> res = analyzer.iPsForDays();
        System.out.println(res);
        assertEquals("amount of visited ips ", res.size(), 3);
        assertEquals("amount of visited ips ", res.get("Sep 21"), Arrays.asList("84.189.158.117", "61.15.121.171", "84.133.195.161", "84.133.195.161"));
        assertEquals("amount of visited ips ", res.get("Sep 30"), Arrays.asList("84.189.158.117", "61.15.121.171", "61.15.121.171", "177.4.40.87", "177.4.40.87"));
   }

    @Test
    public void dayWithMostIPVisits(){
        analyzer.readFile("logs/weblog2_log");
        HashMap<String, ArrayList<String>> res = analyzer.iPsForDays();
        System.out.println("day is"+analyzer.dayWithMostIPVisits(res));
        assertEquals("amount of visited ips ", analyzer.dayWithMostIPVisits(res), "Sep 24");
    }

    @Test
    public void iPsWithMostVisitsOnDay(){
        analyzer.readFile("logs/weblog2_log");
        HashMap<String, ArrayList<String>> res = analyzer.iPsForDays();
        ArrayList<String> str = analyzer.iPsWithMostVisitsOnDay(res, "Sep 29");
        System.out.println(str);
        assertEquals("amount of visited ips ", str, Arrays.asList("210.4.104.99", "212.128.74.248", "84.134.96.171", "106.220.155.36"));
    }
}
