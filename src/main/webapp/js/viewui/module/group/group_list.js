$("document").readyfn(function() {
	var context = this, selectItem = null;
	var i18nGroup = pousheng.getI18N('group'); // 初始化的时候获取国际化的文字
	var groupUserListGrid = $("#groupUserListGrid").datagrid({url:'user/getGroupUser',queryParams:{groupId:''}});
	var groupListGrid = $("#groupListGrid").datagrid({url:'group/dataGrid'});

	groupListGrid.datagrid("option", "onDblClickRow", function(index, item) {

	});
	groupListGrid.datagrid("option", "onClickRow", function(index, item) {
		selectItem = item;
		groupUserListGrid.datagrid("refresh",null,{groupId:item.groupId});
	});
	var modelDialog = function(options) {
		var settings = {
			title : '',
			url : '',
			requestParam : ''
		};
		$.extend(true, settings, options); // true深度拷贝
		$.modal({
			title : settings.title,
			width : 615,
			height : 440,
			remote : settings.url,
			requestParam : settings.requestParam,
			ready : function(event, context) {
				// 弹出框口页面的初始化操作
			},
			singleSelect : true,
			buttons : [ {
				text : btn.save,
				cls : "btn-primary",
				click : function() {
					var $this = $(this);
					$this.find("form").trigger("submit", {
						success : function(data) {
							groupListGrid.datagrid("refresh", null, null);
							groupUserListGrid.datagrid("refresh", null, null);
							$this.modal("close");
						}
					});
				}
			}, {
				text : btn.cancel,
				click : function() {
					$(this).modal("close");
				}
			} ]
		});
	};
	groupListGrid.datagrid("register", "add", function() {
		var options = {
			title : i18nGroup.txt.add,
			url : 'group/addForm'
		};
		modelDialog(options);
	});
	groupListGrid.datagrid("register", "edit", function() {
		selectItem = this.getSelect();
		var options = {
			title : i18nGroup.txt.add,
			url : 'group/editForm',
			requestParam : {groupId:selectItem.groupId}
		};
		if(selectItem.groupId){
			modelDialog(options);
		} else {
			pousheng.warnMsg("请选择一条用户组记录进行编辑");
		}
	});

	groupListGrid.datagrid("register", "delete", function() {
		var that = this;
		pousheng.confirm(i18nGroup.msg.confirmdelete, function(r) {
			if (r) {
				var selectItem = that.getSelect();
				pousheng.ajaxData("group/delete", {
					data : {
						groupId : selectItem.groupId
					}
				}).done(function() {
					groupListGrid.datagrid("refresh", null, null);
				});
			}
		});
	});

	groupListGrid.datagrid("register", "view", function() {
		selectItem = this.getSelect();
		$.modal({
			title : i18nGroup.txt.view,
			width : 615,
			height : 440,
			remote : "group/editForm",
			requestParam : {
				groupId : selectItem.groupId
			},
			ready : function(event, context) {
				// 弹出框口页面的初始化操作
				$(context).viewform(); // 先替换基本的标签
			},
			buttons : [ {
				text : btn.cancel,
				click : function() {
					$(this).modal("close");
				}
			} ]
		});
	});
	groupListGrid.datagrid("register", "addUser", function() {
		
	});
	groupListGrid.datagrid("register", "deleteUser", function() {
		var that = this;
		pousheng.confirm(i18nGroup.msg.confirmdeleteuser, function(r) {
			if (r) {
				var selectItem = that.getSelect();
				pousheng.ajaxData("group/delete", {
					data : {
						groupId : selectItem.groupId
					}
				}).done(function() {
					groupListGrid.datagrid("refresh", null, null);
				});
			}
		});
	});
});
