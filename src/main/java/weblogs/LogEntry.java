package weblogs;

import  java.util.*;

public class LogEntry {

    private String ipAddress;
    private Date accessTime;
    private String request;
    private int statusCode;
    private int bytesReturned;

    public LogEntry(){
        ipAddress = "";
        accessTime = null;
        request = "";
        statusCode = 404;
        bytesReturned = 0;
    }

    public LogEntry(String ipAddress, Date date, String request, int status, int sizeBytes){
        this.ipAddress = ipAddress;
        this.accessTime = date;
        this.request = request;
        this.statusCode = status;
        this.bytesReturned = bytesReturned;
    }

    public String getIpAddress(){
        return ipAddress;
    }

    public Date getAccessTime(){
        return accessTime;
    }

    public String getRequest(){
        return request;
    }

    public int getStatusCode(){
        return statusCode;
    }

    public int getBytesReturned(){
        return bytesReturned;
    }

    public String toString(){
        return "ipAddress: "+ipAddress+" accessTime: "+accessTime+" request: "+request+" statusCode: "+statusCode+" bytesReturned: "+bytesReturned;
    }
    public static void main(String[] args)
    {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
}
