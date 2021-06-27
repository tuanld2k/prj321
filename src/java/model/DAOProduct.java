/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Product;
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
public class DAOProduct extends DBConnect {

    public int addProduct(Product product) {
        int n = 0;
        String preSQL = "INSERT INTO [dbo].[Product]\n"
                + "           ([pid]\n"
                + "           ,[pname]\n"
                + "           ,[quantity]\n"
                + "           ,[price]\n"
                + "           ,[image]\n"
                + "           ,[description]\n"
                + "           ,[status]\n"
                + "           ,[cateID])\n"
                + "     VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(preSQL);
            pre.setInt(1, product.getPid());
            pre.setString(2, product.getPname());
            pre.setInt(3, product.getQuantity());
            pre.setDouble(4, product.getPrice());
            pre.setString(5, product.getImage());
            pre.setString(6, product.getDescription());
            pre.setInt(7, product.getStatus());
            pre.setInt(8, product.getCateID());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public Product getProductbyID(String pid) {
        Product pro = null;
        String sql = "Select * from Product where pid= " + pid;
        //step1: check Bill has pid? (select)
        // step 1yes: change status 1-0
        //n0: delete
        try {

            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int pID = Integer.parseInt(rs.getString("pid"));
                String pname = rs.getString("pname");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                String image = rs.getString("image");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                int cateID = rs.getInt("cateID");
                pro = new Product(pID, pname, quantity, price, image, description, status, cateID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pro;

    }

    public int deleteProduct(int pid) {
        int n = 0;
        String sql = "delete  from Product where pid= '" + pid + "'";
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

    public int updateProduct(Product product) {
        int n = 0;
        String sql = "update Product set "
                + "pname='" + product.getPname() + "',quantity='" + product.getQuantity() + "',"
                + "price='" + product.getPrice() + "',image='" + product.getImage() + "',"
                + "description='" + product.getDescription() + "',"
                + "status='" + product.getStatus() + "',cateID='" + product.getCateID() + "'"
                + " where pid='" + product.getPid() + "'";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Product displayProductBypID(String pname) {
        Product product = null;
        String sql = "SELECT [pid]"
                + "[pname]\n"
                + "      ,[quantity]\n"
                + "      ,[price]\n"
                + "      ,[image]\n"
                + "      ,[description]\n"
                + "      ,[status]\n"
                + "      ,[cateID]\n"
                + "  FROM [dbo].[Product] where pname =" + pname;
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
//            ResultSet rs=dbConn.getData(sql);
            while (rs.next()) {
                int pID = Integer.parseInt(rs.getString("pid"));
//                String pname = rs.getString("pname");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                String image = rs.getString("image");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                int cateID = rs.getInt("cateID");
                product = new Product(pID, pname, quantity, price, image, description, status, cateID);

//                System.out.println(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    public void SortAscProductBycname() {
        String sql = "SELECT [pid]\n"
                + "      ,[pname]\n"
                + "      ,[quantity]\n"
                + "      ,[price]\n"
                + "      ,[image]\n"
                + "      ,[description]\n"
                + "      ,[status]\n"
                + "      ,[cateID]\n"
                + "  FROM [dbo].[Product] order by pname asc";
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
//            ResultSet rs=dbConn.getData(sql);
            while (rs.next()) {
                int pID = Integer.parseInt(rs.getString("pid"));
                String pname = rs.getString("pname");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                String image = rs.getString("image");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                int cateID = rs.getInt("cateID");
                Product product = new Product(pID, pname, quantity, price, image, description, status, cateID);
                System.out.println(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Product> displayAllProduct() {
        ArrayList<Product> ProList = new ArrayList<>();
        String sql = "select * from Product";
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
                int pid = Integer.parseInt(rs.getString("pid"));//rs.getInt(2);
                String pname = rs.getString("pname");//rs.getString("cname");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                String image = rs.getString("image");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                int cateID = rs.getInt("cateID");
//                Customer cus = new Customer(cid, cname, cphone, cAddress, username, password, status);
                Product pro = new Product(pid, pname, quantity, price, image, description, status, cateID);
                ProList.add(pro);
                System.out.println(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ProList;
    }

//    public void SortDescProductBycname() {
//        String sql = "SELECT [pid]\n"
//                + "      ,[pname]\n"
//                + "      ,[quantity]\n"
//                + "      ,[price]\n"
//                + "      ,[image]\n"
//                + "      ,[description]\n"
//                + "      ,[status]\n"
//                + "      ,[cateID]\n"
//                + "  FROM [dbo].[Product] order by pname desc";
//        try {
//            Statement state = conn.createStatement(
//                    ResultSet.TYPE_SCROLL_SENSITIVE,
//                    ResultSet.CONCUR_UPDATABLE);
//            ResultSet rs = state.executeQuery(sql);
////            ResultSet rs=dbConn.getData(sql);
//            while (rs.next()) {
//                String pID = rs.getString("pid");
//                String pname = rs.getString("pname");
//                int quantity = rs.getInt("quantity");
//                double price = rs.getDouble("price");
//                String image = rs.getString("image");
//                String description = rs.getString("description");
//                int status = rs.getInt("status");
//                int cateID = rs.getInt("cateID");
//                Product product = new Product(pID, pname, quantity, price, image, description, status, cateID);
//                System.out.println(product);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public static void main(String[] args) {
        DAOProduct p = new DAOProduct();
        System.out.println(p.getProductbyID("33"));

    }
}
