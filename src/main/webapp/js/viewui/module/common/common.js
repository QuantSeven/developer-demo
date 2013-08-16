/**
 * *公共的弹出框口列表显示页面
 * 
 * @param options
 *            参数
 * @param callback
 *            回调函数
 * 
 */
common.select = function(options, callback) {
	var that = this;
	var settings = {
		singleSelect : true,
		pageUrl : "common/userIndex",
		param : {},
		title : txt.selectuser,
		width : 590,
		height : 480,
		dataUrl : '',
		pagination :true
	};
	$.extend(settings, options);
	var model = $.modal({
		title : settings.title,
		remote : settings.pageUrl,
		requestParam : settings.param,
		width : settings.width,
		height : settings.height,
		resizable : false,
		newwinBtn : false,
		ready : function(et, cxt) {
			var grid = $(cxt).find("#commonDataGridList").datagrid({
				singleSelect : settings.singleSelect,
				url : settings.dataUrl
			});
			if (settings.singleSelect) {
				$(cxt).find("#checkbox").remove();
				grid.datagrid("option", "onDblClickRow", function(index, data) {
					$.isFunction(callback) && callback.call(that, data);
					$(cxt).modal("close");
				});
			}
			//如果有树形的结构
			if(settings.treeSettings){
				var settingTree = {
					data : {
						key : {name : settings.treeSettings.name},
						simpleData : {enable : true,idKey : settings.treeSettings.idKey,pIdKey : settings.treeSettings.pIdKey}
					},
					view: {fontCss: getFontCss},
					callback : {
						//onClick : showUserListAndPosition,
						//onDblClick: viewInfo
					}
				};
				var initAllTree = function() {
					$.getJSON(settings.treeSettings.treeDataUrl, function(data) {
						if(data.length <= 0){
							return;
						}
						$.fn.zTree.init($("#commonTree"), settingTree, data);
						commonTree =  $.fn.zTree.getZTreeObj("commonTree");
					});
				};
				initAllTree();// 第一次加载调用
			}
		},
		buttons : [ {
			text : btn.ok,
			cls : "btn-primary",
			click : function() {
				var $elem = $(this);
				var userItems = $elem.find("#commonDataGridList").datagrid("getSelectedRows");
				if (!userItems.length) {
					pousheng.warnMsg(msg.noselect);
					return;
				}
				$elem.modal("close");
				if ($.isFunction(callback) && settings.singleSelect) {
					callback.call(that, userItems[0]);
				} else if ($.isFunction(callback)) {
					callback.call(that, userItems);
				}

			}
		}, {
			text : btn.cancel,
			click : function() {
				$(this).modal("close");
			}
		} ]
	});

	return model;
};

/**
 * **************以下是查询树节点数据的具体步骤 参照zTree官网的方法******************
 */
var key = null, currentOrgTree = null, nodeList = [];

/**
 * 查询节点方法
 * 
 * @param inputField
 *            文本框控件
 * @param orgTree
 *            当前的组织架构树的id
 */
function searchNode(inputField, orgTree) {
	key = inputField;
	currentOrgTree = orgTree;
	var value = $.trim(key.get(0).value);
	if (value === "") {
		setHighLight(false);
		setExpandNode(false);
		return;
	}
	;
	setHighLight(false);
	nodeList = currentOrgTree.getNodesByParamFuzzy("orgName", value);// 根据组织名称查询
	setHighLight(true);
	setExpandNode(true);

};

/**
 * 打开模糊查询的节点
 * 
 * @param isExpand
 *            是否打开（true、false）
 */
function setExpandNode(isExpand) {
	for ( var i = 0; i < nodeList.length; i++) {
		if (isExpand) {
			currentOrgTree.expandNode(nodeList[i].getParentNode(), true, false, false);
		} else {
			currentOrgTree.expandNode(nodeList[i].getParentNode(), false, false, false);
		}
	}
}
/**
 * 高亮显示查询的字符
 * 
 * @param highlight
 *            是否高亮显示（true、false）
 */
function setHighLight(highlight) {
	for ( var i = 0; i < nodeList.length; i++) {
		nodeList[i].highlight = highlight;
		currentOrgTree.updateNode(nodeList[i]);
	}
}

/**
 * 鼠标移开的时候触发
 * 
 * @param e
 *            事件
 */
function blurKey(e) { //
	if (key.get(0).value === "") {
		setHighLight(false);
	}
}
/**
 * 为查询的字符着色
 * 
 * @param treeId
 *            当前的组织架构树的id
 * @param treeNode
 *            树节点
 * @returns {String} 高亮显示的颜色，字体大小等...
 */
function getFontCss(treeId, treeNode) {
	return (!!treeNode.highlight) ? {
		color : "#A60000",
		"font-weight" : "bold"
	} : {
		color : "#333",
		"font-weight" : "normal"
	};
}
