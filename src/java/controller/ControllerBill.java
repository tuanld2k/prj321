/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Bill;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOBill;
import model.DBConnect;

/**
 *
 * @author nguoitamxa
 */
public class ControllerBill extends HttpServlet {

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
        DAOBill dao = new DAOBill(dbconn);

        try (PrintWriter out = response.getWriter()) {
            // get serice
            String service = request.getParameter("service");
            //check service -- not exception
            if (service == null) {
                service = "listBill";
            }

            if (service.equalsIgnoreCase("listBill")) {
                // call model
                String sql = "select * from Customer";
                ResultSet rs = dbconn.getData(sql);
                ArrayList<Bill> arr = dao.displayAllBill();
                // other data
                String title = "List of Bill";
                // set data for view
                request.setAttribute("rs", rs);
                request.setAttribute("list", arr);
                request.setAttribute("tieude", title);
                // call view 
                // RequestDispatcher dis=request.getRequestDispatcher(URL);
                // URL: jsp, servlet, syntag:  /name
                RequestDispatcher dis = request.getRequestDispatcher("/viewBill.jsp");
                // run
                dis.forward(request, response);

            }

            if (service.equalsIgnoreCase("delete")) {
//                Integer pid = Integer.parseInt(request.getParameter("cid"));
//                DAOBill db1 = new DAOBill();
//                db1.deleteCust(pid);
//                response.sendRedirect("ControllerCustomer");
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
        processRequest(request, response);
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
