/*!
 * BPMS 业务流程管理系统
 * @VERSION
 * 创建人 liezun.xiao
 * 创建时间 2013-5-9
 *
 * Depends:
 *	jquery.js
 */
define(function(require, exports, module) {
	
	window.pousheng =window.viewui;
	
	//申明样式为盒子模型
    $.boxModel = true;
   
      //注册全局方法
      pousheng.addMethod("getI18N",function(code){
    	  var result ={};
    	  pousheng.ajaxData("common/getI18N?prefix="+code,{async:false},function(data){
    		  $.map(data||{},function(v,i){
    			 list2json(v,i.replace(code+".",""),result);
    		  }); 
    	  });
    	  function list2json(val,key,slt){
    		    var k = key.split(".");
    			 if(k.length==1){
    			   slt[k[0]]=val;
    			 }else{
    				slt[k[0]]=slt[k[0]] ||{};
    				list2json(val,key.replace(k[0]+".",""),slt[k[0]]);
    			 }
    		 }
           return result;
           
      });
    
	
	//dom 就绪初始化系统默认参数
	$(function(){
		//初始化系统导航标签
		$("#indexTab").navTab({contextmenu:true,headerCls:"tabs-nav-header "});
		
		
		//初始化首页	
	    seajs.use("module/index"); 
	    
		//a 标签ajax 提交方法
		$("a[data-rel='ajax']").live("click",function(event) {
			event.preventDefault();
			$("#indexTab").navTab("load",$(this).attr("href"));
		});
		
			
		//初始化form   ajax提交
	   $("body").delegate("form:not(.ignore)", "submit", function(event,sittings) {
			var options = {
				beforeSubmit : function(formData, jqForm, options) {
					if(sittings && $.isFunction(sittings.beforeSubmit))sittings.beforeSubmit.call(jqForm);
				},
				success : function(response, statusText, xhr, jqForm) {
					var result = pousheng.jsonEval(response);
		
					if(sittings && $.isFunction(sittings.success))
						sittings.success.call(jqForm,result);
					
					if(result.statusCode && result.message && result.message != ""){
						pousheng.tipAlert(result.statusCode,result.message);
					}
					if (result.forwardUrl && result.forwardUrl != "") {
						 $("#indexTab").navTab("load",result.forwardUrl);
					}
				}
			};
			$(this).ajaxSubmit(options);
			return false;
		}); 
		
		
		
		
		
		/**
		* 动态绑定对象
        */
		$("form").livequery(function(){ 
            $(this).validate();
		}); 
		
			
		$("[data-layout]").livequery(function(){ 
			$(this).layout();
		}); 
		
		$("[data-tabs]").livequery(function(){ 
			$(this).tabs();
		}); 
		
		$("[data-panel]").livequery(function(){ 
			$(this).panel();
		}); 
			 $("input.date").livequery(function(){
			$(this).datepicker({dateFormat:$(this).attr("dateFormat")||"yy-mm-dd"});
		 });
			 
        $("input.time").livequery(function(){
            $(this).datetimepicker({
                timeFormat: $(this).attr("timeFormat") || "HH:mm",
                dateFormat: $(this).attr("dateFormat") || "yy-mm-dd"
            });
        });
		
	    $("[data-buttonset]").livequery(function(){
			$(this).buttonset();
		});
	    
	    $("[data-dropdown]").livequery(function(){ 
			$(this).dropdown();
		}); 
	  
	    $(window).resize(function(){
	    	
	    	$("body>div").triggerHandler("_resize");
	    });
	    
	    
	    
	
        $("[data-datagrid]").livequery(function(){
            var target = $(this).datagrid(pousheng.jsonEval($(this).attr("data-datagrid")));
            target.datagrid("register", "edit", function(element){
                var param = this.getSelect();
                console.log(param);
                if ($.isEmptyObject(param)) {
                    pousheng.warnMsg("请选择一条记录");
                    return;
                }
                 $("#indexTab").navTab("load",$(element).attr("href"),param);
            });
            
            target.datagrid("register", "add", function(element){
				     $("#indexTab").navTab("load",$(element).attr("href"));
            });
            
            target.datagrid("register", "view", function(element){
                 var param = this.getSelect();
                if ($.isEmptyObject(param)) {
                    pousheng.warnMsg("请选择一条记录");
                    return;
                }
      
                $("#indexTab").navTab("load",$(element).attr("href"),param,function(){
                	 $(this).children().viewform();
                });
                
            });
            
            
            target.datagrid("register", "delete", function(element){
				tab=this.getSelect();;
                var param = this.getSelectedRows();
				var that=this;
                if (!param.length > 0) {
                    pousheng.warnMsg("请选择一条记录");
                    return;
                }
                pousheng.confirm("你确定要删除所选记录，删除后将不可恢复", function(r){
                    if (r) {
                        if (that.opts.singleSelect == true) {
                            param = param[0];
                        }
                        else {
                            param = JSON.stringify(param);
                        }
                        pousheng.ajaxData($(element).attr("href"), {
                            data: param,
                            contentType:that.opts.singleSelect?null:"application/json"
                        }).done(function(){
                            that.refresh();
                        });
                    }
                });
            });
            
            target.datagrid("register", "search", function(element){
                $.extend(this.opts.queryParams, this.opts.search.getFieldValues() || {});
                this.refresh(null,this.opts.queryParams);
            });
        });
        
    
			
	});

	
	//添加系统客户端验证模块
	
	seajs.use("jQuery.validate",function(){
		
		$.metadata.setType("attr", "validate");
		  
		$.validator.setDefaults({
		        wrapper: "div",
		        errorElement: "span",
		        errorClass: "va_sp_txt",
		        ignore: ":hidden,.readonly",
		        onfocusin:$.noop, 
		        onkeyup:$.noop,
		        errorPlacement: function(error, element){
		            var pos = element.position();
		            actualWidth = element[0].offsetWidth, actualHeight = element[0].offsetHeight;
		            error.prepend('<span class="va_tips_warn"></span>').append('<span class="arr"></span><a class="tips_close"></a>').css({
		                top: pos.top - 30,
		                left: pos.left
		            }).addClass("validatebox").appendTo(element.parent());
		           error.find(".tips_close").click(function(){
		        	  error.hide();
		           });
		        }
		    });
	      $.extend($.validator.messages,pousheng.getI18N('validator.messages'));
	      seajs.use("module/common/validate");
	});
	
	//初始化js 中使用到的国际化语言部分
	$.extend(true,tipMsg,pousheng.getI18N('tip.messages'));

	var i18n_common = pousheng.getI18N('common');

	$.extend(true,btn,i18n_common.btn);
	
	$.extend(true,txt,i18n_common.txt);
	var i18n_msg = pousheng.getI18N('msg');
	$.extend(true,msg,i18n_msg);
	
	seajs.use("module/common/common");
      

});