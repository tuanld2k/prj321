<%-- 
    Document   : addCust
    Created on : Jun 17, 2021, 10:26:06 PM
    Author     : nguoitamxa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
          <form action="ControllerCustomer" method="post">
            <table border="0">             
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="Name" value="" /></td>
                </tr>
                <tr>
                    <td>Phone</td>
                    <td><input type="text" name="phone" value="" /></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><input type="text" name="address" value="" /></td>
                </tr>
                
                <tr>
                    <td>Username</td>
                    <td><input type="text" name="user" value="" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="text" name="pass" value="" /></td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td> <input type="radio" name="st" value="1" checked />Enable
                        <input type="radio" name="st" value="0" />disable</td>
                </tr>
               
                <tr>
                    <td><input type="submit"  value="Insert" /></td>
                    <td><input type="reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
