package com.timxyz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class AuditItem extends BaseModel {
    private boolean present;
    private boolean skuCorrect;
    private String note;
    private Item item;
    private Audit audit;
    private Status status;

    public AuditItem() {
    }

    @Basic
    @Column(name = "present", nullable = false)
    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    @Basic
    @Column(name = "skuCorrect", nullable = false)
    public Boolean getSkuCorrect() {
        return skuCorrect;
    }

    public void setSkuCorrect(Boolean skuCorrect) {
        this.skuCorrect = skuCorrect;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @ManyToOne
    @JoinColumn(name = "itemId", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @ManyToOne
    @JoinColumn(name = "auditId", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    @ManyToOne
    @JoinColumn(name = "statusId", referencedColumnName = "id")
    @JsonIgnore
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
