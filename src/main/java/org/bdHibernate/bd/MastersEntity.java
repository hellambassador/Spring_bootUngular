package org.bdHibernate.bd;

import javax.persistence.*;

@Entity
@Table(name = "masters", schema = "store1", catalog = "")
public class MastersEntity {
    private int idMastera;
    private String fio;
    private String workExperience;
    private Integer costPerHour;

    @Id
    @Column(name = "ID_Mastera", nullable = false)
    public int getIdMastera() {
        return idMastera;
    }

    public void setIdMastera(int idMastera) {
        this.idMastera = idMastera;
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
    @Column(name = "work_experience", nullable = true, length = 20)
    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    @Basic
    @Column(name = "cost_per_hour", nullable = true)
    public Integer getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(Integer costPerHour) {
        this.costPerHour = costPerHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MastersEntity that = (MastersEntity) o;

        if (idMastera != that.idMastera) return false;
        if (fio != null ? !fio.equals(that.fio) : that.fio != null) return false;
        if (workExperience != null ? !workExperience.equals(that.workExperience) : that.workExperience != null)
            return false;
        if (costPerHour != null ? !costPerHour.equals(that.costPerHour) : that.costPerHour != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMastera;
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        result = 31 * result + (workExperience != null ? workExperience.hashCode() : 0);
        result = 31 * result + (costPerHour != null ? costPerHour.hashCode() : 0);
        return result;
    }
}
