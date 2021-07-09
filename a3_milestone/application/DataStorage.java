/**
 * this File contains the DataStorage class to run the program
 */
package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
/**
 * creates objects to store data
 * @author Yibin Huang
 */
public class DataStorage {
	HashMap<String,Farm> dataStorage;
	private int size;
	/**
	 * default constructor to create DataStorage
	 */
	public DataStorage() {
		dataStorage = new HashMap();
	}

	/**
	 * Insert data into DataStorage
	 * @param farmID
	 * @param farm
	 */
	public void insert(String farmID, Farm farm) {
		dataStorage.put(farmID, farm);
		++size;
	}

	/**
	 * remove data from dataStorage
	 * @param farmID
	 * @param farm
	 * @return
	 */
	public boolean remove(String farmID, Farm farm) {
		if (contains(farmID)) {
			dataStorage.remove(farmID, farm);
			--size;
			return true;
		}
		return false;
	}
	/**
	 * check if dataStorage contains data
	 * @param farmID
	 * @return
	 */
	public boolean contains(String farmID) {
		return dataStorage.containsKey(farmID);
	}
	/**
	 * get data from dataStorage
	 * @param farmID
	 * @return
	 */
	public Farm get(String farmID) {
		return dataStorage.get(farmID);
	}
	/**
	 * get the total weight of the month
	 * @param month
	 * @param year
	 * @return
	 */
	public int getTotalMonthWeight(int month, int year) {
		int totalWeight = 0;
		for(Entry<String, Farm> entry : dataStorage.entrySet()) {
			totalWeight += entry.getValue().getMonthWeight(month , year);
		}
		return totalWeight;
	}
	
	/**
	 * get the total weight of the year
	 * @param year
	 * @return
	 */
	public int getTotalYearWeight(int year) {
		int totalWeight = 0;
		for(Entry<String, Farm> entry : dataStorage.entrySet()) {
			totalWeight += entry.getValue().getYearWeight(year);
		}
		return totalWeight;
	}
	/**
	 * get the size
	 * @return
	 */
	public int getSize() {
		return size;
	}
	
	
	
	
	
	
	
	
	
}
