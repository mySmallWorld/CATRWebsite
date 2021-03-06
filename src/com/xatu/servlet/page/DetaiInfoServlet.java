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
import com.xatu.dao.DBOperation;
import com.xatu.service.ConversionService;

/**
 * 景点信息服务
 */
@WebServlet("/DetaiInfoServlet")
public class DetaiInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DetaiInfoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("GBK");
		String id = request.getParameter("id");
		DBOperation operation = DBOperation.getMyDB();
		HttpSession session = request.getSession();
		String[] tableHead = { "id", "name", "info", "see_num", "query_num", "img_file", "ticket_prices", "address" };
		String tableName = "tb_attractions";
		Attraction attraction = ConversionService
				.object2Attraction1(operation.selectOne(tableHead, tableName, "name='" + id+"'"), getServletContext());
		session.setAttribute("attraction", attraction);
		operation.queryplus("see_num",attraction.getId()+"","tb_attractions");
		response.sendRedirect("jsp/page/detailed_info_page.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
