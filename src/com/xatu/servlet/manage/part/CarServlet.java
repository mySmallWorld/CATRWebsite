package com.xatu.servlet.manage.part;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xatu.bean.Announce;
import com.xatu.bean.Car;
import com.xatu.bean.User;
import com.xatu.dao.DBOperation;
import com.xatu.service.ManagerService;
import com.xatu.util.StringChage;
import com.xatu.util.TableInfo;

/**
 * 租车表业务
 */
@WebServlet("/CarServlet")
public class CarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CarServlet() {
		super();
	}

	/**
	 * 租出表管理员业务操作
	 * 
	 * 增删改查等任务
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("GBK");
		DBOperation operation = DBOperation.getMyDB();
		HttpSession session = request.getSession();
		//获取租车标志指令
		String sign = request.getParameter("sign_car");
		System.out.println("=====================" + sign);
		if (sign != null) {
			//删除指令
			if (sign.equals("delete")) {
				String id = request.getParameter("item_id");
				operation.delete(TableInfo.tableName[6], id);
			}
			//更新指令
			if (sign.equals("updata")) {
				String id = request.getParameter(TableInfo.carTableHead[0]);
				String time = request.getParameter(TableInfo.carTableHead[1]);
				String contact_phone = request.getParameter(TableInfo.carTableHead[2]);
				String name = StringChage.encodingChage(request.getParameter(TableInfo.carTableHead[3]));
				String contact_name = StringChage.encodingChage(request.getParameter(TableInfo.carTableHead[4]));
				String price = request.getParameter(TableInfo.carTableHead[5]);
				String address = StringChage.encodingChage(request.getParameter(TableInfo.carTableHead[6]));
				String[] data = { id, time, contact_phone, name, contact_name, price, address };
				// for (int i = 0; i < data.length; i++) {
				// System.out.println(data[i]);
				// }
				operation.delete(TableInfo.tableName[6], id);
				operation.insertInto(TableInfo.tableName[6], TableInfo.carTableHead, data);
			}
			//添加指令
		} else if (request.getParameter(TableInfo.carTableHead[0]) != null)
			makeAdd(request, response, operation);

		List<String[]> tempStrs = null;
		String query = request.getParameter("query");
		//模糊查询操作
		if (query != null && !query.equals("")) {
			query = StringChage.encodingChage(query);
			tempStrs = operation.selectLike(TableInfo.carTableHead, "tb_car",
					"id like '%" + query + "%' or " + "name like '%" + query + "%' or " + "address like '%" + query
							+ "%' or " + "contact_name like '%" + query + "%'");
		} else {
			tempStrs = operation.select(TableInfo.carTableHead, TableInfo.tableName[6]);
		}
		// 从数据到 bean 的映射交给 service 层完成
		List<Car> cars = ManagerService.StringToCar(tempStrs);
		// 设置 session
		session.setAttribute("cars", cars);
		session.setAttribute("tableHead", TableInfo.carTableHead);
		// 跳转
		response.sendRedirect("jsp/manage/part/car.jsp");
	}

	/**
	 * 处理增加操作
	 * 
	 * @param request
	 * @param response
	 */
	private void makeAdd(HttpServletRequest request, HttpServletResponse response, DBOperation operation) {
		String id = request.getParameter(TableInfo.carTableHead[0]);
		String time = request.getParameter(TableInfo.carTableHead[1]);
		String contact_phone = request.getParameter(TableInfo.carTableHead[2]);
		String name = StringChage.encodingChage(request.getParameter(TableInfo.carTableHead[3]));
		String contact_name = StringChage.encodingChage(request.getParameter(TableInfo.carTableHead[4]));
		String price = request.getParameter(TableInfo.carTableHead[5]);
		String address = StringChage.encodingChage(request.getParameter(TableInfo.carTableHead[6]));
		String[] data = { id, time, contact_phone, name, contact_name, price, address };
		// for (int i = 0; i < data.length; i++) {
		// System.out.println(data[i]);
		// }
		operation.insertInto(TableInfo.tableName[6], TableInfo.carTableHead, data);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
