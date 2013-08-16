$("document").readyfn(function(){
    var that = this;
    var menuList = $("#menuList", that).datagrid({url:"menu/listData"});
    var menuForm = $("<div id='menuForm'></div>");

    /*****************************双击查看 操作日志********************************************/
    menuList.datagrid("setOption","onDblClickRow",function(index, data){
        //this.grid.ajaxLoad("log/view",{data:{"logId":data.logId}});//双击 列表选项时
       /* $("#indexTab").navTab("load","menu/view",{"menuId":data.menuId},function(){
            $(this).viewform();
        });*/
    });

    /*****************************搜索 操作日志********************************************/
    menuList.datagrid("register","search",function(){
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
        if(startDate != null && startDate != ""){
            if(endDate ==  null || endDate == ""){
                pousheng.warnMsg("请填写结束时间!");
                return false;
            }
        }
        if(endDate != null && endDate != ""){
            if(startDate ==  null || startDate == ""){
                pousheng.warnMsg("请填写开始时间!");
                return false;
            }
        }
        if(startDate > endDate){
            pousheng.warnMsg("开始时间比结束时间晚!");
            return false;
        }

        $.extend(this.opts.queryParams, this.opts.search.getFieldValues() || {});
        this.refresh(null,this.opts.queryParams);
    });

});