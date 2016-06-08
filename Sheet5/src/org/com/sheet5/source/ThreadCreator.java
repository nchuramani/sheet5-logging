package org.com.sheet5.source;
/**
 * @author nikhilchuramani
 *
 */
public class ThreadCreator {

	private int numberOfThreads;
	
	public ThreadCreator(int numberOfThreads){
		this.numberOfThreads = numberOfThreads;
	}
	public void startThread() {
		ClientThread cThreads[] = new ClientThread[this.getNumberOfThreads()];

		for(int i=0;i<this.getNumberOfThreads();i++){
			cThreads[i] = new ClientThread((i+1)*10,(i+1)*2000,"ClientThread"+(i+1));
			cThreads[i].start();
		}
	}
	public int getNumberOfThreads() {
		return numberOfThreads;
	}
	public void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
	}
	

}
