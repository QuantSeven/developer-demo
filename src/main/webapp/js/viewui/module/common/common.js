/***************************组织架构 选择用户*******************************/

/**
 * *组织架构 选择用户
 * 
 * @param callback
 *            回调函数
 * @param single
 *            是否多选（true:单选，false:多选）
 * @param {String}
 *            [url] 请求页面的url
 * @param param
 *            参数
 * @param title
 *            标题
 */
org.selectUser = function(callback, single, url, param, title) {
	var that = this;
	var model = $.modal({
		title : title || txt.selectuser,
		remote : url || "common/userIndex",
		requestParam : param,
		width : 590,
		height : 480,
		resizable : false,
		ready : function(et, cxt) {
			var grid = $(cxt).find("#commonUserList").datagrid({
				singleSelect : single
			});
			if (single) {
				$(cxt).find("#checkbox").remove();
				grid.datagrid("option", "onDblClickRow", function(index, data) {
					if (!data.id) {
						pousheng.warnMsg(msg.noselect);
						return;
					}
					$.isFunction(callback) && callback.call(that, data);
					$(cxt).modal("close");
				});
			}
		},
		buttons : [ {
			text : btn.ok,
			cls : "btn-primary",
			click : function() {
				var $elem = $(this);
				var userItems = $elem.find("#commonUserList").datagrid("getSelectedRows");
				if (!userItems.length) {
					pousheng.warnMsg(msg.noselect);
					return;
				}
				$elem.modal("close");
				if ($.isFunction(callback) && single) {
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

/** ************************选择用户组******************************** */
/**
 * 选择用户组
 * 
 * @param callback
 *            回调函数
 * @param single
 *            是否多选（true:单选，false:多选）
 * @param url
 *            请求页面的url
 * @param param
 *            参数
 * @param title
 *            标题
 */
org.selectGroup = function(callback, single, url, param, title) {
	var that = this;
	var model = $.modal({
		title : title || txt.selectgroup,
		remote : url || "common/groupIndex",
		requestParam : param,
		width : 590,
		height : 480,
		resizable : false,
		ready : function(et, cxt) {
			var grid = $(cxt).find("#commonGroupList").datagrid({
				singleSelect : single
			});
			if (single) {
				$(cxt).find("#checkbox").remove();
				grid.datagrid("option", "onDblClickRow", function(index, data) {
					if (!data.id) {
						pousheng.warnMsg(msg.noselect);
						return;
					}
					$.isFunction(callback) && callback.call(that, data);
					$(cxt).modal("close");
				});
			}
		},
		buttons : [ {
			text : btn.ok,
			cls : "btn-primary",
			click : function() {
				var $elem = $(this);
				var groupItems = $elem.find("#commonGroupList").datagrid("getSelectedRows");
				if (!groupItems.length) {
					pousheng.warnMsg(msg.noselect);
					return;
				}
				$elem.modal("close");
				if ($.isFunction(callback) && single) {
					callback.call(that, groupItems[0]);
				} else if ($.isFunction(callback)) {
					callback.call(that, groupItems);
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
function contains(code, ary) {
	var result = false;
	if(ary){
		$.each(ary,function(i, item){
			if(code===item.code){
				result = true;
			}
		});
	}
	return result;
};
/** ------------------表达式高级设置-------------------- */
advanced.selectExpression = function(callback, displayValue, selectVarData) {
	var that = this, returnData = {}, isValidation = false;
	var model = $.modal({
		title : "条件表达式",
		remote : "common/advancedIndex",
		width : 690,
		height : 550,
		ready : function(event, context) {
			var showVarValue = $(context).find("#showVarValue").val(displayValue); // 初始化显示
			var settingFlowVar = {
				data : {
					simpleData : {
						enable : true,
						idKey : "code",
						pIdKey : 'category'
					}
				},
				callback : {
					onClick : onSingleClick,
					onDblClick : onFlowVarTreeDbClick
				}
			};
			var initFlowVarTree = function(item) {
				$.post("flowvar/getFlowVarTree", function(data) {
					var nodes = [
			             /*--自定义函数--*/
			             {code:'0',name:'自定义函数',type:'Function'},
			             {key:'getOrgManagers',value:'getOrgManagers()',code:'getOrgManagers(String userId)',name:'获取行政部门主管',type:'Function', category:'0',returnType:'String[]',paramLength:1,description:'第1个参数表示用户账号'},
			             {key:'getOrgManagers',value:'getOrgManagers(,)',code:'getOrgManagers(String userId, String orgId)',name:'获取指定组织行政部门主管',type:'Function', category:'0',returnType:'String[]',paramLength:2,description:'参数1：用户账号；参数2：组织编号'},
			             {key:'getBusinessOrgManagers',value:'getBusinessOrgManagers()',code:'getBusinessOrgManagers(String userId)',name:'获取业务部门主管',type:'Function', category:'0',returnType:'String[]',paramLength:1,description:'参数1：用户账号'},
			             {key:'getBusinessOrgManagers',value:'getBusinessOrgManagers(,)',code:'getBusinessOrgManagers(String userId, String orgId)',name:'获取指定业务部门主管',type:'Function', category:'0',returnType:'String[]',paramLength:2,description:'参数1：用户账号；参数2：组织编号'},
			             {key:'getHeadSuperiors',value:'getHeadSuperiors()',code:'getHeadSuperiors(String userId)',name:'获取行政上级',type:'Function', category:'0',returnType:'String[]',paramLength:1,description:'参数1：用户账号'},
			             {key:'getHeadSuperiors',value:'getHeadSuperiors(,)',code:'getHeadSuperiors(String userId, String orgId)',name:'获取指定组织行政上级',type:'Function', category:'0',returnType:'String[]',paramLength:2,description:'参数1：用户账号；参数2：组织编号'},
			             {key:'getBusinessSuperiors',value:'getBusinessSuperiors()',code:'getBusinessSuperiors(String userId)',name:'获取业务上级',type:'Function', category:'0',returnType:'String[]',paramLength:1,description:'参数1：用户账号'},
			             {key:'getBusinessSuperiors',value:'getBusinessSuperiors(,)',code:'getBusinessSuperiors(String userId, String orgId)',name:'获取指定组织业务上级',type:'Function', category:'0',returnType:'String[]',paramLength:2,description:'参数1：用户账号；参数2：组织编号'},
			             {key:'getPrevAuditor',value:'getPrevAuditor()',code:'getPrevAuditor(String procInstId)',name:'获取前一参与者id',type:'Function', category:'0',returnType:'String',paramLength:1,description:'参数1：实例id'},
			             {key:'getPrevAuditor',value:'getPrevAuditor(,)',code:'getPrevStepAuditor(String procInstId, String executeId)',name:'获取前一步骤最后参与者id',type:'Function', category:'0',returnType:'String',paramLength:2,description:'参数1：实例id；参数2：executeId'},
			             {key:'getUserName',value:'getUserName()',code:'getUserName(String userId)',name:'获取用户名称',type:'Function', category:'0',returnType:'String',paramLength:1,description:'参数1：用户账号'},
			             {key:'getUserTitle',value:'getUserTitle()',code:'getUserTitle(String userId)',name:'获取用户职务',type:'Function', category:'0',returnType:'String',paramLength:1,description:'参数1：用户账号'},
			             {key:'getUserHierarchy',value:'getUserHierarchy()',code:'getUserHierarchy(String userId)',name:'获取用户层级',type:'Function', category:'0',returnType:'int[]',paramLength:1,description:'参数1：用户账号'},
			             /*--系统函数--*/
			             {code:'1',name:'系统函数',type:'Function'},
			             {key:'now',value:'now()',code:'now()',name:'当前日期和时间',type:'Function', category:'1',returnType:'String',description:'当前日期和时间，格式：yyyy-MM-dd HH:mm:ss'},
			             {key:'time',value:'time()',code:'time()',name:'当前时间',type:'Function', category:'1',returnType:'String',description:'当前时间，格式：HH:mm:ss'},
			             {key:'date',value:'date()',code:'date()',name:'当前日期',type:'Function', category:'1',returnType:'String',description:'当前日期，格式：yyyy-MM-dd'},
	
			             /*--转换函数--*/
			             {code:'2',name:'转换函数',type:'Function'},
			             {key:'cBool',value:'cBool()',code:'cBool(Object obj)',name:'转为Boolean',type:'Function', category:'2',returnType:'boolean',paramLength:1,description:'参数1：被转对象'},
			             {key:'cDouble',value:'cDouble()',code:'cDouble(Object obj)',name:'转为Double',type:'Function', category:'2',returnType:'double',paramLength:1,description:'参数1：被转对象'},
			             {key:'cLong',value:'cLong()',code:'cLong(Object obj)',name:'转为Long',type:'Function', category:'2',returnType:'long',paramLength:1,description:'参数1：被转对象'},
			             {key:'cInt',value:'cInt()',code:'cInt(Object obj)',name:'转为Integer',type:'Function', category:'2',returnType:'int',paramLength:1,description:'参数1：被转对象'},
			             {key:'cStr',value:'cStr()',code:'cStr(Object obj)',name:'转为String',type:'Function', category:'2',returnType:'String',paramLength:1,description:'参数1：被转对象'},
			             {key:'cDate',value:'cDate()',code:'cDate(Object obj)',name:'转为Date',type:'Function', category:'2',returnType:'Date',paramLength:1,description:'参数1：被转对象'}
					];
					$.each(nodes, function(i, item) {
						data.push(item);
					});
					$.fn.zTree.init($("#flowVarTree"), settingFlowVar, data);
				});
			};
			initFlowVarTree();
			function onSingleClick(event, treeId, treeNode) {
				if (treeNode.category) {
					if (treeNode.type === 'Function') {
						$(context).find("#describeValue").val("定义：" + treeNode.returnType + " " + treeNode.code + "\r\n" + "返回值：" + treeNode.returnType + "\r\n" + "描述：" + treeNode.description);
					} else {
						$(context).find("#describeValue").val("定义：" + treeNode.type + " " + treeNode.code + "\r\n" + "返回值：" + treeNode.type + "\r\n" + "描述：" + treeNode.description);
					}
				}
			};
			function onFlowVarTreeDbClick(event, treeId, treeNode) {
				if (treeNode.category) {
					if (treeNode.type === 'Function') {
						$(context).find("#showVarValue").insertAtCursor(" " + treeNode.value + " ");
					} else {
						$(context).find("#showVarValue").insertAtCursor(" " + treeNode.name + " ");
					}
					var flowVar = {
						code : treeNode.code,
						name : treeNode.name,
						type : treeNode.type,
						returnType : treeNode.returnType,
						key:treeNode.key
					};
					if(!contains(flowVar.code,selectVarData)){
						selectVarData.push(flowVar);
					}
				}
			};

			$(context).find("#operating").on("click", "li[class='ui-state-default ui-corner-all']", function() {
				var spanTxt = $(this).find("span").text();
				if (spanTxt === ' NOTLIKE ' || spanTxt === ' LIKE ') {
					var flowVar = {
						code : spanTxt+'(,)',
						type : 'Function'
					};
					spanTxt = spanTxt + '(,)';
					if(!contains(flowVar.code,selectVarData)){
						selectVarData.push(flowVar);
					}
				}
				$(context).find("#showVarValue").insertAtCursor(spanTxt);
			});
			$(context).find("#viewExpression").on("click", function() {
				var value = $(context).find("#showVarValue").val();
				if (selectVarData && selectVarData.length > 0) {
					$.each(selectVarData, function(i, item) {
						if (value.indexOf(item.name) > 0) {
							value = value.replace(new RegExp(" "+item.name +" ","gi"), item.code);
						}
					});
				}
				value = value.replace(/\ AND /g, "&&").replace(/\ OR /g, "||").replace(/\ NOT /g, "!");
				$(context).find("#expressionValue").val(value);
				$(context).find("#showVarValueDiv").slideToggle("normal");
				$(context).find("#expressionValueDiv").slideToggle("normal");
			});
			$(context).find("#viewBack").on("click", function() {
				$(context).find("#showVarValueDiv").slideToggle("normal");
				$(context).find("#expressionValueDiv").slideToggle("normal");
			});
			$(context).find("#reset").on("click", function() {
				$(context).find("#showVarValue").val("");
				selectVarData = [];
			});
		},
		buttons : [ {
			text : "验证",
			cls : "btn btn-success",
			click : function() {
				var value = $(this).find("#showVarValue").val();
				if (selectVarData &&  selectVarData.length > 0) {
					$.each(selectVarData, function(i, item) {
						if (item.type === 'Function') {
							if(value.indexOf('LIKE') > 0){
								value = value.replace(/\ LIKE /g, "conditionExpression.like");
							}
							if(value.indexOf('NOTLIKE') > 0){
								value = value.replace(/\ NOTLIKE /g, "conditionExpression.notLike");
							} 
							value = value.replace(new RegExp(" "+item.key,"gi"), "conditionExpression."+item.key);
						} else {
							if (value.indexOf(item.name) > 0) {
								value = value.replace(new RegExp(" "+item.name +" ","gi"), item.code);
							}
						}
					});
				}
				value = value.replace(/\ AND /g, "&&").replace(/\ OR /g, "||").replace(/\ NOT /g, "!").trim();
				if (value != "") {
					pousheng.ajaxData("common/validationExpression", {
						data : {
							"expression" : value,
							"varTypes" : JSON.stringify(selectVarData)
						}
					}).done(function(msg) {
						if (msg == "true") {
							isValidation = true;
							pousheng.successMsg("表达式验证通过，正确的表达式！");
						} else {
							pousheng.errorMsg("表达式验证错误,输入的表达式有误！");
						}
					});
				}
			}
		}, {
			text : btn.ok,
			cls : "btn btn-primary",
			click : function() {
				if(!isValidation){
					pousheng.warnMsg("请先验证正确的表达式！");
					return;
				}
				var value = $(this).find("#showVarValue").val();
				if (selectVarData && selectVarData.length > 0) {
					$.each(selectVarData, function(i, item) {
						if (item.type === 'Function') {
							if(value.indexOf('LIKE') > 0){
								value = value.replace(/\ LIKE /g, "conditionExpression.like");
							}
							if(value.indexOf('NOTLIKE') > 0){
								value = value.replace(/\ NOTLIKE /g, "conditionExpression.notLike");
							} 
							value = value.replace(new RegExp(" "+item.key,"gi"), "conditionExpression."+item.key);
						} else {
							if (value.indexOf(item.name) > 0) {
								value = value.replace(new RegExp(" "+item.name +" ","gi"), item.code);
							}
						}
					});
				}
				value = value.replace(/\ AND /g, "&&").replace(/\ OR /g, "||").replace(/\ NOT /g, "!").trim();
				returnData.displayValue = $(this).find("#showVarValue").val();
				returnData.conditionExpression = value;
				returnData.selectVarData = selectVarData;
				$(this).modal("close");
				if ($.isFunction(callback)) {
					callback.call(that, returnData);
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
 * *********************************以下是查询树节点数据的具体步骤
 * 参照zTree官网的方法*************************************
 */
var key = null, currentOrgTree = null;
nodeList = [];

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
/** jquery扩展 */
$.fn.extend({
	insertAtCursor : function(myValue) {
		var $t = $(this)[0];
		if (document.selection) {
			this.focus();
			sel = document.selection.createRange();
			sel.text = myValue;
			this.focus();
		} else if ($t.selectionStart || $t.selectionStart == '0') {
			var startPos = $t.selectionStart;
			var endPos = $t.selectionEnd;
			var scrollTop = $t.scrollTop;
			$t.value = $t.value.substring(0, startPos) + myValue + $t.value.substring(endPos, $t.value.length);
			this.focus();
			$t.selectionStart = startPos + myValue.length;
			$t.selectionEnd = startPos + myValue.length;
			$t.scrollTop = scrollTop;
		} else {
			this.value += myValue;
			this.focus();
		}
	}
});
