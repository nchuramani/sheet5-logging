package org.com.sheet5.source;
/**
 * @author nikhilchuramani
 *
 */
import java.io.*;
import java.util.*;

public class PersistenceManager {
	
	static private PersistenceManager pm;
	LogSequenceNumber lsn = new LogSequenceNumber();
	SynchronizedCounter transID = new SynchronizedCounter();
	LogRecord logRec = new LogRecord();
	
	static{
		try{
			pm = new PersistenceManager();
		}
		catch(Throwable e){
			throw new RuntimeException(e.getMessage());
		}
	}
	public PersistenceManager(){
		recovery();
	}
	public int beingTransaction(){
		lsn.increment();
		System.out.println("LSN: "+lsn.getLSN());
		transID.increment();
		System.out.println("TransID: "+transID.getTransID());
		logRec.setTransID(transID.getTransID());
		logRec.setLSN(lsn.getLSN());
		logRec.setPageID(0);
		logRec.setLoginfo("TransactionBegin");
		LogWriter.getInstance().makeEntry(logRec);
		return transID.getTransID();
	}
	public void commit(int transID){
		lsn.increment();
		logRec.setLSN(lsn.getLSN());
		logRec.setTransID(transID);
		logRec.setPageID(0);
		logRec.setLoginfo("TransactionCommit");

		//Making Log Entry		
		
		LogWriter.getInstance().makeEntry(logRec);
		
		//Add to Database Buffer
		DataBufferRecord bufRec = new DataBufferRecord(lsn.getLSN(), transID, 0, "CommitData", true);
		DataBuffer.getInstance().addDataBufRec(bufRec);
		
	}
	public void write(int transID, int pageID, String data){
		lsn.increment();
		logRec.setLSN(lsn.getLSN());
		logRec.setTransID(transID);
		logRec.setPageID(pageID);
		logRec.setLoginfo(data);
		
		//Making Log Entry
		LogWriter.getInstance().makeEntry(logRec);

		//Add to Database Buffer
		DataBufferRecord bufRec = new DataBufferRecord(lsn.getLSN(), transID, pageID, data, false);
		DataBuffer.getInstance().addDataBufRec(bufRec);
	}
	static public PersistenceManager getInstance(){
		return pm;
	}
	
	public void recovery(){
		List<LogRecord> loglist = LogWriter.getInstance().readLog();
		ArrayList<Integer> redoTransactions = new ArrayList<Integer>();
		
		for(int i=0;i<loglist.size();i++){
			if(loglist.get(i).getLoginfo().contains("commit")){
				redoTransactions.add(loglist.get(i).getTransID());
			}
		}
		LogRecord lr;
		for(int i=0;i<loglist.size();i++){
			lr = loglist.get(i);
			if(lr.getPageID()!=0){
				if(redoTransactions.contains(lr.getTransID())){
					if(DataFileManager.getInstance().readRecord(lr.getPageID()).getLSN() < lr.getLSN()){
						DataFileManager.getInstance().writeRecord(lr.getPageID(), lr.getLSN(), lr.getLoginfo());
					}
				}
			}
		}
		if(loglist.size()!=0){
			lr = loglist.get(loglist.size()-1);
			lsn.setLSN(lr.getLSN());
			transID.setTransID(lr.getTransID());
		}
		else{
			lsn.setLSN(0);
			transID.setTransID(0);
		}
	}

}