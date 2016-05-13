/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545;

import edu.mum.cs545.domain.ProductEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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

    private List<ProductEntity> listOfProduct;
    
    @EJB //this annotation causes the container to inject this dependency
    private edu.mum.cs545.db.ProductEntityFacade ejbProductFacade;

               

    @PostConstruct  //this annotation causes this method to run after the constructor completes
    public void init() { //create a few tea products, place in database, and load into the teaEntities list
       
        ProductEntity iPhone = new ProductEntity();
        iPhone.setProductId("P1234");
        iPhone.setName("iPhone");
        iPhone.setUnitPrice(BigDecimal.valueOf(499));
        iPhone.setDescription("iPhone 7");
        iPhone.setCategory("Smart Phone");
        iPhone.setManufacturer("Apple");
        iPhone.setUnitsInStock(100);

        ProductEntity nexus7 = new ProductEntity();
        nexus7.setProductId("P1235");
        nexus7.setName("Nexus 7");
        nexus7.setUnitPrice(BigDecimal.valueOf(350));
        
        nexus7.setDescription("Nexus 7 tablet");
        nexus7.setCategory("Tablet");
        nexus7.setManufacturer("Asus");
        nexus7.setUnitsInStock(500);

        ProductEntity dellLatitude = new ProductEntity();
        dellLatitude.setProductId("P1236");
        dellLatitude.setName("Dell Latitude");
        dellLatitude.setUnitPrice(BigDecimal.valueOf(700));
        
        dellLatitude.setDescription("Dell latitude laptop");
        dellLatitude.setCategory("Laptop");
        dellLatitude.setManufacturer("Dell");
        dellLatitude.setUnitsInStock(50);

       ejbProductFacade.create(iPhone);
       ejbProductFacade.create(nexus7);
       ejbProductFacade.create(dellLatitude);
                  

       List<ProductEntity> productEntities = ejbProductFacade.findAll();
       listOfProduct = new ArrayList<>();
      

       for (ProductEntity proEnt: productEntities) {

           listOfProduct.add(proEnt);

       }

    }  
    
    public ProductBean() {
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

    public List<ProductEntity> getListOfProduct() {
        return listOfProduct;
    }
    
    public String onAdd(){
        ProductEntity product = new ProductEntity();
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
        ejbProductFacade.create(product);
        
        return "products";
    }
}
