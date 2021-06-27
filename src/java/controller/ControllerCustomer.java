/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import model.DAOCustomer;
import model.DBConnect;

/**
 *
 * @author nguoitamxa
 */
public class ControllerCustomer extends HttpServlet {

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
        DAOCustomer dao = new DAOCustomer();

        try (PrintWriter out = response.getWriter()) {
            // get serice
            String service = request.getParameter("service");
            //check service -- not exception
            if (service == null) {
                service = "listCustomer";
            }
            if (service.equals("addCustomer")) {
//                out.println("<form action=\"ControllerCustomer\" method=\"post\">\n"
//                        + "            <table border=\"0\">             \n"
//                        + "                <tr>\n"
//                        + "                    <td>Name</td>\n"
//                        + "                    <td><input type=\"text\" name=\"Name\" value=\"\" /></td>\n"
//                        + "                </tr>\n"
//                        + "                <tr>\n"
//                        + "                    <td>Phone</td>\n"
//                        + "                    <td><input type=\"text\" name=\"phone\" value=\"\" /></td>\n"
//                        + "                </tr>\n"
//                        + "                <tr>\n"
//                        + "                    <td>Address</td>\n"
//                        + "                    <td><input type=\"text\" name=\"address\" value=\"\" /></td>\n"
//                        + "                </tr>\n"
//                        + "                \n"
//                        + "                <tr>\n"
//                        + "                    <td>Username</td>\n"
//                        + "                    <td><input type=\"text\" name=\"user\" value=\"\" /></td>\n"
//                        + "                </tr>\n"
//                        + "                <tr>\n"
//                        + "                    <td>Password</td>\n"
//                        + "                    <td><input type=\"text\" name=\"pass\" value=\"\" /></td>\n"
//                        + "                </tr>\n"
//                        + "                <tr>\n"
//                        + "                    <td>Status</td>\n"
//                        + "                    <td> <input type=\"radio\" name=\"st\" value=\"1\" checked />Enable\n"
//                        + "                        <input type=\"radio\" name=\"st\" value=\"0\" />disable</td>\n"
//                        + "                </tr>\n"
//                        + "               \n"
//                        + "                <tr>\n"
//                        + "                    <td><input type=\"submit\"  value=\"Insert\" /></td>\n"
//                        + "                    <td><input type=\"reset\"></td>\n"
//                        + "                </tr>\n"
//                        + "            </table>\n"
//                        + "        </form>");
                RequestDispatcher dis = request.getRequestDispatcher("/addCust.jsp");
                dis.forward(request, response);

            }

            if (service.equalsIgnoreCase("listCustomer")) {
                // do task
                /* MVC
                 view -- action -- controller
                 controller -- call model
                 */
//                out.println("<table border=\"1\">\n"
//                        + "            <thead>\n"
//                        + "                <tr>\n"
//                        + "                    <th>ID</th>\n"
//                        + "                    <th>Name</th>\n"
//                        + "                    <th>Phone</th>\n"
//                        + "                    <th>Address</th>\n"
//                        + "                    <th>User</th>\n"
//                        + "                    <th>Pass</th>\n"
//                        + "                    <th>Status</th>\n"
//                        + "                    <th>Delete</th>\n"
//                        + "                    <th>Update</th>\n"
//                        + "                </tr>\n"
//                        + "            </thead>\n"
//                        + "            <tbody>");
//                String sql = "select * from Customer";
//                ResultSet rs = dbConn.getData(sql);
//                while (rs.next()) {
//                    out.println("<tr>\n"
//                            + "                    <td>" + rs.getString("cid") + "</td>\n"
//                            + "                    <td>" + rs.getString("cname") + "</td>\n"
//                            + "                    <td>" + rs.getString("cphone") + "</td>\n"
//                            + "                    <td>" + rs.getString("cAddress") + "</td>\n"
//                            + "                    <td>" + rs.getString("username") + "</td>\n"
//                            + "                    <td>" + rs.getString("password") + "</td>\n"
//                            + "                    <td>" + (rs.getInt("status") == 1 ? "Enable" : "Disable") + "</td>\n"
//                            + "                    <td> <p><a href=\"ControllerCustomer?service=delete&cid=" + rs.getString("cid") + "\">delete</a></p></td>\n"
//                            + "                    <td> <p><a href=\"ControllerCustomer?service=preUpdate&cid=" + rs.getString("cid") + "\">update</a></p></td>\n"
//                            + "                </tr>");
//                }
//
//                out.println(" </tbody>\n"
//                        + "        </table>");
                        /* MVC
                 view -- action-- controller
                 controller -- call model -- set data for view
                 -- call view
                 */
                // call model
                String sql = "select * from Customer";
                ResultSet rs = dbconn.getData(sql);
                ArrayList<Customer> arr = dao.displayAllCustomer();
                // other data
                String title = "List of customer";
                // set data for view
                request.setAttribute("rs", rs);
                request.setAttribute("list", arr);
                request.setAttribute("tieude", title);
                // call view 
                // RequestDispatcher dis=request.getRequestDispatcher(URL);
                // URL: jsp, servlet, syntag:  /name
                RequestDispatcher dis = request.getRequestDispatcher("/viewCust.jsp");
                // run
                dis.forward(request, response);

            }

            if (service.equalsIgnoreCase("delete")) {
                Integer pid = Integer.parseInt(request.getParameter("cid"));
                DAOCustomer db1 = new DAOCustomer();
                db1.deleteCust(pid);
                response.sendRedirect("ControllerCustomer");
            }

            if (service.equals("preUpdate")) {
                DAOCustomer daocus = new DAOCustomer();
                Customer customer = daocus.getCustomerByID(Integer.parseInt(request.getParameter("id")));
                // other data
                String title = "Update of Customer";
                // set data for view
                request.setAttribute("acc", customer);
                request.setAttribute("tieude", title);
                // call view 
                // RequestDispatcher dis=request.getRequestDispatcher(URL);
                // URL: jsp, servlet, syntag:  /name
                RequestDispatcher dis = request.getRequestDispatcher("/updateCust.jsp");
                // run
                dis.forward(request, response);
            }

            if (service.equals("update")) {
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("Name");
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");
                String user = request.getParameter("user");
                String pas = request.getParameter("pass");
                int status = Integer.parseInt(request.getParameter("st"));
                Customer cus = new Customer(id,name, phone, address, user, pas, status);

                DAOCustomer daocus = new DAOCustomer();
                daocus.updateCustomer(cus);
                response.sendRedirect("ControllerCustomer");
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
        String name = request.getParameter("Name");
        String phone = request.getParameter("phone");

        String address = request.getParameter("address");
        String user = request.getParameter("user");
        String pas = request.getParameter("pass");
        int status = Integer.parseInt(request.getParameter("st"));

        Customer d = new Customer(name, phone, address, user, pas, status);

        DAOCustomer db = new DAOCustomer();
        db.addCustomer(d);

        response.sendRedirect("ControllerCustomer?service=listCustomer");
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
