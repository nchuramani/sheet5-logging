package org.com.sheet5.source;

public class DataRecord {

	private int pageID;
	private int LSN;
	private String data;

	public DataRecord(){
		
	}

	public int getLSN() {
		return LSN;
	}

	public void setLSN(int lSN) {
		LSN = lSN;
	}

	public int getPageID() {
		return pageID;
	}

	public void setPageID(int pageID) {
		this.pageID = pageID;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	

}
