<%-- 
    Document   : viewBIllDetail
    Created on : Jun 22, 2021, 8:04:13 PM
    Author     : nguoitamxa
--%>

<%@page import="entity.BillDetail"%>
<%@page import="entity.Bill"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%
            ArrayList<BillDetail> arr = (ArrayList<BillDetail>) request.getAttribute("list");
            String title = request.getAttribute("tieude").toString();
        %>
        <table border="1">
            <caption style="
                     color: red;
                     font-size: 160%;
                     font-weight: bold;
                     "><%=title%></caption>
            <thead>
                <tr>
                    <th>oID</th>
                    <th>cname</th>
                    <th>cphone</th>
                    <th>dateCreate</th>
                    <th>cAddress</th>
                    <th>pname</th>
                    <th>cateName</th>
                    <th>image</th>
                    <th>description</th>
                    <th>quantity</th>
                    <th>price</th>
                    <th>total</th>
                </tr>
            </thead>
            <tbody>

                <!--                private String oID,dateCreate,cname,cphone,cAddress;
                    private double total;
                    private int status,cid;-->


                <%for (BillDetail cus : arr) {%>
                <tr>
                    <td><%=cus.getoID()%></td>
                    <td><%=cus.getCname()%></td>
                    <td><%=cus.getCphone()%></td>
                    <td><%=cus.getDateCreate()%></td>
                    <td><%=cus.getcAdress()%></td>
                    <td><%=cus.getPname()%></td>
                    <td><%=cus.getCatename()%></td>
                    <td><img src="<%=cus.getImage()%>"/></td>
                    <td><%=cus.getDescription()%></td>
                    <td><%=cus.getQuantity()%></td>
                    <td><%=cus.getPrice()%></td>
                    <td><%=cus.getTotal()%></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
