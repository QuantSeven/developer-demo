$("document").readyfn(function() {
	var userListGrid = $("#userListGrid").datagrid();
	userListGrid.datagrid("option", "onDblClickRow", function(index, item) {
		$("#indexTab").navTab("load", "user/view", {
			"userId" : item.userId
		}, function() {
			$(this).viewform();
		});

	});

	$("#btnTest").on("click", function() {
		var columns = [{
			title : '',
			checkbox:true,
			field : 'userId',
			width:150
		}, {
			title : '用户编号',
			field : 'userId',
			sortName:'USER_ID',
			width:150
		}, {
			title : '用户名',
			field : 'userName',
			sortName:'USER_NAME',
			search : true,
			width:150
		} , {
			title : '邮箱',
			field : 'email',
			sortName:'EMAIL',
			search : true,
			width:150
		} ,{
			field : 'phone',
			title : '电话',
			width:200
		}];
		common.select({
			requestParam : {columnJson : JSON.stringify(columns)},
			//treeSettings:{treeDataUrl:'user/userList',idKey:'userId',pIdKey:'userId',name:'userName'},
			treeSettings:{treeDataUrl:'menu/menuList',idKey:'menuId',pIdKey:'parentId',name:'name'},
			dataGridSettings : {dataUrl:'user/dataGrid'},
			width:900
		}, function(data) {
			console.info(data);
		});
	});
});
