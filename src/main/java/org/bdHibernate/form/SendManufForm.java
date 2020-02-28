package org.bdHibernate.form;

public class SendManufForm {
    private int idManufacturer;
    private String name;
    private String address;

    public SendManufForm() {

    }

    public SendManufForm(int idManufacturer, String name, String address) {
        this.idManufacturer = idManufacturer;
        this.name = name;
        this.address = address;
    }

    public int getidManufacturer() {
        return idManufacturer;
    }

    public void setidManufacturer(int fromAccountId) {
        this.idManufacturer = fromAccountId;
    }

    public String getname() {
        return name;
    }

    public void setname(String toAccountId) {
        this.name = toAccountId;
    }

    public String getaddress() {
        return address;
    }

    public void setaddress(String amount) {
        this.address = amount;
    }

}
