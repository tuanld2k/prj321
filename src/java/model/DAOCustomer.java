/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class DAOCustomer extends DBConnect{

    // insert, update, delete, select
    public int addCustomer(Customer cus) {
        int n = 0;
        String preSQL = "insert into Customer(cname,cphone,"
                + "cAddress,username,password,status)"
                + " values(?,?,?,?,?,?)";
        try {
            // ? has index start 1
            PreparedStatement pre = conn.prepareStatement(preSQL);
//            pre.setDataType(index of ?, value);
//            dataType is data of ?
            pre.setString(1, cus.getCname());
            pre.setString(2, cus.getCphone());
            pre.setString(3, cus.getcAddress());
            pre.setString(4, cus.getUsername());
            pre.setString(5, cus.getPassword());
            pre.setInt(6, cus.getStatus());
            // execute
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
//        String sql="insert into Customer(cname,cphone,cAddress,username,password,status)"
//                + " values('"+cus.getCname()+"','"+cus.getCphone()+
//                "','"+cus.getcAddress()+"','"+cus.getUsername()+
//                "','"+cus.getPassword()+"',"+cus.getStatus()+")";
//        try {
//            Statement state=conn.createStatement();
//            n=state.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return n;
    }
  public int deleteCust(int pid) {
        int n = 0;
        String sql = "delete  from Customer where cid= '" + pid+"'";
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
    public int updateCustomer(Customer cus) {
        int n = 0;
        String sql = "update Customer set cname='"+cus.getCname()+"',cphone='"+cus.getCphone()+"',"
                + "cAddress='"+cus.getcAddress()+"',username='"+cus.getUsername()+"',password='"+cus.getPassword()+"',status='"+cus.getStatus()+"'"
                + " where cid='"+cus.getCid()+"' ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);      
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int changePassword(int id, String user,
            String oldPass, String newPass) {
        int n = 0;
        // check userName is true (user, oldPass)
        String sql = "update Customer set password=? where cid=? ";
        return n;
    }

    public int changeCname(int cid, String cname) {
        int n = 0;
        String sql = "update Customer set cname=? where cid=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cname);
            pre.setInt(2, cid);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int changeCAdress(int cid, String cadress) {
        int n = 0;
        String sql = "update Customer set cadress=? where cid=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cadress);
            pre.setInt(2, cid);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public Customer getCustomerByID(int cusID) {
        String sql = "SELECT cname,cphone,cAddress,username,password,status"
                + " FROM Customer where cid = " + cusID;
        Customer cus =null;
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
//            ResultSet rs=dbConn.getData(sql);
            while (rs.next()) {
       
                int status = rs.getInt("status");
                String cname = rs.getString("cname");
                String cphone = rs.getString("cphone");
                String cAddress = rs.getString("cAddress");
                String username = rs.getString("username");
                String pass = rs.getString("password");
                cus = new Customer(cusID, cname, cphone, cAddress, username, pass, status);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cus;
    }

    public int changeCphone(int cid, String cphone) {
        int n = 0;
        String sql = "update Customer set cphone=? where cid=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cphone);
            pre.setInt(2, cid);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int changeStatus(int cid, int status) {
        int n = 0;
        String sql = "update Customer set status=? where cid=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, status);
            pre.setInt(2, cid);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void SortAscCustomerbycname() {
        String sql = "SELECT [cid]\n"
                + "      ,[cname]\n"
                + "      ,[cphone]\n"
                + "      ,[cAddress]\n"
                + "      ,[username]\n"
                + "      ,[password]\n"
                + "      ,[status]\n"
                + "  FROM [dbo].[Customer] order by cname asc;";
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
//            ResultSet rs=dbConn.getData(sql);
            while (rs.next()) {
                int cid = rs.getInt("cid");
                String cateName = rs.getString("cname");
                String cphone = rs.getString("cphone");
                String cAddress = rs.getString("cAddress");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int status = rs.getInt("status");
                
                Customer customer = new Customer(cid, username, cphone, cAddress, username, password, status);
                System.out.println(customer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public boolean login(String username, String pass) {
//        boolean flag = false;
//        String sql = "Select * from Customer"
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

    public ArrayList<Customer> displayAllCustomer() {
        ArrayList<Customer> CusList = new ArrayList<>();
        String sql = "select * from Customer";
        try {
            //TYPE_FORWARD_ONLY: default, con tro bản ghi top - down only
            // Statement state=conn.createStatement();
            //TYPE_SCROLL_SENSITIVE: thread safe
            //CONCUR_READ_ONLY: default no modify rs
            //CONCUR_UPDATABLE: modify rs
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
//            ResultSet rs=dbConn.getData(sql);
            while (rs.next()) {
//                rs.getDataType(index|fieldName);
//                DataType is DataType of fieldName
                int cid = rs.getInt("cid");//rs.getInt(2);
                String cname = rs.getString(2);//rs.getString("cname");
                String cphone = rs.getString("cphone"),
                        cAddress = rs.getString(4),
                        username = rs.getString(5),
                        password = rs.getString(6);
                int status = rs.getInt(7);
                Customer cus = new Customer(cid, cname, cphone, cAddress, username, password, status);
                CusList.add(cus);
                System.out.println(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CusList;
    }

//    public static void main(String[] args) {
//        DBConnect dbconn = new DBConnect();
//        DAOCustomer dao = new DAOCustomer(dbconn);
////        int n = dao.addCustomer(new Customer("Hung", "093232", "hanoi", "hung12", "abc123@123", 1));
////        if (n > 0) {
////            System.out.println("inserted");
////        }
//        dao.displayAllCustomer();
//    }

}
