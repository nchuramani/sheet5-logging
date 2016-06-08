package org.com.sheet5.source;
/**
 * @author nikhilchuramani
 *
 */
public class LogRecord {
	
	private int LSN;
	private int transID;
	private int pageID;
	private String loginfo;

	public LogRecord(){
		
	}

	public int getLSN() {
		return LSN;
	}

	public void setLSN(int lSN) {
		LSN = lSN;
	}

	public int getTransID() {
		return transID;
	}

	public void setTransID(int transID) {
		this.transID = transID;
	}

	public int getPageID() {
		return pageID;
	}

	public void setPageID(int pageID) {
		this.pageID = pageID;
	}

	public String getLoginfo() {
		return loginfo;
	}

	public void setLoginfo(String logInfo) {
		this.loginfo = logInfo;
	}

}
