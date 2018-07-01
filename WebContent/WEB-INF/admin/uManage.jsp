<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$(function(){
		$('#dg').datagrid({    
			title: '用户列表',
			iconCls: 'icon-user',
			fit: true,
			fitColumns: true,
		    rownumbers: true,
		    pagination: true,
		    striped: true, //斑马线
			idField: 'id',
		    url:'user_list.action',
		    columns:[[    
		        {field:'chk',checkbox: true},    
		        {field:'id',title:'ID',width:'40'},    
		        {field:'trueName',title:'真实姓名',width:'150'},    
		        {field:'userName',title:'用户名',width:'150'},    
		        {field:'sex',title:'性别',width:'50'},    
		        {field:'birthday',title:'生日',width:'100'},    
		        {field:'dentityCode',title:'身份证',width:'200'},    
		        {field:'email',title:'邮件',width:'150'},    
		        {field:'mobile',title:'电话',width:'150'},    
		        {field:'address',title:'地址',width:'200'},    
		    ]],
		    toolbar: '#tb'
		});
		//设置分页控件 
	    var p = $('#dg').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [10,20,30,50,100],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	    });
	    
		
	})
	
    //查询
    function searchUser(){
    	$("#dg").datagrid('load', {
    		's_user.userName': $("#s_userName").val()
    	});
    };
    //删除用户
    function deleteUser(){
    	var selectRows = $("#dg").datagrid("getSelections");
    	if(selectRows.length == 0){
    		$.messager.alert("信息提示","请选择数据进行操作","warning");
    		return;
    	}
    	var _ids = [];
    	for(var i = 0;i < selectRows.length;i++){
    		_ids.push(selectRows[i].id);
    	}
    	var ids = _ids.join(",");
    	$.messager.confirm('信息提示', "确认删除这"+selectRows.length+"条数据？", function(r){
    		if(r){
    			$.post(
   		    		"user_deleteUser.action",
   		    		{ids:ids},
   		    		function(result){
   		    			var ret = $.parseJSON(result);
   		    			if(ret.success){
   		    				$("#dg").datagrid("reload");
   		    				$.messager.alert("信息提示","删除成功","info");
   		    			}
   		    		}
   		    	);
    		}
    	});
    }
    
    //访问的地址
    var url = "";
    
    //添加用户：打开窗口
    function openAddUser(){
    	$("#dlg").dialog({
    		title: '添加用户',
    		iconCls: 'icon-add'
    	});
    	$("#dlg").dialog("open");
    	
    	url = "user_addUser.action";
    	
    }
    //编辑用户：打开窗口
    function openEditUser(){
    	var selectRows = $("#dg").datagrid("getSelections");
    	if(selectRows.length != 1){
    		$.messager.alert("信息提示","请选择一条数据进行操作","warning");
    		return;
    	}
    	
    	var row = selectRows[0];
    	
    	//$("#userId").val(row.id);
    	$("#trueName").val(row.trueName);
    	$("#userName").val(row.userName);
    	$("#password").val(row.password);
    	$("#sex").combobox("setValue", row.sex);
    	$("#birthday").datebox("setValue", row.birthday);
    	$("#dentityCode").val(row.dentityCode);
    	$("#email").val(row.email);
    	$("#mobile").val(row.mobile);
    	$("#address").val(row.address);
    	
    	$("#dlg").dialog({
    		title: '编辑用户',
    		iconCls: 'icon-edit'
    	});
    	$("#dlg").dialog("open");
    	
    	url = "user_editUser.action?user.id="+row.id;
    }

    //保存
    function save(){
    	//$.messager.progress();
    	//表单
        $("#frm").form('submit', {
        	url: url,
        	onSubmit: function(){
        		return $("#frm").form("validate");
        	},
        	success: function(result){
        		var ret = $.parseJSON(result);
        		if(ret.success){
        			$("#dlg").dialog("close");
        			clearForm();
        			$("#dg").datagrid("reload");
        			$.messager.alert("信息提示","保存成功","info");
        		} else{
        			$.messager.alert("信息提示","保存失败","info");
        		}
        	}
        });
        //$.messager.progress("close");
    }
    //取消
	function cancel(){
		clearForm();
    	$("#dlg").dialog("close");
    }
    //清空表单
    function clearForm(){
    	$("#trueName").val("");
    	$("#userName").val("");
    	$("#password").val("");
    	$("#sex").combobox("setValue", "男");
    	$("#birthday").datebox("setValue", "");
    	$("#dentityCode").val("");
    	$("#email").val("");
    	$("#mobile").val("");
    	$("#address").val("");
    }
</script>
</head>
<body>
	<table id="dg"></table>
	
	<div id="tb">
		<a href="javascript:openAddUser()" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-add'">增加</a>
		<span style="float:none;" class="datagrid-btn-separator"></span>
		<a href="javascript:openEditUser()" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-edit'">编辑</a>
		<span style="float:none;" class="datagrid-btn-separator"></span>
		<a href="javascript:deleteUser()" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-remove'">删除</a>
		<span style="float:none;" class="datagrid-btn-separator"></span>
		 
		 &nbsp;&nbsp;用户名：&nbsp;<input class="easyui-textbox" type="text" id="s_userName" size="20" onkeydown="if(event.keyCode == 13) searchUser()" />
		<a href="javascript:searchUser()" id="btn_searchUser" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-search'">查询</a>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width: 570px; height: 300px;" data-options="closed:true, buttons:'#dlg_buttons'">
		<form id="frm" method="post">
			<table cellspacing="8">
				<tr>
					<td>真实姓名：</td>
					<td>
						<input type="text" id="trueName" name="user.trueName"  style="width: 150px" class="easyui-validatebox" required="required" />
						<!-- <input type="hidden" id="userId" name="user.id" /> -->
					</td>
					<td>&nbsp;</td>
					<td>用户名：</td>
					<td><input type="text" id="userName" name="user.userName"  style="width: 150px" class="easyui-validatebox" required="required" /></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" id="password" name="user.password"  style="width: 150px" class="easyui-validatebox" required="required" /></td>
					<td>&nbsp;</td>
					<td>性别：</td>
					<td>
						<select id="sex" class="easyui-combobox" name="user.sex" style="width: 150px" data-options="panelHeight:40,editable:false">   
						    <option value="男">男</option>   
						    <option value="女">女</option>   
						</select>
					</td>
				</tr>
				<tr>
					<td>出生日期：</td>
					<td><input type="text" id="birthday" class="easyui-datebox" name="user.birthday"  style="width: 150px" editable="false" required="required" /></td>
					<td>&nbsp;</td>
					<td>身份证：</td>
					<td><input type="text" id="dentityCode" name="user.dentityCode"  style="width: 150px" class="easyui-validatebox" required="required" /></td>
				</tr>
				<tr>
					<td>邮件：</td>
					<td><input type="text" id="email" name="user.email"  style="width: 150px" class="easyui-validatebox" validType="email" required="required" /></td>
					<td>&nbsp;</td>
					<td>联系电话：</td>
					<td><input type="text" id="mobile" name="user.mobile"  style="width: 150px" class="easyui-validatebox" required="required" /></td>
				</tr>
				<tr>
					<td>收货地址：</td>
					<td colspan="4"><input type="text" id="address" name="user.address"  style="width: 396px" class="easyui-validatebox" required="required" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg_buttons">
		<a href="javascript:save()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存</a>
		<a href="javascript:cancel()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">关闭</a>
	</div>
</body>
</html>