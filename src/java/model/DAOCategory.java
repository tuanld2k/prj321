/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class DAOCategory extends DBConnect{

    public int addCategory(Category cate) {
        int n = 0;
        String preSQL = "insert into Category(cateName)"
                + " values(?)";
        try {
            PreparedStatement pre = conn.prepareStatement(preSQL);
            pre.setString(1, cate.getCateName());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateCategory(Category category) {
        int n = 0;
        String sql = "update Category set "
                + "cateName='"+category.getCateName()+"',status='"+category.getStatus()+"'"
                + " where cateID='"+category.getCateID()+"'";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int deleteCat(int pid) {
        int n = 0;
        String sql = "delete  from Category where cateID= '" + pid+"'";
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

    public Category getCategoryBycateID(String cateID) {
        String sql = "SELECT [cateName]\n"
                + "      ,[status]\n"
                + "  FROM [dbo].[Category] where cateID = " + cateID;
        Category category =null;
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
//            ResultSet rs=dbConn.getData(sql);
            while (rs.next()) {
                String cateName = rs.getString("cateName");
                int status = rs.getInt("status");
                category = new Category(Integer.parseInt(cateID), cateName, status);
                System.out.println(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return category;
    }

    public void SortAscCategoryBycateName() {
        String sql = "SELECT [cateID]\n"
                + "      ,[cateName]\n"
                + "      ,[status]\n"
                + "  FROM [dbo].[Category] order by cateName asc;";
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
//            ResultSet rs=dbConn.getData(sql);
            while (rs.next()) {
                int cateID = rs.getInt("cateID");
                String cateName = rs.getString("cateName");
                int status = rs.getInt("status");
                Category category = new Category(cateID, cateName, status);
                System.out.println(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void SortDescCategoryBycateName() {
        String sql = "SELECT [cateID]\n"
                + "      ,[cateName]\n"
                + "      ,[status]\n"
                + "  FROM [dbo].[Category] order by cateName desc;";
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
//            ResultSet rs=dbConn.getData(sql);
            while (rs.next()) {
                int cateID = rs.getInt("cateID");
                String cateName = rs.getString("cateName");
                int status = rs.getInt("status");
                Category category = new Category(cateID, cateName, status);
                System.out.println(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
      public ArrayList<Category> displayAllCate() {
        ArrayList<Category> CateList = new ArrayList<>();
        String sql = "select * from Category";
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
                int cid = rs.getInt("cateID");//rs.getInt(2);
                String cname = rs.getString("cateName");//rs.getString("cname");
                int status = rs.getInt(3);
                
                Category cus = new Category(cid, cname, status);
                CateList.add(cus);
                System.out.println(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CateList;
    }
     public static void main(String[] args) {
        DAOCategory daocate = new DAOCategory();
         System.out.println(daocate.getCategoryBycateID("2"));
    }
}
