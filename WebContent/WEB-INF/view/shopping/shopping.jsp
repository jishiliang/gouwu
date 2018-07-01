<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script> 
	$(function(){
		//设置总价
		function setTotal(){
			var sum = 0;
			$(".productTr").each(function(index, element){
				var price = $(this).find(".price_").text();
				var count = $(this).find(".text_box").val();
				sum += price * count;
			});
			$("#product_total").text(sum);
		}
		setTotal();
		
		$(".min").click(function(){
			var text_box = $(this).parent().find(".text_box");
			var count = parseInt($(text_box).val());
			if(count > 0){
				count--;
				$(text_box).val(count);
			}
			//修改单项总价
			var tr = $(this).parent().parent();
			var price = parseInt($(tr).find(".price_").text());
			$(tr).find(".item_price").text(count * price);
			//设置总价
			setTotal();
			//修改session中的值
			var productId = $(this).parent().find(".product_id").val();
			refreshSessionShoppingCar(productId, count);
		});
		
		$(".add").click(function(){
			var text_box = $(this).parent().find(".text_box");
			var count = parseInt($(text_box).val());
			if(count < 10){
				count++;
				$(text_box).val(count);
			}
			//修改单项总价
			var tr = $(this).parent().parent();
			var price = parseInt($(tr).find(".price_").text());
			$(tr).find(".item_price").text(count * price);
			//设置总价
			setTotal();
			//修改session中的值
			var productId = $(this).parent().find(".product_id").val();
			refreshSessionShoppingCar(productId, count);
		});
		//失去焦点
		$(".text_box").blur(function(){
			var val = $(this).val();
			if(!/^[0-9]*$/.test(val)){
				$(this).val(1);
			}
			var count = parseInt($(this).val());
			
			if(count > 10){
				count = 10;
				$(this).val(count);
			}
			//修改单项总价
			var tr = $(this).parent().parent();
			var price = parseInt($(tr).find(".price_").text());
			$(tr).find(".item_price").text(count * price);
			//设置总价
			setTotal();
			//修改session中的值
			var productId = $(this).parent().find(".product_id").val();
			refreshSessionShoppingCar(productId, count);
		});
		
		//更新session
		function refreshSessionShoppingCar(productId, count){
			$.post(
				"shopping_updateShoppingCarItem.action",
				{productId:productId, count:count},
				function(result){
					
				}
			);
		}
		
	});
	
	//删除购物车某项
	function deleteItem(productId){
		if(confirm("确认删除该项？")){
			window.location.href = "shopping_deleteShoppingCarItem.action?productId="+productId;
		}
	}
</script> 
</head>
<body>
<div id="shopping">
		<form action="order_save.action" method="post">
			<table id="myTableProduct">
				<tr>
					<th>商品名称</th>
					<th>商品单价</th>
					<th>金额</th>
					<th>购买数量</th>
					<th>操作</th>
				</tr>
				<c:forEach var="item" items="${shoppingCar.shoppingCarItems}">
					<tr class="productTr">
						<td class="thumb">
							<img class="imgs" src="${item.product.proPic }" /><a href="product_detail.action?productId=${item.product.id}">${item.product.name}</a>
						</td>
						<td class="price" >
							<span>￥<label class="price_" >${item.product.price }</label></span> 
						</td>
						<td class="price" >
							<span>￥<label class="item_price">${item.product.price * item.count }</label></span>
						</td>
						<td class="number">
					        <input type="hidden" class="product_id" value="${item.product.id }"/>
							<input class="min" name="" type="button" value=" - "  /> 
							<input class="text_box"  style="width: 30px;text-align: center" name="" type="text" value="${item.count }" /> 
							<input class="add" name="" type="button" value=" + " /> 
						</td>
						<td class="delete">
							<a href="javascript:deleteItem(${item.product.id})">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>

			<div class="button">
				<input type="submit" value="" />
			</div>
		</form>
</div>

<div class="shopping_list_end">

	<ul>
		<li class="shopping_list_end_2">
			￥<label id="product_total"></label>
		</li>
		<li class="shopping_list_end_3">商品金额总计：</li>
	</ul>
</div>

<div style="background-color: #cddbb8;margin-top: 10px;font-size: 20px;height: 40px;text-align: center">
	<div style="padding: 5px;">
		<b>个人信息</b>&nbsp;&nbsp;&nbsp;&nbsp;<b>收件人：</b>${currentUser.trueName } -- ${shoppingCar.shoppingCarItems.size()}&nbsp;&nbsp;<b>收获地址：</b>${currentUser.address }&nbsp;&nbsp;<b>联系电话：</b>${currentUser.mobile }
	</div>
</div>
</body>
</html>