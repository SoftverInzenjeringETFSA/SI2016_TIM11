package com.timxyz.controllers.forms.Item;

import com.timxyz.models.AuditItem;
import com.timxyz.models.Category;
import com.timxyz.models.Location;
import com.timxyz.models.PastAuditItem;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Dario on 5/9/2017.
 */
public class ItemCreateForm {

    private Long Id;

    @Size(min = 4, max = 45) @NotNull
    private String skuNumber;

    @Size(min = 4, max = 255) @NotNull
    private String name;

    @Size(min = 1, max = 4) @NotNull
    private String unitOfMeasurement;

    @Size(min = 4, max = 255) @NotNull
    private String purchasedBy;

    @Size(min = 4, max = 255) @NotNull
    private String personResponsible;

    @NotNull
    private Timestamp dateOfPurchase;

    @Min(0) @NotNull
    private BigDecimal value;

    @NotNull
    private Long categoryID;

    @NotNull
    private Long locationID;

    public Long getId() {
        return Id;
    }

    public void setId( Long parentId ) {
        this.Id = parentId;
    }

    public String getSkuNumber() {
        return skuNumber;
    }

    public void setSkuNumber( String skuNumber ) {
        this.skuNumber = skuNumber;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement( String unitOfMeasurement ) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public String getPurchasedBy() {
        return purchasedBy;
    }

    public void setPurchasedBy( String purchasedBy ) {
        this.purchasedBy = purchasedBy;
    }

    public String getPersonResponsible() {
        return personResponsible;
    }

    public void setPersonResponsible( String personResponsible ) {
        this.personResponsible = personResponsible;
    }

    public Timestamp getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase( Timestamp dateOfPurchase ) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue( BigDecimal value ) {
        this.value = value;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID( Long categoryID ) {
        this.categoryID = categoryID;
    }

    public Long getLocationID() {
        return locationID;
    }

    public void setLocationID( Long locationID ) {
        this.locationID = locationID;
    }
}
