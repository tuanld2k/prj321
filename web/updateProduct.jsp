<%-- 
    Document   : updateProduct
    Created on : Jun 18, 2021, 11:33:46 AM
    Author     : nguoitamxa
--%>
<%@page import="model.DBConnect"%>
<%@page import="model.DAOProduct"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
    </head>   
    <body>
        <%
            DBConnect dbcon = new DBConnect();
            DAOProduct dao = new DAOProduct();
            ResultSet rs = dbcon.getData("Select * from Category");
            Product cus = (Product) request.getAttribute("acc");
            String title = request.getAttribute("tieude").toString();
        %>
  
        <form action="ControllerProduct" method="GET">
            <table border="0">
                <tr>
                    <td>Product ID</td>
                    <td><input type="hidden" name="ProductID" value="<%=cus.getPid()%>" /><%=cus.getPid()%></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="Name" value="<%=cus.getPname()%>" /></td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td><input type="text" name="quantity" value="<%=cus.getQuantity()%>" /></td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td><input type="text" name="Price" value="<%=cus.getPrice()%>" /></td>
                </tr>
                <tr>
                    <td>image</td>
                    <td><input type="text" name="image" value="<%=cus.getImage()%>" /></td>
                </tr>
                <tr>
                    <td>description</td>
                    <td><input type="text" name="description" value="<%=cus.getDescription()%>" /></td>
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
                <tr>
                    <td><b> Choose Category Name:</b></td>
                    <td>
                        <select name="Cate">
                            <%
                                while (rs.next()) {
                                    if (Integer.parseInt(rs.getString(1)) == cus.getCateID()) {%>
                            <option value="<%=rs.getString(1)%>" selected> <%=rs.getString(2)%></option>
                            <%
                            } else {%>
                            <option value="<%=rs.getString(1)%>"><%=rs.getString(2)%></option>
                            <%}
                                }%>
                        </select>
                    </td>
                </tr>
                <tr><td><input type="submit" name="service" value="update" /></td></tr>
<!--                <tr>
                    <td><input type="submit"  value="Update" /></td>
                    <td><input type="reset"></td>
                </tr>-->

            </table>
        </form>
   
    </body>

</html>

