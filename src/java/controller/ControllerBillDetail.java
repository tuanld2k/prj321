/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.BillDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOBillDetail;
import model.DBConnect;

/**
 *
 * @author nguoitamxa
 */
@WebServlet(name = "ControllerBillDetail", urlPatterns = {"/ControllerBillDetail"})
public class ControllerBillDetail extends HttpServlet {

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
        DAOBillDetail dao = new DAOBillDetail();

        try (PrintWriter out = response.getWriter()) {
            // get serice
            String service = request.getParameter("service");
            //check service -- not exception
            if (service == null) {
                service = "listBillDetail";
            }

            if (service.equalsIgnoreCase("listBillDetail")) {
                // call model
//                String sql = "select * from ";
//                ResultSet rs = dbconn.getData(sql);
                ArrayList<BillDetail> arr = dao.displayBillDetailByoID(request.getParameter("oID"));
                // other data
                String title = "BillDetail of "+ arr.get(0).getCname();
                // set data for view
//                request.setAttribute("rs", rs);
                request.setAttribute("list", arr);
                request.setAttribute("tieude", title);
                // call view 
                // RequestDispatcher dis=request.getRequestDispatcher(URL);
                // URL: jsp, servlet, syntag:  /name
                RequestDispatcher dis = request.getRequestDispatcher("/viewBillDetail.jsp");
                // run
                dis.forward(request, response);

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
