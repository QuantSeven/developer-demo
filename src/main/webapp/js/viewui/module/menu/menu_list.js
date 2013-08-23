$("document").readyfn(function() {
	var i18nMenu = pousheng.getI18N('menu'); //初始化的时候获取国际化的文字
	var ctx = this, menuTree = null, selectedNode = null;
	var menuDataGrid = $(ctx).find("#menuDataGrid").datagrid({
		url : "menu/dataGrid"
	});
	menuDataGrid.datagrid("option", "onDblClickRow", function(index, data) {
		// this.grid.ajaxLoad("log/view",{data:{"logId":data.logId}});//双击 列表选项时
		/*
		 * $("#indexTab").navTab("load","menu/view",{"menuId":data.menuId},function(){
		 * $(this).viewform(); });
		 */
	});
	menuDataGrid.datagrid("option", "onClickRow", function(index, data) {
		selectedNode = data;
	});
	/** ***********************菜单树结构start****************************** */
	var settingMenuTree = {
		treeDataUrl : 'menu/menuList',
		data : {
			key : {
				name : "name"
			},
			simpleData : {
				enable : true,
				idKey : 'menuId',
				pIdKey : 'parentId'
			}
		},
		view : {
			// common.js 中公共的方法,给搜索选中的节点加深颜色
			fontCss : getFontCss
		},
		callback : {
			// 单击节点
			onClick : function(event, treeId, treeNode) {
				menuDataGrid.datagrid("refresh",null,{filter_parentId:treeNode.menuId});
			}
		}
	};
	var initMenuTree = function() {
		$.getJSON('menu/menuList', function(data) {
			if (data.length <= 0) {
				return;
			}
			$.fn.zTree.init($(ctx).find("#menuTree"), settingMenuTree, data);
			menuTree = $.fn.zTree.getZTreeObj("menuTree");
		});
	};
	initMenuTree();// 第一次加载调用
	/** **************以下是查询树节点数据的具体步骤 common.js 中公共的方法************ */
	$(ctx).find("#keyword").on("keyup", function() {
		searchNode('name', $(this), menuTree);
	}).on("blur", function() {
		blurKey();
	});

	/** ***********************菜单树结构end****************************** */
	var modelDialog = function(options){
		var settings = {
			title:'',
			url : '',
			requestParam : ''
		};
		$.extend(true, settings, options); // true深度拷贝
		$.modal({
			title : settings.title,
			width:615,
        	height:440,
        	remote:settings.url,
        	requestParam:settings.requestParam,
        	ready:function(event, context){
        		//弹出框口页面的初始化操作
        	},
        	singleSelect:true,
			buttons : [ {
				text : btn.save,
				cls:"btn-primary",
				click : function() {
					var $this = $(this);
					$this.find("form").trigger("submit", {
						success : function(data) {
							initMenuTree();
							menuDataGrid.datagrid("refresh",null,null);
							$this.modal("close");
						}
					});
				}
			},{	text:btn.cancel,
				click:function(){
				$(this).modal("close");
			}}]
		});
	} ;
	menuDataGrid.datagrid("register", "add", function() {
		var options = {
			title:i18nMenu.txt.add,
			url : 'menu/addForm'
		};
		modelDialog(options);
	});
	menuDataGrid.datagrid("register", "edit", function() {
		var selectItem = this.getSelect();
		var options = {
			title:i18nMenu.txt.edit,
			url : 'menu/editForm',
			requestParam:{menuId:selectItem.menuId}
		};
		if(selectItem.menuId) {
			modelDialog(options);
		} else {
			pousheng.warnMsg("请选择一条菜单记录进行编辑。");
		}
	});
	menuDataGrid.datagrid("register", "delete", function() {
		var that = this;
		pousheng.confirm(i18nMenu.msg.confirmdelete, function(r){
            if (r) {
            	var selectItem = that.getSelect();
				pousheng.ajaxData("menu/delete",{data:{menuId:selectItem.menuId}}).done(function(){
					menuDataGrid.datagrid("refresh",null,null);
					var deleteNode = menuTree.getNodeByParam("name", selectItem.name,null);
					menuTree.removeNode(deleteNode);
				});
            }
        });
	});
	
	menuDataGrid.datagrid("register", "view", function() {
		var selectItem = this.getSelect();
			$.modal({
			title : i18nMenu.txt.view,
			width:615,
        	height:440,
        	remote:"menu/editForm",
        	requestParam:{menuId:selectItem.menuId},
        	ready:function(event, context){
        		//弹出框口页面的初始化操作
				if(selectItem.parentId=='') {
					$(context).find("#parentId").text(''); //如果没有上级菜单，则显示空字符
				} else {
					$(context).find("#parentId").val(selectItem.parentId);
				}
        		$(context).viewform(); //先替换基本的标签
        	},
			buttons : [{text:btn.cancel,
				click:function(){
				$(this).modal("close");
			}}]
		});
	});
});