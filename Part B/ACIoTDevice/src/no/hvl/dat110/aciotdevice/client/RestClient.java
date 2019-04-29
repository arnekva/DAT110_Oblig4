package no.hvl.dat110.aciotdevice.client;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class RestClient {

	public RestClient() {
		// TODO Auto-generated constructor stub
	}
	
	private static final String host = "localhost";
	private static final int port = 8080;
	private static String logpath = "/accessdevice/log";

	public void doPostAccessEntry(String message) {

		try (Socket s = new Socket(host,port)) {
			Gson gson = new Gson();
			//Create the HTTPreq
			
		String jsonbody = gson.toJson(new AccessMessage(message));
		
		String httpUTrequest = 
				"Post " + logpath + "HTTP/1.1\r\n" + "Host: " + host + "\r\n" + 
		"Content-type: application/json\r\n" + "Content-length: " + jsonbody.length() +
		"\r\n" + "Connection: close\r\n" + "\r\n" + jsonbody + "\r\n"; 		}
		
		//send the response over TCP
		
		OutputStream output = s.getOutputStream();
		PrintWriter pw = new PrintWriter(output, false);
		pw.print(httpputrequest);
		pw.flush();
		
		//read the response
		InputStream in = s.getInputStream();
		
		Scanner reader = new Scanner(in);
		StringBuilder jsonresponse = new StringBuilder();
		boolean header = true;
		
		while(reader.hasNext()) {
			String nextline = reader.nextLine();
			if(header) {
				System.out.println(nextline);
			}else{
				jsonresponse.append(nextline); }
			}
		
		if (nextline.isEmpty()) {
			header = false;
		}
		
	}
	
	private static String codepath = "/accessdevice/code";
	
	public AccessCode doGetAccessCode() {

		AccessCode code = null;
		
		// TODO: implement a HTTP GET on the service to get current access code
		try (Socket s = new Socket(host,port)) {
			//construct GET
			 String httpgetrequest = "GET " + codepath + " HTTP/1.1\r\n" + "Accept: application/json\r\n"
					 + "Host: localhost\r\n" + "Connection: close\r\n" + "\r\n";
			 
			 //send request
			 OutputStream output = s.getOutputStream();
			 
			 PrintWriter pw = new PrintWriter(output, false) ;
			 
			 pw.print(httpgetrequest);
			 pw.flush();
			 
			 // read response
			 InputStream in = s.getInputStream();
			 
			 Scanner reader = new Scanner(in);
			 StringBuilder jsonresponse = new StringBuilder();
			 boolean header = true;
			 
			 while(scan.hasNext())  {
				 String nextline = scan.nextLine();
				 
				 if (!header) {
					 jsonresponse.append(nextline);
				 }
				 
				 if (nextline.isEmpty()) {
					 header = false;
				 }
			 }
		}
		return code;
	}
}
