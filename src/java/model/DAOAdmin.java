/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
public class DAOAdmin extends DBConnect {

//    public boolean login(String username, String pass) {
//        boolean flag = false;
//        String sql = "Select * from admin"
//                + " where username='" + username + "' "
//                + "and password='" + pass + "'";
//        ResultSet rs = dbConn.getData(sql);
//        try {
//            if (rs.next()) {
//                flag = true;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return flag;
//    }
    public Admin getAdminByID(String id) {
        Admin admin = null;
        String sql = "select * from admin "
                + " where adminID=" + id;
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
//            ResultSet rs=dbConn.getData(sql);
            while (rs.next()) {
                int adminID = rs.getInt("adminID");
                String username = rs.getString("username");
                String password = rs.getString("password");
                admin = new Admin(adminID, username, password);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admin;
    }

    public int updateAdmin(Admin admin) {
        int n = 0;
        String sql = "update admin set username='"+admin.getUserName()+"',password='"+admin.getPassowrd()+"' where adminID='"+admin.getAdminID()+"';";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteAd(int pid) {
        int n = 0;
        String sql = "delete  from Admin where adminID= '" + pid + "'";
        //step1: check Bill has pid? (select)
        // step 1yes: change status 1-0
        //n0: delete
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int changePassword(int id, String user,
            String oldPass, String newPass) {
        int n = 0;
        // check userName is true (user, oldPass)
        String sql = "update Admin set password=? where adminID=? ";

        return n;
    }

    public int addAdmin(Admin admin) {
        int n = 0;
        String preSQL = "insert into Admin(username,password)"
                + " values(?,?)";
        try {
            // ? has index start 1
            PreparedStatement pre = conn.prepareStatement(preSQL);
//            pre.setDataType(index of ?, value);
//            dataType is data of ?
            pre.setString(1, admin.getUserName());
            pre.setString(2, admin.getPassowrd());

            // execute
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public ArrayList displayAdmin() {
        ArrayList<Admin> adList = new ArrayList<>();
        String sql = "SELECT adminID\n"
                + "      ,username\n"
                + "      ,password\n"
                + "  FROM admin";
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
//            ResultSet rs=dbConn.getData(sql);
            while (rs.next()) {
                int adminID = rs.getInt("adminID");
                String username = rs.getString("username");
                String password = rs.getString("password");

                Admin admin = new Admin(adminID, username, password);
           
                adList.add(admin);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adList;
    }

//    public void SortAscAdmin() {
//        String sql = "SELECT [adminID]\n"
//                + "      ,[username]\n"
//                + "      ,[password]\n"
//                + "  FROM [dbo].[admin] order by username asc;";
//        try {
//            Statement state = conn.createStatement(
//                    ResultSet.TYPE_SCROLL_SENSITIVE,
//                    ResultSet.CONCUR_UPDATABLE);
//            ResultSet rs = state.executeQuery(sql);
////            ResultSet rs=dbConn.getData(sql);
//            while (rs.next()) {
//                int adminID = rs.getInt("adminID");
//                String username = rs.getString("username");
//                String password = rs.getString("password");
//
//                Admin admin = new Admin(adminID, username, password);
//                System.out.println(admin);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public static void main(String[] args) {
        DAOAdmin abc = new DAOAdmin();
        Admin yu = new Admin(1017, "chien", "abc213@213");
        abc.updateAdmin(yu);
        System.out.println(abc.getAdminByID("1017"));
    }

}
