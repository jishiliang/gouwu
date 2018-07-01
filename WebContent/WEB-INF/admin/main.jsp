<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	//点击菜单 打开面板
	function openTab(title, url, iconCls){
		if(!$("#tab").tabs("getTab", title)){
			var content = "<iframe frameborder=0 scrolling='auto' style='width:100%; height:100%;' src='"+url+"'></iframe>";
			$("#tab").tabs("add", {
					title: title,
					selected: true,
					iconCls: iconCls,
					closable: true,
					//href: url, //无法加载页面,可以显示简单的文字
					//style: {padding: '10px'}
					content: content
				}
			)
		} else{
			$("#tab").tabs("select", title);
		};
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>易买网-后台首页</title>
</head>
<body class="easyui-layout">   
    <div data-options="region:'south',split:true" style="height:40px; font-weight: bold; text-align:center; color: #35437F; background: #D3E0F1;">
    	<div style="height: 10px;"></div>
    	<span style="padding-top: 20px; font-size: 12px;">
    		Copyright &copy; 2015 Chiangchou All Rights Reserved.
			<a target="_blank" style="color: #35437F;" href="http://www.cnblogs.com/chiangchou">Chiangchou</a>
		</span>
    </div><div data-options="region:'north',split:true" style="height:80px; background: #93ABCC;">
    	<table style="padding: 6px 10px 0px 6px; height: 66px;" width="100%">
    		<tr>
    			<td width="50%">
    				<!-- <img alt="易买网" src="images/bglogo.png"> -->
    			</td>
    			<td width="50%" valign="bottom" align="right">
    				<font style="font-size: 18px; font-weight: bold; color:red;">欢迎：${currentUser.userName }</font>
    			</td>
    		</tr>
    	</table>
    </div>   
       
    <div data-options="region:'west',split:true, title:'导航菜单', border:false" style="width:240px;">
    	<div id="menu" class="easyui-accordion" data-options="fit:true">   
	    	<div title="用户管理"  data-options="iconCls:'icon-user'" style="padding:10px">
				<a href="javascript:openTab('管理用户', 'user_toUManageView.action', 'icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 160px;">管理用户</a>
			</div>
	    	<div title="商品管理"  data-options="iconCls:'icon-product'" style="padding:10px;">
				<a href="javascript:openTab('管理商品', 'product_toProductManageView.action', 'icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 160px;">管理商品</a>
				<a href="javascript:openTab('管理商品大类', '', 'icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 160px;">管理商品大类</a>
				<a href="javascript:openTab('管理商品小类', '', 'icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 160px;">管理商品小类</a>
			</div>
			<div title="订单管理"  data-options="iconCls:'icon-order'" style="padding:10px">
				<a href="javascript:openTab('管理订单', '', 'icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 160px;">管理订单</a>
			</div>
			<div title="留言管理" data-options="iconCls:'icon-comment'" style="padding:10px">
				<a href="javascript:openTab('管理留言', '', 'icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 160px;">管理留言</a>
			</div>
			<div title="公告管理"  data-options="iconCls:'icon-notice'" style="padding:10px">
				<a href="javascript:openTab('管理公告', '', 'icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 160px;">管理公告</a>
			</div>
			<div title="新闻管理"  data-options="iconCls:'icon-news'" style="padding:10px">
				<a href="javascript:openTab('管理新闻', '', 'icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 160px;">管理新闻</a>
			</div>
			<div title="标签管理"  data-options="iconCls:'icon-tag'" style="padding:10px">
				<a href="javascript:openTab('管理标签', '', 'icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 160px;">管理标签</a>
			</div>
			<div title="系统管理"  data-options="iconCls:'icon-item'" style="padding:10px">
				<a href="javascript:openTab('修改密码', '', 'icon-modifyPassword')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 160px;">修改密码</a>
				<a href="javascript:;" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'" style="width: 160px;">安全退出</a>
				<a href="javascript:;" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-refresh'" style="width: 160px;">刷新系统缓存</a>
			</div>
		</div>  
    </div>   
    <div data-options="region:'center'" style="background:#eee;">
    	<div id="tab" class="easyui-tabs" data-options="fit:true, border: false">   
		    <div title="首页" style="padding:20px;" data-options="iconCls:'icon-home',closable: false" >   
				<span style="color: red;">
					<p style="font-size: 28px; line-height: 40px; height: 40px;">欢迎使用易买网后台管理系统</p>
				  	<p>开发人员：刘体勇</p>
				  	<p>开发周期：半个月</p>
				  	
				  	<hr />
				  	<h2>系统环境</h2>
				  	<p>系统环境：Windows</p>
					<p>开发工具：MyEclipse</p>
					<p>Java版本：JDK 1.7</p>
					<p>服务器：tomcat 7.0</p>
					<p>数据库：MySQL 5.1</p>
					<p>系统采用技术：SSH + easyui</p>
				</span>   
		    </div>   
		</div>
    </div>   
</body> 
</html>