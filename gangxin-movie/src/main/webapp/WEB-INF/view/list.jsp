<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<LINK href="resources/css/css.css" type="text/css" rel="stylesheet">
<script src="resources/js/jquery-1.8.2.min.js"></script>
<title>Insert title here</title>
</head>
<body>


	<script type="text/javascript">

var orderMethod='${vo.orderMethod=='asc'?'desc':'asc' }'
  function query(orderField){
	  location.href="list?orderField="+orderField+"&orderMethod="+orderMethod;
  }
  
  function selAll(flag){
	  $(".ck").attr("checked",flag)
  }
  
  function fx(){
	  $(".ck").each(function(){
		  this.checked=!this.checked
	  })
  }
  
  function deleteBatch(){
	  var ids=$(".ck:checked").map(function(){
		  return this.value
	  }).get().join()
	  alert(ids)
	  if(ids.length==0){
		  alert("请选择数据");
	  }else{
			$.post(
					"/deleteBatch",
					{ids:ids},
					function(flag){
				if(flag=="ok"){
					alert("成功");
					location.href="list"//重新加载
				}else{
					alert("失败");
				}
			}),
			"text"
	  }
  }
  
  function update(id,status){

	  $.post(
			  "update",
			  {id:id,status:status},
			  function(flag){
				  if(flag=="ok"){
					  alert("修改成功");
					  location.href="list"
				  }else{
					  alert("修改失败");
				  }
			  },"text"
	  
	  )
  }

</script>


	<form action="list" method="post">
		<input type="button" value="反选" onclick="fx()"> <input
			type="button" value="批量删除" onclick="deleteBatch()">

		<table>
			<tr>
				<th><input type="checkbox" onclick="selAll(this.checked)"></th>
				<th>名称</th>
				<th>导演</th>
				<th>票价</th>
				<th><a href="javascript:query('uptime')">上映时间</a></th>
				<th><a href="javascript:query('longtime')">时间长度</a></th>
				<th>类型</th>
				<th><a href="javascript:query('year')">年代</a></th>
				<th>区域</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${pg.list }" var="m">
				<tr>
					<td><input type="checkbox" class="ck" value="${m.id }"></td>
					<td>${m.name }</td>
					<td>${m.actor }</td>
					<td>${m.price }</td>
					<td><fmt:formatDate value="${m.uptime }" pattern="yyyy-MM-dd" />
					</td>
					<td>${m.longtime }</td>
					<td>${m.type }</td>
					<td>${m.year }</td>
					<td>${m.area }</td>
					<td>${m.status }</td>
					<td>
					 <c:if test="${m.status=="热映" }">
					    <button onclick="update('${m.id}','下架')">下架</button>
					 </c:if>
					 <c:if test="${m.status=="即将上映" }">
					    <button onclick="update('${m.id}','热映')">热映</button>
					 </c:if>
					 <c:if test="${m.status=="下架" }">
					    <button onclick="update('${m.id}','即将上映')">即将上映</button>
					 </c:if>
					 
					</td>
				</tr>
			</c:forEach>
		</table>
		${page }
	</form>
</body>
</html>