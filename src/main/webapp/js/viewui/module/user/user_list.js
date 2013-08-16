$("document").readyfn(function() {
	var userItem = null;
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
			width:150
		} ,{
			field : 'phone',
			title : '电话',
			width:200
		}];
		common.select({
			param : {columnJson : JSON.stringify(columns)},
			dataUrl : 'common/userData',
			treeSettings:{treeDataUrl:'user/userList',idKey:'userId',pIdKey:'0',name:'userName'},
			dataGridSettings : {dataUrl:'common/userData'},
			width:900
		}, function(data) {
			console.info(data);
		});
	});
});
