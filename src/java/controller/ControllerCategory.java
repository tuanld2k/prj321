/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Category;
import entity.Customer;
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
import model.DAOCategory;
import model.DBConnect;

/**
 *
 * @author nguoitamxa
 */
public class ControllerCategory extends HttpServlet {

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
        DAOCategory dao = new DAOCategory();
        try (PrintWriter out = response.getWriter()) {
            // get serice
            String service = request.getParameter("service");
            //check service -- not exception
            if (service == null) {
                service = "listCategory";
            }
            if (service.equals("addCategory")) {
//                out.println("<form action=\"ControllerCategory\" method=\"POST\">\n"
//                        + "            <table border=\"0\">\n"
//                        + "                <tr>\n"
//                        + "                    <th><label for=\"cate\">CateName</label></th>\n"
//                        + "                    <th><input type=\"text\" name=\"cate\" id=\"cate\" required>\n"
//                        + "                    </th>\n"
//                        + "                </tr>\n"
//                        + "                <tr>\n"
//                        + "                    <td><label for=\"status\">Status</label></td>\n"
//                        + "                    <td>\n"
//                        + "                        <input type=\"radio\" name=\"status\" id=\"status\" value=\"1\">active\n"
//                        + "                        <input type=\"radio\" name=\"status\" id=\"status\" value=\"0\">deActive\n"
//                        + "                    </td>\n"
//                        + "                </tr>\n"
//                        + "                 <tr>\n"
//                        + "                     <td><input type=\"submit\" name=\"submit\" value=\"Add Category\"></td>\n"
//                        + "                     <td><input type=\"reset\" value=\"Reset\"></td>\n"
//                        + "                </tr>\n"
//                        + "            </table>\n"
//                        + "        </form>");
                RequestDispatcher dis = request.getRequestDispatcher("/addCate.jsp");
                dis.forward(request, response);

            }

            if (service.equalsIgnoreCase("listCategory")) {
                // do task
//                out.println("<table border=\"1\">\n"
//                        + "            <thead>\n"
//                        + "                <tr>\n"
//                        + "                    <th>ID</th>\n"
//                        + "                    <th>Name</th>\n"
//                        + "                    <th>Status</th>\n"
//                        + "                    <th>Delete</th>\n"
//                        + "                    <th>Update</th>\n"
//                        + "                </tr>\n"
//                        + "            </thead>\n"
//                        + "            <tbody>");
//                String sql = "select * from Category";
//                ResultSet rs = dbConn.getData(sql);
//                while (rs.next()) {
//                    out.println("<tr>\n"
//                            + "                    <td>" + rs.getString("cateID") + "</td>\n"
//                            + "                    <td>" + rs.getString("cateName") + "</td>\n"
//                            + "                    <td>" + (rs.getInt("status") == 1 ? "Enable" : "Disable") + "</td>\n"
//                            + "                    <td> <p><a href=\"ControllerCategory?service=delete&cateID=" + rs.getString("cateID") + "\">delete</a></p></td>\n"
//                            + "                    <td> <p><a href=\"ControllerCategory?service=preUpdate&cateID=" + rs.getString("cateID") + "\">update</a></p></td>\n"
//                            + "                </tr>");
//                }
//
//                out.println(" </tbody>\n"
//                        + "        </table>");
                String sql = "select * from Category";
                ResultSet rs = dbconn.getData(sql);
                ArrayList<Category> arr = dao.displayAllCate();
                // other data
                String title = "List of category";
                // set data for view
                request.setAttribute("rs", rs);
                request.setAttribute("list", arr);
                request.setAttribute("tieude", title);
                // call view 
                // RequestDispatcher dis=request.getRequestDispatcher(URL);
                // URL: jsp, servlet, syntag:  /name
                RequestDispatcher dis = request.getRequestDispatcher("/viewCate.jsp");
                // run
                dis.forward(request, response);
            }

            if (service.equalsIgnoreCase("delete")) {
                Integer pid = Integer.parseInt(request.getParameter("id"));
                DAOCategory db1 = new DAOCategory();
                db1.deleteCat(pid);
                response.sendRedirect("ControllerCategory");
            }
            
              if (service.equals("preUpdate")) {   
         
                DAOCategory daocate = new DAOCategory();
                Category cate = daocate.getCategoryBycateID(request.getParameter("id"));
                // other data
                String title = "Update of Cate";
                // set data for view
                request.setAttribute("acc", cate);
                request.setAttribute("tieude", title);
                // call view 
                // RequestDispatcher dis=request.getRequestDispatcher(URL);
                // URL: jsp, servlet, syntag:  /name
                RequestDispatcher dis = request.getRequestDispatcher("/updateCate.jsp");
                // run
                dis.forward(request, response);
            }

            if (service.equals("update")) {
                int id = Integer.parseInt(request.getParameter("id"));
                String user = request.getParameter("username");
                int status = Integer.parseInt(request.getParameter("st"));
                Category cate = new Category(id, user, status);
                DAOCategory dao1 = new DAOCategory();
                dao1.updateCategory(cate);
                response.sendRedirect("ControllerCategory");
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
        String name = request.getParameter("cate");
        int status = Integer.parseInt(request.getParameter("status"));

        Category d = new Category(name, status);

        DAOCategory db = new DAOCategory();
        db.addCategory(d);

        response.sendRedirect("ControllerCategory?service=listCategory");
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
