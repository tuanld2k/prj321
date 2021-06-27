/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOProduct;
import model.DBConnect;

/**
 *
 * @author nguoitamxa
 */
public class ControllerProduct extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DBConnect dbconn = new DBConnect();
        DAOProduct dao = new DAOProduct();

        try (PrintWriter out = response.getWriter()) {
            // get serice
            String service = request.getParameter("service");
            //check service -- not exception
            if (service == null) {
                service = "listAll";
            }

            if (service.equalsIgnoreCase("listAll")) {
                // do task
//                out.println("<table border=\"1\">\n"
//                        + "            <thead>\n"
//                        + "                <tr>\n"
//                        + "                    <th>ID</th>\n"
//                        + "                    <th>Name</th>\n"
//                        + "                    <th>Image</th>\n"
//                        + "                    <th>Quantity</th>\n"
//                        + "                    <th>Price</th>\n"
//                        + "                    <th>Description</th>\n"
//                        + "                    <th>Status</th>\n"
//                        + "                    <th>Delete</th>\n"
//                        + "                    <th>update</th>\n"
//                        + "                </tr>\n"
//                        + "            </thead>\n"
//                        + "            <tbody>");
//                String sql = "select * from Product";
//                ResultSet rs = dbConn.getData(sql);
//                while (rs.next()) {
//                    out.println("<tr>\n"
//                            + "                    <td>" + rs.getString("pid") + "</td>\n"
//                            + "                    <td>" + rs.getString("pname") + "</td>\n"
//                            + "                    <td> <img src=\"" + rs.getString("image") + "\"/></td>\n"
//                            + "                    <td>" + rs.getInt("quantity") + "</td>\n"
//                            + "                    <td>" + rs.getDouble("price") + "</td>\n"
//                            + "                    <td>" + rs.getString("description") + "</td>\n"
//                            + "                    <td>" + (rs.getInt("status") == 1 ? "Enable" : "Disable") + "</td>\n"
//                            + "                    <td> <p><a href=\"ControllerProduct?service=delete&pid=" + rs.getString("pid") + "\">delete</a></p></td>\n"
//                            + "                    <td> <p><a href=\"ControllerProduct?service=preUpdate&pid=" + rs.getString("pid") + "\">update</a></p></td>\n"
//                            + "                </tr>");
//                }
//
//                out.println(" </tbody>\n"
//                        + "        </table>");

                String sql = "select * from Product";
                ResultSet rs = dbconn.getData(sql);
                ArrayList<Product> arr = dao.displayAllProduct();
                // other data
                String title = "List of Product";
                // set data for view
                request.setAttribute("rs", rs);
                request.setAttribute("list", arr);
                request.setAttribute("tieude", title);
                // call view 
                // RequestDispatcher dis=request.getRequestDispatcher(URL);
                // URL: jsp, servlet, syntag:  /name
                RequestDispatcher dis = request.getRequestDispatcher("/viewPro.jsp");
                // run
                dis.forward(request, response);
            }

//            if (service.equalsIgnoreCase("listName")) {
//                out.println("<tr>\n"
//                        + "                    <td><b> Choose Product Name:</b></td>\n"
//                        + "                    <td>\n"
//                        + "                            <select name=\"CateSelect\">");
//
//                String Sql = "select * from Product";
//                ResultSet rs = dbConn.getData(Sql);
//
//                while (rs.next()) {
//                    out.println(" <option value=\"" + rs.getInt(1) + "\">" + rs.getString(2) + "</option></tr>");
//                }
//            }
            if (service.equalsIgnoreCase("delete")) {
                Integer pid = Integer.parseInt(request.getParameter("pid"));
                DAOProduct db = new DAOProduct();
                db.deleteProduct(pid);
                response.sendRedirect("ControllerProduct");
            }

            if (service.equalsIgnoreCase("addProduct")) {
//                out.println("<form action=\"ControllerProduct\" method=\"post\">\n"
//                        + "            <table border=\"0\">");
//                out.println(" <tr>\n"
//                        + "<tr>\n"
//                        + "                    <td>Product ID</td>\n"
//                        + "                    <td><input type=\"text\" name=\"ProductID\" value=\"\" /></td>\n"
//                        + "                </tr>"
//                        + "                    <td>Name</td>\n"
//                        + "                    <td><input type=\"text\" name=\"Name\" value=\"\" /></td>\n"
//                        + "                </tr>\n"
//                        + "                <tr>\n"
//                        + "                    <td>Quantity</td>\n"
//                        + "                    <td><input type=\"text\" name=\"quantity\" value=\"\" /></td>\n"
//                        + "                </tr>\n"
//                        + "                <tr>\n"
//                        + "                    <td>Price</td>\n"
//                        + "                    <td><input type=\"text\" name=\"Price\" value=\"\" /></td>\n"
//                        + "                </tr>\n"
//                        + "                <tr>\n"
//                        + "                    <td>image</td>\n"
//                        + "                    <td><input type=\"text\" name=\"image\" value=\"\" /></td>\n"
//                        + "                </tr>\n"
//                        + "                <tr>\n"
//                        + "                    <td>description</td>\n"
//                        + "                    <td><input type=\"text\" name=\"description\" value=\"\" /></td>\n"
//                        + "                </tr>\n"
//                        + "                <tr>\n"
//                        + "                    <td>Status</td>\n"
//                        + "                    <td> <input type=\"radio\" name=\"st\" value=\"1\" checked />Enable\n"
//                        + "                        <input type=\"radio\" name=\"st\" value=\"0\" />disable</td>\n"
//                        + "                </tr>");
//                out.println("<tr>\n"
//                        + "                    <td><b> Choose Category Name:</b></td>\n"
//                        + "                    <td>\n"
//                        + "                            <select name=\"Cate\">");
//
//                String Sql = "select * from Category";
//                 ResultSet rs = dbconn.getData(Sql);
//
//                while (rs.next()) {
//                    out.println(" <option value=\"" + rs.getInt(1) + "\">" + rs.getString(2) + "</option>");
//                }
//
//                out.println("/select>\n"
//                        + "                        </td>\n"
//                        + "                </tr>\n"
//                        + "                <tr>\n"
//                        + "                    <td><input type=\"submit\"  value=\"Insert\" /></td>\n"
//                        + "                    <td><input type=\"reset\"></td>\n"
//                        + "                </tr>\n"
//                        + "            </table>\n"
//                        + "        </form>");
                RequestDispatcher dis = request.getRequestDispatcher("/addPro.jsp");
                dis.forward(request, response);

            }

            if (service.equals("preUpdate")) {
//                Product pr = new DAOProduct().getProductbyID(request.getParameter("id"));
//                out.println("<form action=\"ControllerProduct\" method=\"get\">\n"
//                        + "            <table border=\"0\">");
//                out.println(" <tr>\n"
//                        + "                    <td>Product ID</td>\n"
//                        + "                    <td><input type=\"hidden\" name=\"ProductID\" value=\"" + pr.getPid() + "\" />" + request.getParameter("id") + "</td>\n"
//                        + "</tr>"
//                        + "<tr>"
//                        + "                    <td>Name</td>\n"
//                        + "                    <td><input type=\"text\" name=\"Name\" value=\"" + pr.getPname() + "\" /></td>\n"
//                        + "                </tr>\n"
//                        + "                <tr>\n"
//                        + "                    <td>Quantity</td>\n"
//                        + "                    <td><input type=\"text\" name=\"quantity\" value=\"" + pr.getQuantity() + "\" /></td>\n"
//                        + "                </tr>\n"
//                        + "                <tr>\n"
//                        + "                    <td>Price</td>\n"
//                        + "                    <td><input type=\"text\" name=\"Price\" value=\"" + pr.getPrice() + "\" /></td>\n"
//                        + "                </tr>\n"
//                        + "                <tr>\n"
//                        + "                    <td>image</td>\n"
//                        + "                    <td><input type=\"text\" name=\"image\" value=\"" + pr.getImage() + "\" /></td>\n"
//                        + "                </tr>\n"
//                        + "                <tr>\n"
//                        + "                    <td>description</td>\n"
//                        + "                    <td><input type=\"text\" name=\"description\" value=\"" + pr.getDescription() + "\" /></td>\n"
//                        + "                </tr>\n"
//                        + "                <tr>\n"
//                        + "                    <td>Status</td>\n"
//                );
//                if ((pr.getStatus() == 1)) {
//                    out.println(""
//                            + "                    <td> "
//                            + "                        <input type=\"radio\" name=\"st\" value=\"" + pr.getStatus() + "\" checked/>Enable\n"
//                            + "                        <input type=\"radio\" name=\"st\" value=\"" + pr.getStatus() + "\" />Disable");
//                } else {
//                    out.println(""
//                            + "                    <td> "
//                            + "                        <input type=\"radio\" name=\"st\" value=\"" + pr.getStatus() + "\"/>Enable\n"
//                            + "                        <input type=\"radio\" name=\"st\" value=\"" + pr.getStatus() + "\" checked/>Disable");
//                }
//
//                out.println("<tr>\n"
//                        + "                    <td><b> Choose Category Name:</b></td>\n"
//                        + "                    <td>\n"
//                        + "                            <select name=\"Cate\">");
//
//                String Sql = "select * from Category";
//                ResultSet rs = dbconn.getData(Sql);
//
//                while (rs.next()) {
//                    if (Integer.parseInt(rs.getString(1)) == pr.getCateID()) {
//                        out.println("<option value=\"" + rs.getString(1) + "\" selected>" + rs.getString(2) + "</option>");
//                    } else {
//                        out.println(" <option value=\"" + rs.getString(1) + "\">" + rs.getString(2) + "</option>");
//                    }
//                }
//
//                out.println("/select>\n"
//                        + "                        </td>\n"
//                        + "                </tr>\n"
//                        + "                <tr>\n"
//                        + "                    <td><input type=\"submit\"  value=\"Update\" /></td>\n"
//                        + "                    <td><input type=\"reset\"></td>\n"
//                        + "                </tr>\n"
//                        + "            </table>\n"
//                        + "        </form>");
//
//                int pid = Integer.parseInt(request.getParameter("ProductID"));
//                String name = request.getParameter("Name");
//                int quantity = Integer.parseInt(request.getParameter("quantity"));
//                double price = Double.parseDouble(request.getParameter("Price"));
//                String images = request.getParameter("image");
//                String des = request.getParameter("description");
//                int status = Integer.parseInt(request.getParameter("st"));
//                int cateid = Integer.parseInt(request.getParameter("Cate"));
//                Product k = new Product(pid, name, quantity, price, images, des, status, cateid);
//                DAOProduct db = new DAOProduct();
//                db.updateProduct(k);
                DAOProduct daopro = new DAOProduct();
                Product pro = daopro.getProductbyID(request.getParameter("id"));
                // other data
                String title = "Update of Product";
                // set data for view
                request.setAttribute("acc", pro);
                request.setAttribute("tieude", title);
                // call view 
                // RequestDispatcher dis=request.getRequestDispatcher(URL);
                // URL: jsp, servlet, syntag:  /name
                RequestDispatcher dis = request.getRequestDispatcher("/updateProduct.jsp");
                // run
                dis.forward(request, response);
            }

            if (service.equals("update")) {
                int id = Integer.parseInt(request.getParameter("ProductID"));
                String name = request.getParameter("Name");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                int price = Integer.parseInt(request.getParameter("Price"));
                String images = request.getParameter("image");
                String des = request.getParameter("description");
                int status = Integer.parseInt(request.getParameter("st"));
                int cateid = Integer.parseInt(request.getParameter("Cate"));

                Product pro = new Product(id, name, quantity, price, images, des, status, cateid);
                DAOProduct daop = new DAOProduct();
                daop.updateProduct(pro);
                response.sendRedirect("ControllerProduct");
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
//        String pid = request.getParameter("ProductID");
//        String name = request.getParameter("Name");
//        int quantity = Integer.parseInt(request.getParameter("quantity"));
//        double price = Double.parseDouble(request.getParameter("Price"));
//        String images = request.getParameter("image");
//        String des = request.getParameter("description");
//        int status = Integer.parseInt(request.getParameter("st"));
//        int cateid = Integer.parseInt(request.getParameter("Cate"));
//        Product k = new Product(pid, name, quantity, price, images, des, status, cateid);
//        DAOProduct db = new DAOProduct();
//        db.updateProduct(k);
//
////         request.getRequestDispatcher("ControllerProduct?service=listAll").forward(request, response);
//        response.sendRedirect("ControllerProduct?service=listAll");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        int pid = Integer.parseInt(request.getParameter("ProductID"));
        String name = request.getParameter("Name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int price = Integer.parseInt(request.getParameter("Price"));
        String images = request.getParameter("image");
        String des = request.getParameter("description");
        int status = Integer.parseInt(request.getParameter("st"));
        int cateid = Integer.parseInt(request.getParameter("Cate"));
        Product d = new Product(pid, name, quantity, price, images, des, status, cateid);
        DAOProduct db = new DAOProduct();
        db.addProduct(d);
//        db.updateProduct(d);

//        String id2 = request.getParameter("ProductID2");
        response.sendRedirect("ControllerProduct?service=listAll");
//        response.getWriter().println("Update successful!");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
