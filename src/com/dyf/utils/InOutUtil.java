package com.dyf.utils;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;
import javax.swing.text.AbstractDocument.BranchElement;

import com.dyf.bean.DBBean;

public class InOutUtil {

	// 根据传过来的停车场管理员id获得该管理员所在停车场的名称
	public static String getParklotName(String id) {
		SysoUtils.print("传入的停车场管理员id为：" + id);
		String name = null;
		DBBean dbBean = new DBBean();
		String sql = "select parklotName from table_parklotInfo where parklotAdminId = " + "'" + id + "'";
		SysoUtils.print("查询name的sql为：" + sql);
		ResultSet rSet = dbBean.executeQuery(sql);
		try {
			while (rSet.next()) {
				name = rSet.getString("parklotName");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				rSet.close();
				dbBean.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		SysoUtils.print("查询到的停车场名称为：" + name);
		return name;
	}

	// 根据传过来的停车场管理员id获得该管理员所在停车场的车位总数量
	public static int getParkAmount(String id) {
		SysoUtils.print("传入的停车场管理员id为：" + id);
		int amount = 0; // 停车场管理员id所在停车场的车位总数量
		DBBean dbBean = new DBBean();
		String amountSql = "select parklotAmount from table_parklotInfo where parklotAdminId = " + "'" + id + "'";
		SysoUtils.print("查询车位数量的amountSql为：" + amountSql);
		ResultSet rSet = dbBean.executeQuery(amountSql);

		try {
			while (rSet.next()) {
				amount = rSet.getInt("parklotAmount");
				SysoUtils.print("车位总数为：" + amount);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rSet.close();
				dbBean.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		SysoUtils.print("查询到的车位总数为：" + amount);
		return amount;
	}

	// 根据传过来的停车场管理员id获得该管理员所在停车场的已经停车的车位号，返回一个ArrayList整型数组
	public static ArrayList<Integer> getArrParkId(String id) throws SQLException {
		int amount = getParkAmount(id);
		ArrayList<Integer> arrayList = new ArrayList<>();
		// int[] arr_ParkId = new int[]{}; //查询出来的已经有车的车位号放进一个数组中
		int i = -1;
		DBBean dbBean = new DBBean();
		String parkIdNumSql = "select parkid from table_inoutinfo where parkadminid = " + "'" + id + "'";
		SysoUtils.print("查询已经停车的车位号的parkIdNumSql为：" + parkIdNumSql);
		ResultSet rSet = dbBean.executeQuery(parkIdNumSql);
		while (rSet.next()) {
			i++;
			// SysoUtils.print(rSet.getInt("parkid"));
			// arr_ParkId[i] = rSet.getInt("parkid");
			arrayList.add(rSet.getInt("parkid"));
		}
		SysoUtils.print("查到的已经停车的车位号为：");
		for (int j = 0; j < arrayList.size(); j++) {
			SysoUtils.p(arrayList.get(j));
		}
		rSet.close();
		dbBean.close();

		return arrayList;
	}

	// 根据传过来的停车场管理员id获得该管理员所在停车场的车位数量以及已经停车场的车位号，返回一个没有车辆的车位数
	public static int getParkNumber(String id) throws SQLException {
		SysoUtils.print("传入的停车场管理员id为：" + id);
		boolean flag; // 作为进行从1开始进行车位号比较时的标记
		int number = 0; // 返回的可以停车的车位号
		int amount = getParkAmount(id); // 获得车位总数量
		ArrayList<Integer> arrayList = new ArrayList<>();
		arrayList = getArrParkId(id); // 获得已经停车的车位号放入数组中

		for (int i = 1; i <= amount; i++) {
			flag = false;
			for (int j = 0; j < arrayList.size(); j++) {
				if (i == arrayList.get(j)) {
					flag = true;
					break;
				}
			}
			if (flag == false) {
				number = i;
				break;
			}
		}
		SysoUtils.print("找到的可以停车位号为：" + number);
		return number;
	}

}
