/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Bill;
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
 * @author nguoitamxa
 */
public class DAOBill extends DBConnect{

    Connection conn = null;
    DBConnect dbConn = null;

    public DAOBill(DBConnect dbconn) {
        conn = dbconn.conn;
        this.dbConn = dbconn;
    }
    
     public int deleteBill(int pid) {
        int n = 0;
        String sql = "delete  from Bill where oID= '" + pid+"'";
        //step1: check Bill has pid? (select)
        // step 1yes: change status 1-0
        //n0: delete
        try {
            PreparedStatement pre = conn.prepareStatement(sql);          
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addBillDetail(Bill bill) {
        int n = 0;
        String preSQL = "INSERT INTO [dbo].[Bill]\n"
                + "           ([oID]\n"
                + "           ,[dateCreate]\n"
                + "           ,[cname]\n"
                + "           ,[cphone]\n"
                + "           ,[cAddress]\n"
                + "           ,[total]\n"
                + "           ,[status]\n"
                + "           ,[cid])\n"
                + "     VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(preSQL);
            pre.setString(1, bill.getoID());
            pre.setString(2, bill.getDateCreate());
            pre.setString(3, bill.getCname());
            pre.setString(4, bill.getCphone());
            pre.setString(5, bill.getcAddress());
            pre.setDouble(6, bill.getTotal());
            pre.setInt(7, bill.getStatus());
            pre.setInt(8, bill.getCid());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateBill(Bill bill) {
        int n = 0;
        String sql = "update Bill set oID=?,dateCreate=?,"
                + "cname=?,cphone=?,cAddress=?,total=?,status=?"
                + " where cid=? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, bill.getoID());
            pre.setString(2, bill.getDateCreate());
            pre.setString(3, bill.getCname());
            pre.setString(4, bill.getCphone());
            pre.setString(5, bill.getcAddress());
            pre.setDouble(6, bill.getTotal());
            pre.setInt(7, bill.getStatus());
            pre.setInt(8, bill.getCid());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void displayBillByoID(String oID) {
        String sql = "SELECT "
                + "      ,[dateCreate]\n"
                + "      ,[cname]\n"
                + "      ,[cphone]\n"
                + "      ,[cAddress]\n"
                + "      ,[total]\n"
                + "      ,[status]\n"
                + "      ,[cid]\n"
                + "  FROM [dbo].[Bill] where oID = " + oID;
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
//            ResultSet rs=dbConn.getData(sql);
            while (rs.next()) {
                String dateCreate = rs.getString("dateCreate");
                String cname = rs.getString("cname");
                String cphone = rs.getString("cphone");
                String cAddress = rs.getString("cAddress");
                double total = rs.getDouble("total");
                int status = rs.getInt("status");
                int cid = rs.getInt("cid");
                Bill bill = new Bill(oID, dateCreate, cname, cphone, cAddress, total, status, cid);
                System.out.println(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SortAscBillBytotal() {
        String sql = "SELECT [oID]\n"
                + "      ,[dateCreate]\n"
                + "      ,[cname]\n"
                + "      ,[cphone]\n"
                + "      ,[cAddress]\n"
                + "      ,[total]\n"
                + "      ,[status]\n"
                + "      ,[cid]\n"
                + "  FROM [dbo].[Bill] order by total asc";
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
//            ResultSet rs=dbConn.getData(sql);
            while (rs.next()) {
                String oID = rs.getString("oID");
                String dateCreate = rs.getString("dateCreate");
                String cname = rs.getString("cname");
                String cphone = rs.getString("cphone");
                String cAddress = rs.getString("cAddress");
                double total = rs.getDouble("total");
                int status = rs.getInt("status");
                int cid = rs.getInt("cid");
                Bill bill = new Bill(oID, dateCreate, cname, cphone, cAddress, total, status, cid);
                System.out.println(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SortDescBillBytotal() {
        String sql = "SELECT [oID]\n"
                + "      ,[dateCreate]\n"
                + "      ,[cname]\n"
                + "      ,[cphone]\n"
                + "      ,[cAddress]\n"
                + "      ,[total]\n"
                + "      ,[status]\n"
                + "      ,[cid]\n"
                + "  FROM [dbo].[Bill] order by total desc";
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
//            ResultSet rs=dbConn.getData(sql);
            while (rs.next()) {
                String oID = rs.getString("oID");
                String dateCreate = rs.getString("dateCreate");
                String cname = rs.getString("cname");
                String cphone = rs.getString("cphone");
                String cAddress = rs.getString("cAddress");
                double total = rs.getDouble("total");
                int status = rs.getInt("status");
                int cid = rs.getInt("cid");
                Bill bill = new Bill(oID, dateCreate, cname, cphone, cAddress, total, status, cid);
                System.out.println(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public ArrayList<Bill> displayAllBill() {
        ArrayList<Bill> CateList = new ArrayList<>();
        String sql = "select * from Bill";
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
                String oid = rs.getString("oID");//rs.getInt(2);
                String date = rs.getString("dateCreate");
                String cname = rs.getString("cname");//rs.getString("cname");
                String cphone = rs.getString("cphone");
                String cAdress = rs.getString("cAddress");
                double total = rs.getDouble("total");
                int status = rs.getInt("status");
                int cid = rs.getInt("cid");
                Bill cus = new Bill(oid, date, cname, cphone, cAdress, total, status, cid);
                CateList.add(cus);
                System.out.println(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CateList;
    }

}
