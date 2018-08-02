/**
 * 
 */
package com.thanoj.tackletest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author thanoj.g
 *
 */
public class ArrangeSeat {

	private ArrayList<String> StudentsList = new ArrayList(); 
	private ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>(); 
	private int noOfRows = 2; // can be parameter too. 
	
	public ArrangeSeat(int total){
		generateStudentArray(total);
		rearrangeSeating(StudentsList);
		// Prove by output
		printRows();
	}
	
	public static void main(String[] args){
		ArrangeSeat seats = new ArrangeSeat(30);
	}
	
	private void rearrangeSeating(ArrayList studentsList) {
		// TODO Auto-generated method stub
		
		for(int i=0; i < StudentsList.size(); i++){ 
				ArrayList<String> a;
				if(rows.isEmpty() || rows.size() <= (i < (StudentsList.size()/2) ? 0 : 1) ) {
					a = new ArrayList<String>();
					a.add(StudentsList.get(i)); 	
					rows.add(a);
					
				} else {
					a = rows.get((i < (StudentsList.size()/2) ? 0 : 1));
					a.add(StudentsList.get(i)); 	
				}
					
		}
		if(StudentsList.size()%2 ==0){
			// get second row and do the magic
			ArrayList<String> secondRow = rows.get(1);
			String firstOfRow = secondRow.get(0);
			secondRow.remove(0);
			secondRow.add(firstOfRow);
		}
		
		
	}

	private void printRows() {
		// TODO Auto-generated method stub
		for(int i=0; i < (int) Math.ceil((double)StudentsList.size()/new Double(2)); i++){
			p("row 0: " + ((rows.get(0).size() > i) ? rows.get(0).get(i) : " ") + " ,  row 1: " +  ((rows.get(1).size() > i) ? rows.get(1).get(i) : " "));
		}
		
	}	

	private int getSumofStudents(int[] Students){
		int total = 0;
		for(int i : Students){
			total += i;
		}
		return total;
	}
	// helper for print	
	private void p(String s){
		 System.out.println(s);
	}
	// we may think of random input, if that's the case, I'll rearrange it using similar method. 
	private void generateStudentArray(int n){
		for(int i=1; i <= n; i++){
			
			if(i%3 == 0){	
				// first Maths
				StudentsList.add("M" + Integer.valueOf(i));
			} else if(i%3==1){
				StudentsList.add("P" + Integer.valueOf(i));
			} else {
				StudentsList.add("C" + Integer.valueOf(i));
			}
		}
		
	}

}
