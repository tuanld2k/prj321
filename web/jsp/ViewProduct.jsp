<%-- 
    Document   : ViewProduct
    Created on : Jun 9, 2021, 9:56:29 AM
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
            DAOProduct dao = new DAOProduct();
            ResultSet rs = dbcon.getData("Select * from Product");
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Image</th>
                    <th>Description</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%while (rs.next()) {%>
                <tr>
                    <td><%=rs.getString(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                    <td><%=rs.getString(4)%></td>
                    <td><img src="<%=rs.getString(5)%>"/></td>
                    <td><%=rs.getString(6)%></td>
                    <td><%=rs.getString(7)%></td>
                    <td><%=rs.getString(8)%></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
