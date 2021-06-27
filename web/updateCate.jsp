<%-- 
    Document   : updateCate
    Created on : Jun 18, 2021, 11:34:22 AM
    Author     : nguoitamxa
--%>

<%@page import="entity.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Category cus = (Category) request.getAttribute("acc");
            String title = request.getAttribute("tieude").toString();
        %>
        <form action="ControllerCategory" method="GET">
            <table border="0">
                <caption><%=title%></caption>
                <tr>
                        <td>ID</td>
                        <td><input type="hidden" name="id" value="<%=cus.getCateID()%>"/><%=cus.getCateID()%></td>
                    </tr>
                <tr>
                    <td>Cate Name</td>
                    <td><input type="text" name="username" value="<%=cus.getCateName()%>"/></td>
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
