<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>管理员</title>
<!-- <link rel="stylesheet" href="bootstrap3.2.2/css/bootstrap.min.css"> -->
<script type="text/javascript" language="javascript">
	function select(num) {
		window.location.href = "ManageServlet?sign=" + num;
	}
</script>
<style type="text/css">
.item_table_head {
	font-size: 20px;
	color: #4169e1;
	background-color: #444444;
	border-radius: 5px;
}
</style>
</head>
<body>
	<div style="width: 100%">
		<a href="" style="float: right; padding-bottom: 20px">退出</a>
		<h1 style="width: 200px">管理员界面</h1>
	</div>
	<hr />
	<table name="table1" style="background-color: EEEEEE; height: 100%;" width="100%">
		<tr>
			<td width="15%" height="10%" align="center" class="item_table_head" onclick="select(0)">管理员表</td>
			<td width="80%" rowspan="8" valign="top" height="100%">
				<div style="padding: 20px; height: 100%">
					<iframe src="${param.urlName}" width="100%" height="100%" style="height: 100%"></iframe>
				</div>
			</td>
		</tr>
		<tr>
			<td width="15%" height="10%" align="center" class="item_table_head" onclick="select(1)" id="td1">会员信息</td>
		</tr>
		<tr>
			<td width="15%" height="10%" align="center" class="item_table_head" onclick="select(2)" id="td2">景点信息</td>
		</tr>
		<tr>
			<td width="15%" height="10%" align="center" class="item_table_head" onclick="select(3)" id="td3">公告信息</td>
		</tr>
		<tr>
			<td width="15%" height="10%" align="center" class="item_table_head" onclick="select(4)" id="td4">留言信息</td>
		</tr>
		<tr>
			<td width="15%" height="10%" align="center" class="item_table_head" onclick="select(5)" id="td5">酒店信息</td>
		</tr>
		<tr>
			<td width="15%" height="10%" align="center" class="item_table_head" onclick="select(6)" id="td6">租车信息</td>
		</tr>
		<tr>
			<td width="15%" height="10%" align="center" class="item_table_head" onclick="select(7)" id="td7">多日游</td>
		</tr>
	</table>
</body>
</html>
