/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Admin;
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
import model.DAOAdmin;
import model.DBConnect;

/**
 *
 * @author nguoitamxa
 */
public class ControllerAdmin extends HttpServlet {

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
        DAOAdmin dao = new DAOAdmin();

        try (PrintWriter out = response.getWriter()) {

            // get serice
            String service = request.getParameter("service");
            //check service -- not exception

            if (service == null) {
                service = "listAdmin";
            }

            if (service.equals("addAdmin")) {
//                out.println("  <form action=\"ControllerAdmin\" method=\"POST\">\n"
//                        + "            <table border=\"0\">\n"
//                        + "                <tr>\n"
//                        + "                    <th><label for=\"name\">UserName</label></th>\n"
//                        + "                    <th><input type=\"text\" name=\"name\" id=\"cate\" required>\n"
//                        + "                    </th>\n"
//                        + "                </tr>\n"
//                        + "                <tr>\n"
//                        + "                    <th><label for=\"pass\">Password</label></th>\n"
//                        + "                    <th><input type=\"text\" name=\"pass\" id=\"cate\" required>\n"
//                        + "                    </th>\n"
//                        + "                </tr>\n"
//                        + "                <tr>\n"
//                        + "                    <td><input type=\"submit\" name=\"submit\" value=\"Add\"></td>\n"
//                        + "                    <td><input type=\"reset\" value=\"Reset\"></td>\n"
//                        + "                </tr>\n"
//                        + "            </table>\n"
//                        + "        </form>");
                RequestDispatcher dis = request.getRequestDispatcher("/addAdmin.jsp");
                dis.forward(request, response);
            }

            if (service.equalsIgnoreCase("listAdmin")) {
                // do task
//                out.println("<table border=\"1\">\n"
//                        + "            <thead>\n"
//                        + "                <tr>\n"
//                        + "                    <th>ID</th>\n"
//                        + "                    <th>Name</th>\n"
//                        + "                    <th>Password</th>\n"
//                        + "                    <th>Delete</th>\n"
//                        + "                    <th>Update</th>\n"
//                        + "                </tr>\n"
//                        + "            </thead>\n"
//                        + "            <tbody>");
//                String sql = "select * from Admin";
//                ResultSet rs = dbConn.getData(sql);
//                while (rs.next()) {
//                    out.println("<tr>\n"
//                            + "                    <td>" + rs.getString("adminID") + "</td>\n"
//                            + "                    <td>" + rs.getString("username") + "</td>\n"
//                            + "                    <td>" + rs.getString("password") + "</td>\n"
//                            + "                    <td> <p><a href=\"ControllerAdmin?service=delete&adminID=" + rs.getString("adminID") + "\">delete</a></p></td>\n"
//                            + "                    <td> <p><a href=\"ControllerAdmin?service=preUpdate&adminID=" + rs.getString("adminID") + "\">update</a></p></td>\n"
//                            + "                </tr>");
//                }
//
//                out.println(" </tbody>\n"
//                        + "        </table>");
                String sql = "select * from Admin";
                ResultSet rs = dbconn.getData(sql);

                ArrayList<Admin> arr = dao.displayAdmin();
                // other data
                String title = "List of Admin";
                // set data for view
                request.setAttribute("rs", rs);
                request.setAttribute("list", arr);
                request.setAttribute("tieude", title);
                // call view 
                // RequestDispatcher dis=request.getRequestDispatcher(URL);
                // URL: jsp, servlet, syntag:  /name
                RequestDispatcher dis = request.getRequestDispatcher("/viewAdmin.jsp");
                // run
                dis.forward(request, response);
            }

            if (service.equalsIgnoreCase("delete")) {
                Integer pid = Integer.parseInt(request.getParameter("id"));
                DAOAdmin db1 = new DAOAdmin();
                db1.deleteAd(pid);
                response.sendRedirect("ControllerAdmin");
            }

            if (service.equalsIgnoreCase("preUpdate")) {
                // model

//                ResultSet rs = dbconn.getData("select * from admin where adminID=" + id);
//                try {
//                    if (rs.next()) {
//                        //set data
//                        Admin acc = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3));
//                        request.setAttribute("acc", acc);
//                        request.setAttribute("title", "Update Account");
//                        //view
////                        dispath(request, response, "updateAccount.jsp");
//                        RequestDispatcher dis = request.getRequestDispatcher("/updateAdmin.jsp");
//                        // run
//                        dis.forward(request, response);
//                    }
//                } catch (SQLException ex) {
//                    Logger.getLogger(ControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
//         
         
                DAOAdmin abc = new DAOAdmin();
                Admin ad = abc.getAdminByID(request.getParameter("id"));
                // other data
                String title = "Update of Admin";
                // set data for view
                request.setAttribute("acc", ad);
                request.setAttribute("tieude", title);
                // call view 
                // RequestDispatcher dis=request.getRequestDispatcher(URL);
                // URL: jsp, servlet, syntag:  /name
                RequestDispatcher dis = request.getRequestDispatcher("/updateAdmin.jsp");
                // run
                dis.forward(request, response);
            }

            if (service.equals("update")) {
                int id = Integer.parseInt(request.getParameter("id"));
                String user = request.getParameter("username");
                String pass = request.getParameter("password");
                Admin acc = new Admin(id, user, pass);
                DAOAdmin dao1 = new DAOAdmin();
                dao1.updateAdmin(acc);
                response.sendRedirect("ControllerAdmin");
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
//        String id = request.getParameter("id");
//        DAOAdmin abc = new DAOAdmin();
//        // other data
//        String title = "Update of Admin";
//        // set data for view
//        request.setAttribute("acc", abc.getAdminByID(id));
//        request.setAttribute("tieude", title);
//        // call view 
//        // RequestDispatcher dis=request.getRequestDispatcher(URL);
//        // URL: jsp, servlet, syntag:  /name
//        RequestDispatcher dis = request.getRequestDispatcher("/updateAdmin.jsp");
//        // run
//        dis.forward(request, response);
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
//         processRequest(request, response);
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        Admin d = new Admin(name, pass);
        DAOAdmin db = new DAOAdmin();
        db.addAdmin(d);
        response.sendRedirect("ControllerAdmin?service=listAdmin");
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
