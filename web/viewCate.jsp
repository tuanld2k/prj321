<%-- 
    Document   : viewCate
    Created on : Jun 16, 2021, 2:43:21 AM
    Author     : nguoitamxa
--%>
<%@page import="entity.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%
            ResultSet rs = (ResultSet) request.getAttribute("rs");
            ArrayList<Category> arr = (ArrayList<Category>) request.getAttribute("list");
            String title = request.getAttribute("tieude").toString();
        %>
        <table border="1">
            <caption><%=title%></caption>
            <thead>
                <tr>
                    <th>cateID</th>
                    <th>cateName</th>
                    <th>status</th>
                    <th>update</th>
                    <th>delete</th>
                </tr>
            </thead>
            <tbody>
                <%for (Category cus : arr) {%>
                <tr>
                    <td><%=cus.getCateID()%></td>
                    <td><%=cus.getCateName()%></td>
                    <td><%=(cus.getStatus() == 1 ? "Enable" : "Disable")%></td>
                    <td><a href="ControllerCategory?service=preUpdate&id=<%=cus.getCateID()%>">update</a></td>
                    <td><a href="ControllerCategory?service=delete&id=<%=cus.getCateID()%>">delete</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
