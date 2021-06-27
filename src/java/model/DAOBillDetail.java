/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Bill;
import entity.BillDetail;
import entity.Category;
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
public class DAOBillDetail extends DBConnect {


//    public int addBillDetail(BillDetail billdt) {
//        int n = 0;
//        String preSQL = "INSERT INTO [dbo].[BillDetail]\n"
//                + "           ([pid]\n"
//                + "           ,[oID]\n"
//                + "           ,[quantity]\n"
//                + "           ,[price]\n"
//                + "           ,[total])\n"
//                + "     VALUES(?,?,?,?,?)";
//        try {
//            PreparedStatement pre = conn.prepareStatement(preSQL);
//            pre.setString(1, billdt.getPid());
//            pre.setString(2, billdt.getoID());
//            pre.setDouble(3, billdt.getMoney());
//            pre.setInt(4, billdt.getQuantity());
//            pre.setDouble(5, billdt.getTotal());
//            n = pre.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return n;
//    }
//
//    public int updateBillDetail(BillDetail billdt) {
//        int n = 0;
//        String sql = "update BillDetail set "
//                + "quantity=?,price=?,total=?"
//                + " where pid=? and oID=?";
//        try {
//            PreparedStatement pre = conn.prepareStatement(sql);
//            pre.setInt(1, billdt.getQuantity());
//            pre.setDouble(2, billdt.getMoney());
//            pre.setDouble(3, billdt.getTotal());
//            pre.setString(4, billdt.getPid());
//            pre.setString(5, billdt.getoID());
//
//            n = pre.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return n;
//    }

    public ArrayList displayBillDetailByoID(String oID) {
        String sql = "SELECT cu.cname,cu.cphone,bi.dateCreate,cu.cAddress,"
                + "pname,c.cateName,p.image, p.description,b.quantity, "
                + "b.price,b.total\n"
                + "FROM Product p\n"
                + "INNER JOIN BillDetail b ON p.pid=b.pid INNER JOIN Bill bi ON"
                + " b.oID = bi.oID INNER JOIN Category c ON c.cateID = p.pid "
                + "INNER JOIN Customer cu ON cu.cid = bi.cid where b.oID = '"+oID+"';";
        ArrayList<BillDetail> bills = new ArrayList<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
//            ResultSet rs=dbConn.getData(sql);
            while (rs.next()) {
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                double total = rs.getDouble("total");
                String cname = rs.getString("cname");
                String phone = rs.getString("cphone");
                String date = rs.getString("dateCreate");
                String description = rs.getString("description");
                String image = rs.getString("image");
                String cateName = rs.getString("cateName");
                String cAddress = rs.getString("cAddress");
                String pname = rs.getString("pname");
               BillDetail bill = new BillDetail(oID, pname, cateName, description, image, date, phone, cname, cAddress, quantity, total, price);
//                BillDetail billDetail = new BillDetail(pid, oID, quantity, money, total);
               bills.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bills;
    }

//    public void SortAscBillDetailByPrice() {
//        String sql = "SELECT [pid]\n"
//                + "      ,[oID]\n"
//                + "      ,[quantity]\n"
//                + "      ,[price]\n"
//                + "      ,[total]\n"
//                + "  FROM [dbo].[BillDetail] order by price asc;";
//        try {
//            Statement state = conn.createStatement(
//                    ResultSet.TYPE_SCROLL_SENSITIVE,
//                    ResultSet.CONCUR_UPDATABLE);
//            ResultSet rs = state.executeQuery(sql);
////            ResultSet rs=dbConn.getData(sql);
//            while (rs.next()) {
//                String pid = rs.getString("pid");
//                String oID = rs.getString("oID");
//                int quantity = rs.getInt("quantity");
//                double money = rs.getDouble("price");
//                double total = rs.getDouble("total");
//                BillDetail billDetail = new BillDetail(pid, oID, quantity, money, total);
//                System.out.println(billDetail);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    public void SortDescBillDetailByPrice() {
//        String sql = "SELECT [pid]\n"
//                + "      ,[oID]\n"
//                + "      ,[quantity]\n"
//                + "      ,[price]\n"
//                + "      ,[total]\n"
//                + "  FROM [dbo].[BillDetail] order by price desc;";
//        try {
//            Statement state = conn.createStatement(
//                    ResultSet.TYPE_SCROLL_SENSITIVE,
//                    ResultSet.CONCUR_UPDATABLE);
//            ResultSet rs = state.executeQuery(sql);
////            ResultSet rs=dbConn.getData(sql);
//            while (rs.next()) {
//                String pid = rs.getString("pid");
//                String oID = rs.getString("oID");
//                int quantity = rs.getInt("quantity");
//                double money = rs.getDouble("price");
//                double total = rs.getDouble("total");
//                BillDetail billDetail = new BillDetail(pid, oID, quantity, money, total);
//                System.out.println(billDetail);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public static void main(String[] args) {
        DAOBillDetail bill = new DAOBillDetail();
        System.out.println(bill.displayBillDetailByoID("1"));
    }
}
