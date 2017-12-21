package com.dyf.utils;

public class BuyUtil {

	public static int[][] parkTimeSort(int[][] arr) {

		int temp1 = 0;
		int temp2 = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j][0] > arr[j + 1][0]) {
					temp1 = arr[j][0];
					temp2 = arr[j][1];
					arr[j][0] = arr[j + 1][0];
					arr[j][1] = arr[j + 1][1];
					arr[j + 1][0] = temp1;
					arr[j + 1][1] = temp2;
				}
			}

		}
		return arr;
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * int[][] array = {{14,18},{20,23},{9,12},{23,6}};
	 * 
	 * BuyUtil buyUtil = new BuyUtil(); int[][] brr=buyUtil.parkTimeSort(array);
	 * for(int i=0;i<brr.length;i++) { for (int j = 0; j < brr[0].length; j++) {
	 * System.out.print(brr[i][j]+" "); } System.out.println(); } }
	 */

}
