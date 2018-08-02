/**
 * 
 */
package com.thanoj.tackletest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;

/**
 * @author schamarthi
 *
 */
public class SensorReport {
	private HashMap<Integer,HashMap<Integer,Integer>> processedData = new HashMap<Integer,HashMap<Integer,Integer>>();
	int sampleAverage = 1*1000;
	private ArrayList<int[]> initialData;
	private int timeEnd = 999;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SensorReport report = new SensorReport();
		report.setData();
		report.processData();
		report.generateReport();
	}

	private void generateReport() {
		// TODO Auto-generated method stub 
		int[] range =  findMinMax(sampleAverage);
		for(int i= range[0]; i < range[1]; i++){
			printAverageByTime(i*sampleAverage);
		}
	}
 
	 
	private void printAverageByTime(int i) {
		// TODO Auto-generated method stub
		p(getAverageByTime(i));
	}

	private String getAverageByTime(int i) {
		// TODO Auto-generated method stub
		int startTime = i;
		int endTime = startTime+timeEnd;
		return startTime + " - "+ (endTime) + ": "+ getAverageFor(startTime);
	}

	@SuppressWarnings("unchecked")
	private String getAverageFor(int startTime) {
		// TODO Auto-generated method stub 
		// get list of keys valid
		ArrayList<Integer> nowList = new ArrayList<>();
		Set<Integer> list = processedData.keySet();
		for(Integer ts: list){
			if(ts >= startTime && ts < (startTime + timeEnd)){
				nowList.add(ts);
			}
		}
		// get values from map
		ArrayList<Integer> vals = new ArrayList<Integer>();
		for(Integer k : nowList){
			HashMap<Integer,Integer> j = processedData.get(k);
			vals.addAll(j.values()); 
		}
		
		int sum = vals.stream().reduce(0, (e1, e2) -> (e1 + e2));
		
		return String.format("%.2f",  new Double(sum)/new Double(vals.size())) ;
	}

	private int[] findMinMax(int sampleAverage2) {
		// TODO Auto-generated method stub
		Set<Integer> keyset = processedData.keySet();
		ArrayList<Integer> keys = new ArrayList<Integer>(keyset);
		OptionalInt minValue = keys.stream().mapToInt(v  -> v).min();
		OptionalInt maxValue = keys.stream().mapToInt(v  -> v).max();
		int minVal = getIntVal(minValue, false);
		int maxVal = getIntVal(maxValue, true);
		return new int[]{minVal, maxVal};
	}
	
	// helper for print	
	private void p(String s){
		 System.out.println(s);
	}
	
	private int getIntVal(OptionalInt maxValue, boolean b) {
		// TODO Auto-generated method stub
		int retval = 0;
		if(b){
			retval = (int) Math.ceil((double) maxValue.getAsInt()/ new Double(sampleAverage));
		} else {
			retval = (int) Math.floor((double) maxValue.getAsInt()/ new Double(sampleAverage));
		}
		return retval;
	}

	private void processData() {
		//  Preserving sensor id, however, not used in the code anywhere.
		for(int[] entry : initialData){
			if(entry.length == 3){
				HashMap<Integer,Integer> sampleData = new HashMap<Integer, Integer>();
				sampleData.put(new Integer(entry[0]), new Integer(entry[2]));
				 processedData.put(new Integer(entry[1]), sampleData);
			}
		}
	}
	
	

	/*
	 * Set initial data from test
	 */
	private void setData() {
		// TODO Auto-generated method stub
		ArrayList<int[]> data = new ArrayList<int[]>(); 
		 
		data.add((new int[]{1,10000,40}));
		data.add((new int[]{1,10002,45}));
		data.add((new int[]{1,11015,50}));
		data.add((new int[]{2,10005,42}));
		data.add((new int[]{2,11051,45}));
		data.add((new int[]{2,12064,42}));
		data.add((new int[]{2,13161,42})); 
		
		this.initialData = data;
	}

	
}
