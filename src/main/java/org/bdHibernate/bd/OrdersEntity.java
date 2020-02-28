package org.bdHibernate.bd;

import javax.persistence.*;

@Entity
@Table(name = "orders", schema = "store1", catalog = "")
public class OrdersEntity {
    private int idOrder;
    private Double cost;
    private MastersEntity mastersByIdMastera;
    private ByerEntity byerByIdByer;

    @Id
    @Column(name = "ID_Order", nullable = false)
    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    @Basic
    @Column(name = "Cost", nullable = true, precision = 0)
    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (idOrder != that.idOrder) return false;
        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOrder;
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Mastera", referencedColumnName = "ID_Mastera")
    public MastersEntity getMastersByIdMastera() {
        return mastersByIdMastera;
    }

    public void setMastersByIdMastera(MastersEntity mastersByIdMastera) {
        this.mastersByIdMastera = mastersByIdMastera;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Byer", referencedColumnName = "ID_Byer")
    public ByerEntity getByerByIdByer() {
        return byerByIdByer;
    }

    public void setByerByIdByer(ByerEntity byerByIdByer) {
        this.byerByIdByer = byerByIdByer;
    }
}
