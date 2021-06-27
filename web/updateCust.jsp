<%-- 
    Document   : updateCust
    Created on : Jun 18, 2021, 11:34:06 AM
    Author     : nguoitamxa
--%>

<%@page import="entity.Customer"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
    </head>   
    <body>
        <%
            Customer cus = (Customer) request.getAttribute("acc");
            String title = request.getAttribute("tieude").toString();
        %>
        <form action="ControllerCustomer" method="GET">
            <table border="0">      
                <tr>
                    <td>ID</td>
                    <td><input type="hidden" name="id" value="<%=cus.getCid()%>"/><%=cus.getCid()%></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="Name" value="<%=cus.getCname()%>" /></td>
                </tr>
                <tr>
                    <td>Phone</td>
                    <td><input type="text" name="phone" value="<%=cus.getCphone()%>" /></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><input type="text" name="address" value="<%=cus.getcAddress()%>" /></td>
                </tr>

                <tr>
                    <td>Username</td>
                    <td><input type="text" name="user" value="<%=cus.getUsername()%>" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="text" name="pass" value="<%=cus.getPassword()%>" /></td>
                </tr>
                <tr>
                    <td>Status</td>
                    <%if ((cus.getStatus() == 1)) {%>

                    <td> 
                        <input type="radio" name="st" value="<%=cus.getStatus()%>"  checked/>Enable
                        <input type="radio" name="st" value="0" />Disable
                        <%} else {%>

                    <td> 
                        <input type="radio" name="st" value="1" />Enable
                        <input type="radio" name="st" value="<%=cus.getStatus()%>" checked/>Disable
                        <%}%>
                </tr>
                <tr><td><input type="submit" name="service" value="update" /></td></tr>
            </table>
        </form>
    </body>
</html>
