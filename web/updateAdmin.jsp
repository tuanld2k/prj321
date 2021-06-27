<%-- 
    Document   : updateAdmin
    Created on : Jun 19, 2021, 1:17:30 PM
    Author     : nguoitamxa
--%>

<%@page import="entity.Admin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%
            Admin acc = (Admin) request.getAttribute("acc");
            String title = request.getAttribute("tieude").toString();
        %>
        <form action="ControllerAdmin" method="GET">
            <table border="0">
                <caption><%=title%></caption>
                <tbody>
                    <tr>
                        <td>ID</td>
                        <td><input type="hidden" name="id" value="<%=acc.getAdminID()%>"/><%=acc.getAdminID()%></td>
                    </tr>
                    <tr>
                        <td>User Name</td>
                        <td><input type="text" name="username" value="<%=acc.getUserName()%>"/></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="text" name="password" value="<%=acc.getPassowrd()%>"/></td>
                    </tr>
                    <tr><input type="submit" name="service" value="update"></tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
