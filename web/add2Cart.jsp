<%-- 
    Document   : Add2Cart
    Created on : Jun 24, 2021, 10:09:33 PM
    Author     : nguoitamxa
--%>

<%@page import="entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<%
//        Product pro = request.getParameter("id");
	String id = request.getParameter("id"); 
    // id - key
        //get infor of product: name, price, images
        // create product 
	Object value = session.getAttribute(id);
    //getKey(id)
        Product pro=(Product)session.getAttribute(id);
	//the first time the product is selected
	if(value==null){
            //set quantity of product is 1
		//put name-value pair into session object (see HttpSession)
//		session.setAttribute(id,"1"); // put(key,value)
                pro.setQuantity(1);
                session.setAttribute(id,pro);
	}
	//the second/third... time the product is selected
	else{
//		int count = Integer.parseInt(value.toString())+1;
                pro.setQuantity(pro.getQuantity()+1);
		//put name-value pair into session object (see HttpSession)
//		session.setAttribute(id,String.valueOf(count));
                session.setAttribute(id,pro);		
	}	
%>
<h1>Item <%=pro.getPname()%> with id=<%=id%> was added to the Shopping Cart</h1>
<h2><a href="showCart.jsp">Show Shopping Cart</h2>

</body>
</html>
