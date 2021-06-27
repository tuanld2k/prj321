<%-- 
    Document   : ViewAdmin
    Created on : Jun 9, 2021, 9:59:03 AM
    Author     : nguoitamxa
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="model.DAOProduct"%>
<%@page import="model.DBConnect"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
      <body>
        <%
            DBConnect dbcon = new DBConnect();
           
            ResultSet rs = dbcon.getData("Select * from Admin");
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Admin ID</th>
                    <th>Name</th>
                    <th>Pass</th>               
                </tr>
            </thead>
            <tbody>
                <%while (rs.next()) {%>
                <tr>
                    <td><%=rs.getString(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                 
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
