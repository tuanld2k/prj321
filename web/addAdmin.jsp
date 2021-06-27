<%-- 
    Document   : addAdmin
    Created on : Jun 17, 2021, 9:22:50 PM
    Author     : nguoitamxa
--%>

<%@page import="entity.Admin"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>      
        <form action="ControllerAdmin" method="POST">
            <table border="0">
                <tr>
                    <th><label for="name">UserName</label></th>
                    <th><input type="text" name="name" id="cate" required>
                    </th>
                </tr>
                <tr>
                    <th><label for="pass">Password</label></th>
                    <th><input type="text" name="pass" id="cate" required>
                    </th>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="Add"></td>
                    <td><input type="reset" value="Reset"></td>     
                </tr>
            </table>
        </form>

</body>
</html>
