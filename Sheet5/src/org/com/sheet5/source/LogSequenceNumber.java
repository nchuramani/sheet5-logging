package org.com.sheet5.source;
/**
 * @author nikhilchuramani
 *
 */
public class LogSequenceNumber {
	private int LSN=0;
	
	public synchronized void increment(){
		LSN++;
	}
	public synchronized void decrement(){
		LSN--;
	}
	public synchronized int getLSN(){
		return LSN;
	}
	public synchronized void setLSN(int lsn){
		this.LSN = lsn;
	}
}
