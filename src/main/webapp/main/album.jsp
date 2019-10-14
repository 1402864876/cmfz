<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="cc" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="app" value="${pageContext.request.contextPath}"></c:set>
<script>
    $(function () {
        $("#users").jqGrid({
            styleUI: "Bootstrap",
            url: "${app}/album/queryPage",
            datatype: "json",
            colNames:["ID","标题","评分","作者","播音","集数","发布时间","简介","封面路径"],   //表格标题
            autowidth: true,
            caption: "专辑信息",
            pager: "#page",
            rowNum: 2,
            viewrecords: true,
            align:"center",
            rowList: [2, 4, 6],
            editurl: "${app}/album/edit",
            cellEdit: false,
            toolbar: [true, "top"],
            multiselect:true,
            height:"69%",
            subGrid : true,
            subGridRowExpanded:function (subgrid_id, albumId) {
                addSubGrid(subgrid_id,albumId);
            },
            colModel: [
                {name: "id",align:"center"},
                {name: "title", editable: true,align:"center"},
                {name:"score",editable: true,align:"center"},
                {name:"author", editable: true,align:"center"},
                {name:"beam",align:"center",editable: true},
                {name: "count"},
                {name: "publish_date"},
                {name: "content", editable: true},
                {name: "cover", editable: true,edittype:"file",
                    formatter:function (cellvalue, options, rowObject) {
                        return "<img style='width:150px;height:50px' src='${pageContext.request.contextPath}/img/"+cellvalue+"'/>"}
                }
            ],
        }).jqGrid(
            "navGrid","#page",
            {
                //在处理页面几个按钮的样式
                search:false
            },
            {
                //在编辑之前或者之后进行的额外的操作 beforeShowForm afterSubmit
                closeAfterEdit:true,
                beforeShowForm:function (obj) {
                    obj.find("#cover").attr("disabled",true)
                }
            },
            {
                //在添加数据之前或者之后进行的额外的操作
                closeAfterAdd:true,
                afterSubmit:function (response) {
                    var banner = response.responseText;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/album/updateCover",
                        fileElementId:"cover",
                        data:{id:banner},
                        success:function (data) {
                            $("#users").trigger("reloadGrid")
                        }
                    });
                    return response;
                }
            },
            {
                closeAfterDel:true
                //在删除数据之前或者之后进行的额外的操作
            }
        )
        $("#t_users").append("<button class=\"btn btn-primary\" id=\"btn\">添加</button>");
    });
    function addSubGrid(subgrid_id,albumId){
        var tableId = subgrid_id+"table";
        var divId = albumId+"div";
        $("#"+subgrid_id).html(
            "<table id='"+tableId+"'></table>"+"<div id='"+divId+"'></div>"
        )
        $("#"+tableId).jqGrid({
            styleUI: "Bootstrap",
            url: "${app}/chapter/queryPage?id="+albumId,
            datatype: "json",
            colNames:["标题","大小","时长","音频","状态","操作"],   //表格标题
            autowidth: true,
            caption: "章节信息",
            pager: "#"+divId,
            rowNum: 2,
            viewrecords: true,
            align:"center",
            rowList: [2, 4, 6],
            editurl: "${app}/chapter/edit?album_id="+albumId,
            multiselect:true,
            height:"67%",
            colModel: [
                {name: "title",align:"center", editable: true},
                {name: "size", align:"center"},
                {name:"time",align:"center"},
                {name:"filePath", editable: true,align:"center",
                    edittype:"file"
                },
                {name:"status",align:"center",editable: true,
                    edittype:"select",
                    editoptions:
                        {
                            value:"1:正在展示;2:不在展示"
                        },
                    formatter:function(cellvalue, options, rowObject){
                        if(rowObject.status==1){
                            return "正在展示"
                        }
                        if(rowObject.status==2){
                            return "不在展示"
                        }
                    }
                },
                {
                    name:"",
                    formatter:function (cellValue, options, rowObject) {
                        return "<a href='${pageContext.request.contextPath}/mp3/"+rowObject.filePath+"'>    <span class='glyphicon glyphicon-play-circle'></span></a>"+"                  "+
                            "<a href='${pageContext.request.contextPath}/chapter/download?filePath="+rowObject.filePath+"'><span class='glyphicon glyphicon-download'></span></a>"
                    }
                }
            ],
        }).jqGrid(
            "navGrid","#"+divId ,
            {
                //在处理页面几个按钮的样式
                search:false
            },
            {
                //在编辑之前或者之后进行的额外的操作 beforeShowForm afterSubmit
                closeAfterEdit:true,
                beforeShowForm:function (obj) {
                    obj.find("#filePath").attr("disabled",true)
                }
            },
            {
                //在添加数据之前或者之后进行的额外的操作
                closeAfterAdd:true,
                afterSubmit:function (response) {
                    var banner = response.responseText;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/chapter/upload",
                        fileElementId:"filePath",
                        data:{id:banner},
                        success:function (data) {
                            $("#users").trigger("reloadGrid")
                        }
                    });
                    return response;
                }
            },
            {
                //在删除数据之前或者之后进行的额外的操作
                closeAfterDel:true,
                afterSubmit:function ()  {
                    $("#"+tableId).trigger("reloadGrid");
                    $("#users").trigger("reloadGrid");
                }
            }
        )
    }
</script>
<table id="users"></table>
<div id="page"></div>