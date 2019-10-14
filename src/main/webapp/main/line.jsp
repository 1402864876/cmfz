<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="margin:0 auto;width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '持名法洲用户注册折线图'
        },
        tooltip: {},
        legend: {
            data:['注册量']
        },
        xAxis: {
            data: ["今天","昨天","前两天","前三天","前四天","前五天","近一周"]
        },
        yAxis: {},
        series: [{
            name: '注册量',
            type: 'line'
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    $.ajax({
        url:"${pageContext.request.contextPath}/user/line",
        datatype:"json",
        type:"POST",
        success:function (da) {
            console.log(da);
            myChart.setOption({
                series:[{data:da}]
            });
        }
    })
</script>