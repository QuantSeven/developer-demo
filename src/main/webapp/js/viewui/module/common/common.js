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
		pageUrl : "common/selectIndex",
		requestParam : {},
		title : txt.selectuser,
		width : 590,
		height : 480,
		dataGridSettings:{
			dataUrl:'',
			singleSelect : true,
			pagination :true,
			queryParams: {}
		},
		treeSettings : {
			treeDataUrl : '',
			name :'',
			idKey: '',
			pIdKey:''
		}
	};
	$.extend(true, settings, options); //true深度拷贝
	var model = $.modal({
		title : settings.title,
		remote : settings.pageUrl,
		requestParam : settings.requestParam,
		width : settings.width,
		height : settings.height,
		resizable : false,
		newwinBtn : false,
		ready : function(et, cxt) {
			var grid = null,commonTree = null;
			$(cxt).find(".widget-box").livequery(function(){
				var height = $(".modal-body div:first-child").height()-40;
				$(cxt).find(".widget-content").height(height);
				$(cxt).find(".widget-content").triggerHandler("_resize"); 
			});
			$(cxt).find(".changeView").on("click",".viewType",function(){
				if($(this).hasClass("list_style_a_on") || $(this).hasClass("list_style_b_on")){
					return false;
				}
				if($(this).hasClass("list_style_a")){
					$(this).addClass("list_style_a_on");
					$(this).siblings().removeClass("list_style_b_on");
				} else if($(this).hasClass("list_style_b")) {
					$(this).addClass("list_style_b_on");
					$(this).siblings().removeClass("list_style_a_on");
				}
				$(cxt).find(".widget-content").slideToggle();
			});
			//如果有dataGrid设置
			if(settings.dataGridSettings && settings.dataGridSettings.dataUrl){
				grid = $(cxt).find("#commonDataGridList").datagrid({
					singleSelect : settings.dataGridSettings.singleSelect,
					url : settings.dataGridSettings.dataUrl,
					pagination:settings.dataGridSettings.pagination
				});
				grid.datagrid("register","searchBtn",function(){
					 $.extend(this.opts.queryParams, this.opts.search.getFieldValues() || {});
			         this.refresh(null,this.opts.queryParams);
				});
				if (settings.dataGridSettings.singleSelect) {
					$(cxt).find("#checkbox").remove();
					grid.datagrid("option", "onDblClickRow", function(index, data) {
						$.isFunction(callback) && callback.call(that, data);
						$(cxt).modal("close");
					});
				}
			} else {
				$(cxt).find("#dataGridContent").remove(); 
				$(cxt).find("#treeContent").show();
				$(cxt).find("#treeView").addClass("list_style_a_on");
				$(cxt).find("#gridView").remove(); 
			}
			//如果有树形的结构
			if(settings.treeSettings && settings.treeSettings.treeDataUrl){
				var settingTree = {
					data : {
						key : {name : settings.treeSettings.name},
						simpleData : {enable : true, idKey : settings.treeSettings.idKey, pIdKey : settings.treeSettings.pIdKey}
					},
					view: {fontCss: getFontCss},
					callback : {
						onDblClick: function(event, treeId, treeNode){
							$.isFunction(callback) && callback.call(that, treeNode);
							$(cxt).modal("close");
						}
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
				/***********************以下是查询树节点数据的具体步骤 common.js 中公共的方法*************/
			 	$(cxt).find("#keyword").on("keyup",function(){
			 		searchNode(settings.treeSettings.name, $(this), commonTree);
			 	}).on("blur",function(){
			 		blurKey();
			 	});
			 	/*********************************** 是查询树节点数据 end**************************************/
			} else {
				$(cxt).find("#treeContent").remove(); 
				$(cxt).find("#treeView").remove(); 
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
var key = null, currentTree = null, nodeList = [];

/**
 * 查询节点方法
 * 
 * @param inputField
 *            文本框控件
 * @param tree
 *            当前的树的id
 */
function searchNode(fileName, inputField, tree) {
	key = inputField;
	currentTree = tree;
	var value = $.trim(key.get(0).value);
	if (value === "") {
		setHighLight(false);
		setExpandNode(false);
		return;
	}
	setHighLight(false);
	nodeList = currentTree.getNodesByParamFuzzy(fileName, value);// 根据组织名称查询
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
			currentTree.expandNode(nodeList[i].getParentNode(), true, false, false);
		} else {
			currentTree.expandNode(nodeList[i].getParentNode(), false, false, false);
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
		currentTree.updateNode(nodeList[i]);
	}
}

/**
 * 鼠标移开的时候触发
 * 
 * @param e
 *            事件
 */
function blurKey(e) { 
	if(key){
		if (key.get(0).value === "") {
			setHighLight(false);
		}
	}
}
/**
 * 为查询的字符着色
 * 
 * @param treeId
 *            当前的树的id
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
