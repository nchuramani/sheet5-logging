package org.com.sheet5.source;
/**
 * @author nikhilchuramani
 *
 */
public class SynchronizedCounter {
	
	private int transID=0;
	
	public synchronized void increment(){
		transID++;
	}
	public synchronized void decrement(){
		transID--;
	}
	public synchronized int getTransID(){
		return transID;
	}
	public synchronized void setTransID(int transID){
		this.transID = transID;
	}
}
