package com.dyf.utils;

public class BuyUtil {

	/**
	 * 将车辆的已经停车时间进行排序，原来的已经停车时间为20 6,12 19,9 11 排序之后为：9 11,12 19,20 6
	 * @param arr 待排序数组，也就是车辆的已经停车时间
	 * @return 已经停车时间排序之后的数组
	 */
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
		SysoUtils.print("已经停车时间排序之后的数组为：");
		for (int i = 0; i < arr.length; i++) {
			SysoUtils.print(arr[i][0] + " " + arr[i][1]);
		}
		return arr;
	}

	/**
	 * 根据已经停车时间排序之后的数组得到可以停车时间排序之后的数组 已经停车时间排序之后的数组：9 11,12 19,20 6
	 * 可以停车时间排序之后的数组：6 9,11 12，19 20
	 * 这种排序方法之后，可能会出现5点到5点的情况，需要使用这个方法之后，对数值进行一个非等判断
	 * 
	 * @param 已经停车时间排序之后的数组
	 * @return 可以停车时间排序之后的数组
	 */
	public static int[][] getCanParkTime(int[][] arr) {

		int temp = -1;
		temp = arr[arr.length - 1][1];
		for (int i = arr.length - 1; i > 0; i--) {
			arr[i][1] = arr[i][0];
			arr[i][0] = arr[i - 1][1];
		}
		arr[0][1] = arr[0][0];
		arr[0][0] = temp;
		SysoUtils.print("可以停车时间排序之后的数组为：");
		for (int i = 0; i < arr.length; i++) {
			SysoUtils.print(arr[i][0] + " " + arr[i][1]);
		}
		return arr;
	}

	/*public static void main(String[] args) {

		 int[][] array = { { 14, 18 }, { 20, 6 }, { 9, 12 }, { 6, 8 } }; 
		int[][] array = { { 20, 6 }, { 12, 19 }, { 9, 11 } };

		BuyUtil buyUtil = new BuyUtil();
		int[][] brr = buyUtil.parkTimeSort(array);
		brr = buyUtil.getCanParkTime(brr);
		for (int i = 0; i < brr.length; i++) {
			for (int j = 0; j < brr[0].length; j++) {
				System.out.print(brr[i][j] + " ");
			}
			System.out.println();
		}
	}*/

}
