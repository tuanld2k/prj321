<%-- 
    Document   : details
    Created on : Jun 24, 2021, 10:11:25 PM
    Author     : nguoitamxa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <%
            String id = request.getParameter("id");
            Object value = session.getAttribute(id);
            if (value == null) {
                session.setAttribute(id, "1");
            } else {
                int count = Integer.parseInt(value.toString()) + 1;
                session.setAttribute(id, String.valueOf(count));

            }
        %>
        <h1>Item with id=<%=id%> was added to the Shopping Cart</h1>
        <h2><a href="showCart.jsp">Show Shopping Cart</h2>

    </body>
</html>
