package org.com.sheet5.source;
/**
 * @author nikhilchuramani
 *
 */
public class DataBufferRecord {

	private int LSN;
	
	private int transID;
	
	private int pageID;
	
	private String data;
	
	private boolean commitFlag;
	
	

	public DataBufferRecord(int lsn, int transID, int pageID, String data, boolean commitFlag) {
		super();
		if (lsn > 0){
			this.LSN = lsn;	
		}
		else{
			System.out.println("LSN not > 0");
			throw new RuntimeException();
		}
		if (transID > 0){
			this.transID = transID;	
		}
		else{
			System.out.println("transID not > 0");
			throw new RuntimeException();
		}
		if (pageID >=0){
			this.pageID = pageID;	
		}
		else{
			System.out.println("PageID not >= 0");
			throw new RuntimeException();
		}
		if (data != null){
			this.data = data;	
		}
		else{
			System.out.println("Data is empty");
			throw new RuntimeException();
		}
		this.commitFlag = commitFlag;
	}

	public int getLSN() {
		return this.LSN;
	}

	public void setLSN(int lSN) {
		this.LSN = lSN;
	}

	public int getTransID() {
		return this.transID;
	}

	public void setTransID(int transID) {
		this.transID = transID;
	}

	public int getPageID() {
		return this.pageID;
	}

	public void setPageID(int pageID) {
		this.pageID = pageID;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public boolean isCommitFlag() {
		return this.commitFlag;
	}

	public void setCommitFlag(boolean commitFlag) {
		this.commitFlag = commitFlag;
	}
	
	
}
