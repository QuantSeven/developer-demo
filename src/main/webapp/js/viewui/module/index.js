
define(function(require, exports, module){
	
    /**
     * 左侧菜单区域
     */
    pousheng.ajaxData("menu/menuList").done(function(data){
        var setting = {
            id: "menuId",
            pId: "parentId"
        };
      
   
        var helperMenu = $("<div class='menu-helper'></div>");
		
		//$("#leftmenu").find("h3[aria-selected='true']").find("i.icon-chevron-right").removeClass("icon-chevron-right").addClass("icon-chevron-down");
        
        $("#leftmenu").html($("#leftMenuTemplate").render(pousheng.transformTozTreeFormat(data, setting))).accordion({
            header: "h3",
            heightStyle: "fill",
            icons: null,
			active:true,
			collapsible: true,
            beforeActivate: function(event, ui){
                helperMenu.hide();
				ui.newHeader.find("i:not(.menu1)").removeClass("icon-chevron-right").addClass("icon-chevron-down");
				ui.oldHeader.find("i:not(.menu1)").removeClass("icon-chevron-down").addClass("icon-chevron-right");
            }
        }).find("h3").mouseenter(function(){
            if ($(this).attr("aria-selected") == "false") {
                helperMenu.html($(this).next().clone().html()).show().appendTo($(this).closest(".group"));
                var pos = $(this).offset();
                var h = helperMenu.height();
                var $top = pos.top + h < $(window).height() ? pos.top : $(window).height() - h;
                helperMenu.animate({
                    left: pos.left + $("#leftmenu").width() - 1,
                    top: $top
                });
            }
        }).end().find(".group").mouseleave(function(){
            helperMenu.hide();
        }).end().sortable({
            axis: "y",
            handle: "h3",
            stop: function(event, ui){
                ui.item.children("h3").triggerHandler("focusout");
            }
        }).bind("_resize", function(){
            $(this).accordion("refresh");
        });
        
        
        
        $("#leftmenu .menu-nav").on("click", "a", function(event){
            event.preventDefault();
			$("#leftmenu").find("a.ui-state-active").removeClass("ui-state-active");
            var $this = $(this).addClass("ui-state-active");
            $("#indexTab").navTab("newTab",$this.attr("href"),$this.attr("title"))
        });
        
        helperMenu.on("click", "a", function(event){
            event.preventDefault();
            var $this = $(this);
            $this.closest(".group").children("h3").trigger("click")
			.next().find("[title='"+$this.attr("title")+"']").trigger("click");   
        });
    }); 
});










