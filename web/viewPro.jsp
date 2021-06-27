<%-- 
    Document   : viewPro
    Created on : Jun 16, 2021, 8:42:14 AM
    Author     : nguoitamxa
--%>

<%@page import="entity.Product"%>
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
         ArrayList<Product> arr = (ArrayList<Product>) request.getAttribute("list");
         String title = request.getAttribute("tieude").toString(); 
        %>
        <table border="1">
            <caption><%=title%></caption>
            <thead>
                <tr>
                    <th>pid</th>
                    <th>pname</th>
                    <th>quantity</th>
                    <th>price</th>
                    <th>image</th>
                    <th>description</th>
                    <th>status</th>
                    <th>cateID</th>
                    <th>update</th>
                    <th>delete</th>
                </tr>
            </thead>
            <tbody>
                <%for (Product cus : arr) {%>
                <tr>
                    <td><%=cus.getPid() %></td>
                    <td><%=cus.getPname()%></td>
                    <td><%=cus.getQuantity()%></td>
                    <td><%=cus.getPrice()%></td>
                    <td><img src="<%=cus.getImage()%>"/></td>
                    <td><%=cus.getDescription()%></td>
                    <td><%=(cus.getStatus() == 1 ? "Enable" : "Disable")%></td>
                    <td><%=cus.getCateID()%></td>
                    <td><a href="ControllerProduct?service=preUpdate&id=<%=cus.getPid() %>">update</a></td>
                    <td><a href="ControllerProduct?service=delete&pid=<%=cus.getPid() %>">delete</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
