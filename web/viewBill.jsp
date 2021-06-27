<%-- 
    Document   : viewBill
    Created on : Jun 16, 2021, 10:42:38 AM
    Author     : nguoitamxa
--%>

<%@page import="entity.Bill"%>
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
            ArrayList<Bill> arr = (ArrayList<Bill>) request.getAttribute("list");
            String title = request.getAttribute("tieude").toString();
        %>
        <table border="1">
            <caption><%=title%></caption>
            <thead>
                <tr>
                    <th>oID</th>
                    <th>Date</th>
                    <th>Cname</th>
                    <th>Cphone</th>
                    <th>CAddress</th>
                    <th>Total</th>
                    <th>Status</th>
                    <th>BillDetails</th>
                </tr>
            </thead>
            <tbody>

                <!--                private String oID,dateCreate,cname,cphone,cAddress;
                    private double total;
                    private int status,cid;-->


                <%for (Bill cus : arr) {%>
                <tr>
                    <td><%=cus.getoID()%></td>
                    <td><%=cus.getDateCreate()%></td>
                    <td><%=cus.getCname()%></td>
                    <td><%=cus.getCphone()%></td>
                    <td><%=cus.getcAddress()%></td>
                    <td><%=cus.getTotal()%></td>
                    <td><%=(cus.getStatus() == 1 ? "Enable" : "Disable")%></td>
<!--                    <td><a href="ControllerCustomer?service=update&id=<%=cus.getoID()%>">update</a></td>
                    <td><a href="ControllerCustomer?service=delete&id=<%=cus.getoID()%>">delete</a></td>-->
                    <td><a href="ControllerBillDetail?service=listBillDetail&oID=<%=cus.getoID()%>">BillDetails</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
