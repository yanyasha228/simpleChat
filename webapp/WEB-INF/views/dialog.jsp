<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chat Page</title>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/chatScript.js"></script>
    <script>
        var socket = new WebSocket('ws://' + window.location.host + '/room?user1=${user1}&user2=${user2}&whos=${whos}');
        socket.onopen = function (event) {
            //alert("Соединение открылось");
        };
        socket.onclose = function () {
            // alert("Соединение закрылось");
        };
        socket.onmessage = function (event) {
            var   xmlDoc, message, value;
            message = event.data;

            xmlDoc = parseXmlFromString(message);


            if(xmlDoc.getElementsByTagName("message").item(0).attributes.getNamedItem("textofthemessage").nodeValue != ""){
                messagesDequeHandler(xmlDoc);
            }

        };

    </script>
</head>
<body bgcolor="#E8E8E8">

<div style="background-image:url(${pageContext.request.contextPath}/images/texture.jpg); background-repeat:repeat; width: 100%; height: 150px">

    <img src="${pageContext.request.contextPath}/images/logo.png" width="228" height="150"
         style="margin: 0px 0px 0px 0px">

</div>

<h1 style="color: #4e4e4e; text-align: left;">Dialog between ${user1} & ${user2}</h1>

<input type="submit" onclick="end();" value="End the dialog"/>

<div style="width: 80%; padding: 5px; margin-left: 20%;">
    <%--MAIN--%>


        <table border="0">
            <tr>
                <td>
                    <input type="text" name="login" value="" size="90" id="inputtext"/>
                    <button type="submit" onclick="socket.send(readUsersInput());">
                        <img src="${pageContext.request.contextPath}/images/send.png" width="56" height="16" style="margin: 0px 0px 0px 0px" alt="Send">
                    </button>
                </td>
            </tr>
            <tr>
                <td>
                    <span id='mainChatTextArea'></span>
                </td>
            </tr>
        </table>
</div>


</body>
</html>

