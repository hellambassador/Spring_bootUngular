package org.bdHibernate.bd;

import javax.persistence.*;

@Entity
@Table(name = "byer", schema = "store1", catalog = "")
public class ByerEntity {
    private int idByer;
    private String fio;
    private String contact;

    @Id
    @Column(name = "ID_Byer", nullable = false)
    public int getIdByer() {
        return idByer;
    }

    public void setIdByer(int idByer) {
        this.idByer = idByer;
    }

    @Basic
    @Column(name = "FIO", nullable = true, length = 20)
    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Basic
    @Column(name = "Contact", nullable = true, length = 20)
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ByerEntity that = (ByerEntity) o;

        if (idByer != that.idByer) return false;
        if (fio != null ? !fio.equals(that.fio) : that.fio != null) return false;
        if (contact != null ? !contact.equals(that.contact) : that.contact != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idByer;
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        return result;
    }
}
