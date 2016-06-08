package org.com.sheet5.source;

import java.util.*;

public class DataBuffer {

	private static final int buffer_size = 5;
	public static DataBuffer db;
	ArrayList<DataBufferRecord> bufferList = new ArrayList<DataBufferRecord>();
	
	static{
		try{
			db = new DataBuffer();
		}
		catch(Throwable e){
			throw new RuntimeException(e.getMessage());
		}
	}
	public DataBuffer(){
		
	}
	public static DataBuffer getInstance(){
		return db;
	}
	
	
	public void addDataBufRec(DataBufferRecord bufRec){
		bufferList.add(bufRec);
		if(bufferList.size() > buffer_size){
			System.out.println("Writing to DB");
			//Write to Database
			writeToFile();
		}
	}
	public void writeToFile(){
		DataBufferRecord dbr = null;
		ArrayList<DataBufferRecord> dbrlist =null;
		for(int i=0;i<this.bufferList.size();i++){
			if(this.bufferList.get(i).isCommitFlag() == true){
				dbr = this.bufferList.get(i);
				if(dbr != null){
					dbrlist = this.getRecordsByTransID(dbr.getTransID());
					DataFileManager dfm = DataFileManager.getInstance();
					for(int j=0;j<dbrlist.size();j++){
						if(dbrlist.get(j).getPageID()!=0){
							dfm.writeRecord(dbrlist.get(j).getPageID(), dbrlist.get(j).getLSN(), dbrlist.get(j).getData());
						}
					}
			}
			else{
				continue;
			}
		}
		
		}
		
	}
	public ArrayList<DataBufferRecord> getRecordsByTransID(int transID){
		ArrayList<DataBufferRecord> dbrlist = new ArrayList<DataBufferRecord>();
		for(int i=0;i<this.bufferList.size();i++){
			if(this.bufferList.get(i).getTransID() == transID){
				dbrlist.add(this.bufferList.get(i));
				this.bufferList.remove(i);
				i--;
			}
		}
		return dbrlist;
	}
}
