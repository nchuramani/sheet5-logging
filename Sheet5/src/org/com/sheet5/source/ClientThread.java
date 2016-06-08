package org.com.sheet5.source;
/**
 * @author nikhilchuramani
 *
 */
public class ClientThread extends Thread {
	
	private int pageIDStart;
	private int runTime;
	private String threadName;
	private int transID;
	
	public ClientThread(int pageIDStart, int runtime, String name) {
		this.pageIDStart = pageIDStart;
		this.runTime = runtime;
		this.threadName = name;
		this.setTransID(PersistenceManager.getInstance().beingTransaction());
		System.out.println("Client TransactionID: "+this.getTransID());

	}

	public void run(){
		int pageIDEnd = this.getPageIDStart() + 9;
		System.out.println("Client Thread: "+this.getThreadName());

		try{
			for(int pageID = this.getPageIDStart(); pageID <= pageIDEnd; pageID++){
				Thread.sleep(this.getRunTime());
				PersistenceManager.getInstance().write(transID, pageID, "Trans"+this.getTransID()+"Trans"+pageID+"Data");				
			}
			PersistenceManager.getInstance().commit(transID);
		}
		catch(InterruptedException e){
			System.out.println("ClientThread: "+this.getThreadName()+" interrupted!");
		}
	}

	public int getPageIDStart() {
		return pageIDStart;
	}

	public void setPageIDStart(int pageIDStart) {
		this.pageIDStart = pageIDStart;
	}

	public int getRunTime() {
		return runTime;
	}

	public void setRunTime(int runTime) {
		this.runTime = runTime;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public int getTransID() {
		return transID;
	}

	public void setTransID(int transID) {
		this.transID = transID;
	}
	
	
}
