/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author HP
 */
public class BillDetail {
    private String oID,pname,catename,description,image,dateCreate,cphone,cname,cAdress;
    private int quantity;
    private double total,price; 

    public BillDetail() {
    }

    public BillDetail(String oID, String pname, String catename, String description, String image, String dateCreate, String cphone, String cname, String cAdress, int quantity, double total, double price) {
        this.oID = oID;
        this.pname = pname;
        this.catename = catename;
        this.description = description;
        this.image = image;
        this.dateCreate = dateCreate;
        this.cphone = cphone;
        this.cname = cname;
        this.cAdress = cAdress;
        this.quantity = quantity;
        this.total = total;
        this.price = price;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getcAdress() {
        return cAdress;
    }

    public void setcAdress(String cAdress) {
        this.cAdress = cAdress;
    }
    
    public String getCatename() {
        return catename;
    }
    
    public void setCatename(String catename) {
        this.catename = catename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getoID() {
        return oID;
    }

    public void setoID(String oID) {
        this.oID = oID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return catename+" "+cphone; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
