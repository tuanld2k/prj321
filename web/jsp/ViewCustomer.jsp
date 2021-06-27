e<%-- 
    Document   : viewCustomer
    Created on : Jun 15, 2021, 4:27:55 PM
    Author     : nguoitamxa
--%>

<%@page import="entity.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
    </head>   
    <body>
        <% // get data from servlet (controller)
            ResultSet rs = (ResultSet) request.getAttribute("rs");
            ArrayList<Customer> arr
                    = (ArrayList<Customer>) request.getAttribute("list");
            String title = request.getAttribute("tieude").toString();
        %>
        <table border="1">
            <caption><%=title%></caption>
            <thead>
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Username</th>
                    <th>status</th>
                    <th>update</th>
                    <th>delete</th>
                </tr>
            </thead>
            <tbody>
                <%for (Customer cus : arr) {%>
                <tr>
                    <td><%=cus.getCid() %></td>
                    <td><%=cus.getCname()%></td>
                    <td><%=cus.getCphone()%></td>
                    <td><%=cus.getcAddress()%></td>
                    <td><%=cus.getUsername()%></td>
                    <td><%=(cus.getStatus() == 1 ? "Enable" : "Disable")%></td>
                    <td><a href="ControllerCustomer?service=update&id=<%=cus.getCid() %>">update</a></td>
                    <td><a href="ControllerCustomer?service=delete&id=<%=cus.getCid() %>">delete</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
