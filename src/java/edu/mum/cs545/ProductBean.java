/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545;

import edu.mum.cs545.domain.Product;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author 984986
 */
@Named
@SessionScoped
public class ProductBean implements Serializable{
    private String productId;
    private String name;
    private BigDecimal unitPrice;
    private String description;
    private String manufacturer;
    private String category;
    private long unitsInStock;
    private long unitsInOrder;
    private boolean discontinued;
    private String condition;

    private List<Product> listOfProduct;
    
    public ProductBean() {
        listOfProduct = new ArrayList<Product>();
        
        Product iPhone = new Product("P1234", "iPhone", BigDecimal.valueOf(499));
        iPhone.setDescription("iPhone 7");
        iPhone.setCategory("Smart Phone");
        iPhone.setManufacturer("Apple");
        iPhone.setUnitsInStock(100);

        Product nexus7 = new Product("P1235", "Nexus 7", BigDecimal.valueOf(350));
        nexus7.setDescription("Nexus 7 tablet");
        nexus7.setCategory("Tablet");
        nexus7.setManufacturer("Asus");
        nexus7.setUnitsInStock(500);

        Product dellLatitude = new Product("P1236", "Dell Latitude", BigDecimal.valueOf(700));
        dellLatitude.setDescription("Dell latitude laptop");
        dellLatitude.setCategory("Laptop");
        dellLatitude.setManufacturer("Dell");
        dellLatitude.setUnitsInStock(50);

        listOfProduct.add(iPhone);
        listOfProduct.add(nexus7);
        listOfProduct.add(dellLatitude);
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(long unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public long getUnitsInOrder() {
        return unitsInOrder;
    }

    public void setUnitsInOrder(long unitsInOrder) {
        this.unitsInOrder = unitsInOrder;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List<Product> getListOfProduct() {
        return listOfProduct;
    }
    
    public String onAdd(){
        Product product = new Product();
        product.setProductId(productId);
        product.setCategory(category);
        product.setCondition(condition);
        product.setDiscontinued(discontinued);
        product.setDescription(description);
        product.setManufacturer(manufacturer);
        product.setName(name);
        product.setUnitPrice(unitPrice);
        product.setUnitsInOrder(unitsInOrder);
        product.setUnitsInStock(unitsInStock);
        
        listOfProduct.add(product);
        
        return "products";
    }
}
