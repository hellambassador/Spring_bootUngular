package org.bdHibernate.bd;

import javax.persistence.*;

@Entity
@Table(name = "expense", schema = "store1", catalog = "")
public class ExpenseEntity {
    private int idExpenses;
    private Integer count;

    @Id
    @Column(name = "ID_Expenses", nullable = false)
    public int getIdExpenses() {
        return idExpenses;
    }

    public void setIdExpenses(int idExpenses) {
        this.idExpenses = idExpenses;
    }

    @Basic
    @Column(name = "count", nullable = true)
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpenseEntity that = (ExpenseEntity) o;

        if (idExpenses != that.idExpenses) return false;
        if (count != null ? !count.equals(that.count) : that.count != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idExpenses;
        result = 31 * result + (count != null ? count.hashCode() : 0);
        return result;
    }
}
