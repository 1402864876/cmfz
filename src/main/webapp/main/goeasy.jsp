<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script src="../boot/js/jquery-2.2.1.min.js"></script>
    <script>
        var goEasy = new GoEasy({
            appkey: "BC-b0e8ecd689ab44baa461d1946d81c058"
        });
        //GoEasy-OTP可以对appkey进行有效保护,详情请参考​ ​
        goEasy.subscribe({
            channel: "my_channel",
            onMessage: function (message) {
                alert("Channel:" + message.channel + " content:" + message.content);
            }
        });
        function speak(){
            var content = $("#ss").val();
            $.ajax({
                url:"${pageContext.request.contextPath}/user/goEasy",
                data:"content="+content,
                type:"post",
                datatype:"JSON",
                success:function (data) {
                    $("<p></p>").text()
                }
            })
        }
    </script>
</head>
<body>
    HELLO WORlD <br><br>
    输入框 : <input type="text"  id="ss">
             <input type="submit" onclick="speak()" value="发送">
</body>
</html>