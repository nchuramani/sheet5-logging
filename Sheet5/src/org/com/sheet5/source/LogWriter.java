package org.com.sheet5.source;

/**
 * @author nikhilchuramani
 *
 */

import java.io.*;
import java.util.*;

public class LogWriter {

	static final private LogWriter logWriter;
	
	static{
		try{
			logWriter = new LogWriter();
		}
		catch(Throwable e){
			throw new RuntimeException(e.getMessage());
		}
	}
	
	private LogWriter(){
		
	}
	
	static public LogWriter getInstance(){
		return logWriter;
	}
	
	public void makeEntry(LogRecord logRecord){
		try{
			FileWriter fw = new FileWriter("logFile.txt",true);
			BufferedWriter bw = new BufferedWriter(fw);
			System.out.println("Writing: "+logRecord.getLSN()+" " + logRecord.getTransID() + " " + logRecord.getPageID() + " " + logRecord.getLoginfo());
			bw.write(logRecord.getLSN()+" " + logRecord.getTransID() + " " + logRecord.getPageID() + " " + logRecord.getLoginfo());
			bw.newLine();
			bw.close();
		}
		catch(IOException io){
			io.printStackTrace();
		}
	}
	public List<LogRecord> readLog() {
		List<LogRecord> logs = new ArrayList<LogRecord>();
		String readLine;
		LogRecord log = new LogRecord();
		
		try {
			FileReader fr = new FileReader("logFile.txt");
			BufferedReader br = new BufferedReader(fr);
			
			while ((readLine = br.readLine()) != null ) {
				
				String[] logEntries = readLine.split(" ");
				log.setLSN(Integer.parseInt(logEntries[0]));
				log.setTransID(Integer.parseInt(logEntries[1]));
				log.setPageID(Integer.parseInt(logEntries[2]));
				log.setLoginfo(logEntries[3]);
			
				
				logs.add(log);
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Log entry not found!");
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return logs;
	}
	
}

