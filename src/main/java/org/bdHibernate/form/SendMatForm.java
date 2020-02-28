package org.bdHibernate.form;

import org.bdHibernate.bd.ManufacturerEntity;
import org.bdHibernate.bd.ToolEntity;

import java.util.HashSet;
import java.util.Set;

public class SendMatForm {

    private int idMaterial;
    private String name;
    private Double cost;
    private ManufacturerEntity manufacturerByIdManufacturer;

    private Set<ToolEntity> tools = new HashSet<>();

    public SendMatForm() {

    }

    public SendMatForm(int idMaterial, String name, Double cost,ManufacturerEntity manufacturerByIdManufacturer,Set<ToolEntity> tools) {
        this.idMaterial = idMaterial;
        this.name = name;
        this.cost = cost;
        this.manufacturerByIdManufacturer =manufacturerByIdManufacturer;
        this.tools=tools;


    }
    public int getidMaterial() {
        return idMaterial;
    }
    public void setidMaterial(int fromAccountId) {
        this.idMaterial = fromAccountId;
    }

    public String getname() {
        return name;
    }
    public void setname(String fromAccountId) {
        this.name = fromAccountId;
    }

    public Double getcost() {
        return cost;
    }
    public void setcost(Double fromAccountId) {
        this.cost = fromAccountId;
    }

    public ManufacturerEntity getmanufacturerByIdManufacturer() {
        return manufacturerByIdManufacturer;
    }
    public void setmanufacturerByIdManufacturer(ManufacturerEntity fromAccountId) {
        this.manufacturerByIdManufacturer = fromAccountId;
    }

    public Set<ToolEntity> gettools() {
        return tools;
    }
    public void settools(Set<ToolEntity> fromAccountId) {
        this.tools = fromAccountId;
    }

}
