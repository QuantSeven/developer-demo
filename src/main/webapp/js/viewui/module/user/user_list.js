$("document").readyfn(function() {
	var userItem = null;
	var userListGrid = $("#userListGrid").datagrid();
	userListGrid.datagrid("option","onDblClickRow",function(index, item){
		$("#indexTab").navTab("load","user/view",{"userId":item.userId},function(){
			$(this).viewform();
		});
      
	});
});
