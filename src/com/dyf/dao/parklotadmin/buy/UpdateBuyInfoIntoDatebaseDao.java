package com.dyf.dao.parklotadmin.buy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dyf.bean.DBBean;
import com.dyf.model.BuyInfo;
import com.dyf.utils.InOutUtil;
import com.dyf.utils.SysoUtils;

@WebServlet(description = "将修改过的购买信息存进数据库", urlPatterns = { "/UpdateBuyInfoIntoDatebaseDao" })
public class UpdateBuyInfoIntoDatebaseDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateBuyInfoIntoDatebaseDao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SysoUtils.print("进入UpdateBuyInfoIntoDatebaseDao.java");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		String buyCreatedTime = request.getParameter("buycreatedtime");
		String ownerphone = request.getParameter("ownerphone");
		String owneraddress = request.getParameter("owneraddress");
		int buyparkid = Integer.parseInt(request.getParameter("buyparkid"));
		String buystartparktime = request.getParameter("buystartparktime");
		String buyendparktime = request.getParameter("buyendparktime");
		String buystartparkdate = request.getParameter("buystartparkdate");
		String buyendparkdate = request.getParameter("buyendparkdate");
		double afterbuymoney = Double.parseDouble(request.getParameter("buymoney"));
		double beforebuymoney = Double.parseDouble(request.getParameter("beforebuymoney"));
		double buymoney = afterbuymoney + beforebuymoney;
		SysoUtils.print("购买信息修改之后的数据为：" + buyCreatedTime + " " + ownerphone + " " + owneraddress + " " + buyparkid + " "
				+ buystartparktime + " " + buyendparktime + " " + buystartparkdate + " " + buyendparkdate + " "
				+ afterbuymoney);

		HttpSession session = request.getSession();
		String adminId = (String) session.getAttribute("userid");
		SysoUtils.print("管理员id为：" + adminId);

		String updateSql = "UPDATE table_buyinfo buy, table_ownerinfo own SET buy.buyparkid = " + buyparkid
				+ ", buy.buystartparktime = " + buystartparktime + ", buy.buyendparktime = " + buyendparktime
				+ ", buy.buystartparkdate = '" + buystartparkdate + "', buy.buyendparkdate = '" + buyendparkdate
				+ "', buy.buymoney = " + buymoney + ", own.ownerphone = '" + ownerphone + "', own.owneraddress = '"
				+ owneraddress + "' WHERE buy.parkadminid = '" + adminId + "' AND own.parkadminid = '" +adminId+ "' AND buy.buycreatedtime = '"
				+ buyCreatedTime + "' AND own.ownercreatedtime = '" + buyCreatedTime + "'";
		SysoUtils.print("updateSql:" + updateSql);

		DBBean dbBean = new DBBean();
		int i = dbBean.executeUpdate(updateSql);
		if (i == 1 || i == 2) {
			SysoUtils.print("修改成功。");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('修改成功');</script>");
			response.setHeader("refresh", "0;url=QueryBuyInfoDao");
		} else {
			SysoUtils.print("修改失败。");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('修改失败');</script>");
			response.setHeader("refresh", "0;url=QueryBuyInfoDao");
		}
		dbBean.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
