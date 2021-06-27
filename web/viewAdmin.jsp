<%-- 
    Document   : viewAdmin
    Created on : Jun 16, 2021, 3:14:48 AM
    Author     : nguoitamxa
--%>
<%@page import="entity.Admin"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%
            ArrayList<Admin> arr = (ArrayList<Admin>) request.getAttribute("list");
            String title = request.getAttribute("tieude").toString();
        %>
        <table border="1">
            <caption><%=title%></caption>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Pass</th>
                    <th>update</th>
                    <th>delete</th>
                </tr>
            </thead>
            <tbody>
                <%for (Admin cus : arr) {%>
                <tr>
                    <td><%=cus.getAdminID()%></td>
                    <td><%=cus.getUserName()%></td>
                    <td><%=cus.getPassowrd()%></td>
                    <td><a href="ControllerAdmin?service=preUpdate&id=<%=cus.getAdminID()%>">update</a></td>
                    <td><a href="ControllerAdmin?service=delete&id=<%=cus.getAdminID()%>">delete</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
