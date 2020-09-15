package weblogs;

import java.io.*;
import java.util.*;

public class LogAnalyzer {

    private ArrayList<LogEntry> records;

    public LogAnalyzer(){
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String name){
        FileInputStream stream = null;
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            File fileName = new File(classLoader.getResource(name).getFile());
            stream = new FileInputStream(fileName);
            BufferedReader reader = null;
            String nextLine;
            reader = new BufferedReader(new InputStreamReader(stream));
            while ((nextLine = reader.readLine()) != null) {
                WebLogParser parser = new WebLogParser();
                LogEntry log = parser.parseEntry(nextLine);
                records.add(log);
            }
        }
        catch (IOException e) {
            System.err.println("Problem looking for dictionary file: " + name);
            e.printStackTrace();
        }
        finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void printLogs(){
        for(LogEntry le: records){
            System.out.println(le);
        }
    }

    public ArrayList <LogEntry> getLogRecords(){
        return records;
    }

    public int uniqueIPAddresses(){
        ArrayList <String> ips = new ArrayList <String>();
        for(LogEntry le: records){
            if(!ips.contains(le.getIpAddress())){
                ips.add(le.getIpAddress());
            }
        }
        return ips.size();
    }
    public ArrayList <LogEntry> getAllHigherThanNum(int num){
        ArrayList <LogEntry> res = new ArrayList <LogEntry>();
        for(LogEntry le: records){
            if(le.getStatusCode()>num){
                res.add(le);
                System.out.println(le);
            }
        }
        return res;
    }
    public ArrayList <String> uniqueIPVisitsOnDay(String someday){
        ArrayList <String> res = new ArrayList <String>();
        for(LogEntry le: records){
            if(le.getAccessTime().toString().contains(someday)){
                if(!res.contains(le.getIpAddress())){
                    res.add(le.getIpAddress());
                }
            }
        }
        return res;
    }
    public int countUniqueIPsInRange(int low, int high){
        ArrayList <String> res = new ArrayList <String>();

        for(LogEntry le: records){
            int status = le.getStatusCode();
            if(status>=low && status<=high){
                //System.out.println(le);
                if(!res.contains(le.getIpAddress())){
                    res.add(le.getIpAddress());
                }
            }
        }
        return res.size();
    }
    public HashMap<String, Integer> countVisitsPerIP(){
        HashMap<String, Integer> visits = new HashMap<String, Integer>();
        for(LogEntry le: records){
            String ip = le.getIpAddress();
            if(visits.containsKey(ip)){
                visits.put(ip, visits.get(ip)+1);
            }
            else{
                visits.put(ip, 1);
            }
        }
        return visits;
    }
    public int mostNumberVisitsByIP(HashMap<String, Integer> map){
        int curAmount = 0;
        int maxAmount = 0;
        for(String a: map.keySet()){
            curAmount = map.get(a);
            if(curAmount > maxAmount){
                maxAmount = curAmount;
            }
        }
        return maxAmount;
    }
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map){
        ArrayList<String> res = new ArrayList<String>();
        int amount = mostNumberVisitsByIP(map);
        System.out.println("max amount is "+amount);
        for(String a: map.keySet()){
            if(map.get(a) == amount){
                res.add(a);
            }
        }
        return res;
    }
    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> res = new HashMap<String, ArrayList<String>>();
        for(LogEntry le: records){
            String time = le.getAccessTime().toString().substring(4, 10);
            //System.out.println("time: "+ time);

            String ip = le.getIpAddress();
            if(res.containsKey(time)){
                ArrayList<String> ips = res.get(time);
                ips.add(ip);
                res.put(time, ips);
            }
            else{
                ArrayList<String> ips = new ArrayList<String>();
                ips.add(ip);
                res.put(time, ips);
            }
        }
        return res;
    }
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map){
        int maxAmount = 0;
        int size = 0;
        String day = "";
        for(String time: map.keySet()){
            size = map.get(time).size();
            if(size > maxAmount){
                maxAmount = size;
                day = time;
            }
        }
        return day;
    }
    public  ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day){
        ArrayList<String> ips = new  ArrayList<String>();
        if(map.containsKey(day)){
            ArrayList<String> list =map.get(day);
            for(String ip: list){
                if(!ips.contains(ip)){
                    ips.add(ip);
                }
            }
        }
        return ips;
    }

    public static void main(String[] args)
    {

    }
}