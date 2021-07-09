/**
 * This file contains the Farm class to run the program
 */
package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Creates objects to build a Farm
 * @author huangyibin
 *
 */
public class Farm {
	private String id = null;
	private String month = null;
	private int weight = 0;
	private int[] milkWeight;
	private String[] dateArray;
	private double percentage = 0;
	private int index = 0;
	int size;
	
	public Farm() {
		size = 100;
		milkWeight = new int[size];
		dateArray = new String[size];
	}
	/**
	 * construct a new Farm type
	 * @param month
	 * @param weight
	 * @param d
	 */
	public Farm(String month, int weight, double d) {
		this.month = month;
		this.weight = weight;
		this.percentage = d;
	}
	/**
	 * return the month
	 */
	public String getMonth() {
        return month;
    }
	/**
	 * return the milk weight
	 * @return
	 */
	public int getMilkWeight() {
        return weight;
    }
	/**
	 * return the percentage
	 * @return
	 */
	public double getPercentage() {
        return percentage;
    }
	/**
	 * return the id
	 * @return
	 */
	public String getid() {
		return id;
	}
	
	/**
	 * add data to a Farm
	 * @param milk
	 * @param date
	 */
	public void add(int milk, String date) {
		if(index == size) {
			String[] mediumD = new String[size * 2];
			int[] mediumM = new int[size * 2];
			for(int i = 0; i < index; i++) {
				mediumD[i] = dateArray[i];
				mediumM[i] = milkWeight[i];
			}
			dateArray = mediumD;
			milkWeight = mediumM;
			size = size * 2;
		}
		milkWeight[index] = milk;
		dateArray[index] = date;
		index++;
	}

	/**
	 * get the month weight
	 * @param month
	 * @param year
	 * @return
	 */
	public int getMonthWeight(int month, int year) {
		
		int totalWeight = 0;
		
		for (int i = 0; i < index; i++) {
			if(dateArray[i].
					contains("-"+month+"-") 
					&& dateArray[i]
							.contains(""+year)) {
				totalWeight = totalWeight + milkWeight[i];
			}
			else {
				continue;
			}
		}
		return totalWeight;
	}
	/**
	 * get the year weight
	 * @param year
	 * @return
	 */
	public int getYearWeight(int year) {
		int totalWeight = 0;
		for (int i = 0; i < index; i++) {
			if (dateArray[i].contains(Integer.toString(year))) {
					totalWeight = totalWeight + milkWeight[i];
			}
		}
		return totalWeight;
	}
	/**
	 * get the range weight
	 * @param startD
	 * @param endD
	 * @return
	 * @throws ParseException
	 */
	public int getRangeWeight(String startD, String endD) throws ParseException {
		int totalWeight = 0;
		int startIndex = 0;
		int endIndex = 0;
		int count = 0;
		
		String[] SD = startD.split("-");
		String endDate = SD[0] + "-" + endD;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = format.parse(startD);
		Date date2 = format.parse(endDate);
		Date lastDate = format.parse(dateArray[index - 1]);
		String a = startD.substring(0,4);
		
		if (startD.contains("-1-")) {
			Date b = format.parse(a + "-1-" + "31");
			if (date1.compareTo(b) > 0 || date2.compareTo(b) > 0) {
				throw new IllegalArgumentException();
			}
		}
		else if (startD.contains("-3-")) {
			Date b = format.parse(a + "-3-" + "31");
			if (date1.compareTo(b) > 0 || date2.compareTo(b) > 0) {
				throw new IllegalArgumentException();
			}
		}
		else if (startD.contains("-5-")) {
			Date b = format.parse(a + "-5-" + "31");
			if (date1.compareTo(b) > 0 || date2.compareTo(b) > 0) {
				throw new IllegalArgumentException();
			}
		}
		else if (startD.contains("-7-")) {
			Date b = format.parse(a + "-7-" + "31");
			if (date1.compareTo(b) > 0 || date2.compareTo(b) > 0) {
				throw new IllegalArgumentException();
			}
		}
		else if (startD.contains("-8-")) {
			Date b = format.parse(a + "-8-" + "31");
			if (date1.compareTo(b) > 0 || date2.compareTo(b) > 0) {
				throw new IllegalArgumentException();
			}
		}
		else if (startD.contains("-10-")) {
			Date b = format.parse(a + "-10-" + "31");
			if (date1.compareTo(b) > 0 || date2.compareTo(b) > 0) {
				throw new IllegalArgumentException();
			}
		}
		else if (startD.contains("-12-")) {
			Date b = format.parse(a + "-12-" + "31");
			if (date1.compareTo(b) > 0 || date2.compareTo(b) > 0) {
				throw new IllegalArgumentException();
			}
		}
		else if (startD.contains("-4-")) {
			Date b = format.parse(a + "-4-" + "30");
			if (date1.compareTo(b) > 0 || date2.compareTo(b) > 0) {
				throw new IllegalArgumentException();
			}
		}
		else if (startD.contains("-6-")) {
			Date b = format.parse(a + "-6-" + "30");
			if (date1.compareTo(b) > 0 || date2.compareTo(b) > 0) {
				throw new IllegalArgumentException();
			}
		}
		else if (startD.contains("-9-")) {
			Date b = format.parse(a + "-9-" + "30");
			if (date1.compareTo(b) > 0 || date2.compareTo(b) > 0) {
				throw new IllegalArgumentException();
			}
		}
		else if (startD.contains("-9-")) {
			Date b = format.parse(a + "-9-" + "30");
			if (date1.compareTo(b) > 0 || date2.compareTo(b) > 0) {
				throw new IllegalArgumentException();
			}
		}
		else if (startD.contains("-11-")) {
			Date b = format.parse(a + "-11-" + "30");
			if (date1.compareTo(b) > 0 || date2.compareTo(b) > 0) {
				throw new IllegalArgumentException();
			}
		}
		else if (startD.contains("-2-")) {
			Date b;
			if(Integer.parseInt(a) % 4 == 0) {
				b = format.parse(a + "-2-" + "29");
			}
			else {
				b = format.parse(a + "-2" + "28");
			}
			if (date1.compareTo(b) > 0 || date2.compareTo(b) > 0) {
				throw new IllegalArgumentException();
			}
		}
		
		
		for (int i = 0; i < index; i++) {
			Date currentD = format.parse(dateArray[i]);
			if (currentD.compareTo(date1) >= 0) {
				if (count == 0) {
					startIndex = i;
					count++;
				}
			}
			if (currentD.compareTo(date2) >= 0) {
				if (currentD.compareTo(date2) == 0) {
					endIndex = i;
					break;
				} else {
					endIndex = i - 1;
					break;
				}
			}
			if(i == index - 1 && count != 0) {
				endIndex = i;
			}
		}
		if(date2.compareTo(date1) < 0) {
			throw new IllegalArgumentException();
		}
		
		for(int i = startIndex; i<= endIndex; i++) {
			totalWeight += milkWeight[i];
		}
		return totalWeight;
	}
	
}
