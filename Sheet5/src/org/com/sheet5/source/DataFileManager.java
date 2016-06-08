package org.com.sheet5.source;
/**
 * @author nikhilchuramani
 *
 */
import java.io.*;


public class DataFileManager {
	
	static private DataFileManager DFM;
	
	static{
		try{
			DFM = new DataFileManager();
		}
		catch(Throwable e){
			new RuntimeException(e.getMessage());
		}
	}
	public DataFileManager(){
		
	}
	public static DataFileManager getInstance(){
		return DFM;
	}

	public void writeRecord(int pageID, int LSN, String data){
		try{
			FileWriter fw = new FileWriter("dataFile.txt",true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(pageID+" "+LSN+" "+data);
			
			System.out.println("Record Written: " + pageID+" "+LSN+" "+data);
			bw.newLine();
			bw.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public DataRecord readRecord(int pageID){
		DataRecord dr = new DataRecord();
		Boolean flag = true;
		try{
			FileReader fr = new FileReader("dataFile.txt");
			BufferedReader br = new BufferedReader(fr);
			while(flag){
				String dataRecord = br.readLine();
			
				String datarecData[] = dataRecord.split(" ");
				if(Integer.parseInt(datarecData[0]) == pageID){
					dr.setPageID(pageID);
					dr.setLSN(Integer.parseInt(datarecData[1]));
					dr.setData(datarecData[2]);
					flag=false;
				}
				else{
					flag=true;
					continue;
				}
			}
			br.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return dr;
	}
}
