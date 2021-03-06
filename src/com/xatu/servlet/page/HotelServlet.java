package com.xatu.servlet.page;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xatu.bean.Attraction;
import com.xatu.bean.Hotel;
import com.xatu.dao.DBOperation;
import com.xatu.service.ConversionService;

/**
 * 酒店服务业务处理
 */
@WebServlet("/HotelServlet")
public class HotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HotelServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DBOperation operation = DBOperation.getMyDB();
		HttpSession session = request.getSession();
		response.setCharacterEncoding("GBK");
		// 防止重复加载
		if (session.getAttribute("hotelList") == null) {
			String[] tableHead = { "id", "time", "contact_phone", "name", "contact_name", "prices", "address" };
			String tableName = "td_hotel";
			List<Hotel> hotels = ConversionService.object2Hotel(operation.select(tableHead, tableName),
					getServletContext());
			session.setAttribute("hotelList", hotels);
		}
//		/*
//		 * 2017年4月16日11:49:28 修复当直接点击酒店/租车页面时，无法正确显示目的地
//		 */
//		if (session.getAttribute("attractionList") == null) {
			String[] tableHead1 = { "id", "name", "info", "see_num", "query_num", "img_file", "ticket_prices",
					"address" };
			String tableName1 = "tb_attractions";
			List<Attraction> attractions = ConversionService.object2Attraction(operation.select(tableHead1, tableName1),
					getServletContext());

			session.setAttribute("attractionList", attractions);
//		}
		response.sendRedirect("jsp/page/hotel_reservation.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
