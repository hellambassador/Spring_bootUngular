package org.bdHibernate.bd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.Session;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tool", schema = "store1", catalog = "")
public class ToolEntity {
    private int idTools;
    private String name;
    private Integer timeToCreate;

    @JsonIgnoreProperties
    private Set<MaterialEntity> materials = new HashSet<>();

    //    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "material_tool",
            //foreign key for CarsEntity in employee_car table
            joinColumns = @JoinColumn(name = "tool_id"),
            //foreign key for other side - EmployeeEntity in employee_car table
            inverseJoinColumns = @JoinColumn(name = "material_id"))

    public Set<MaterialEntity> getMaterial() {
        return materials;
    }

    public void setMaterial(Set<MaterialEntity> cars) {
        this.materials = cars;
    }

    public void addmaterial(MaterialEntity material) {
        materials.add(material);
    }

    @Id
    @Column(name = "ID_Tools", nullable = false)
    public int getIdTools() {
        return idTools;
    }

    public void setIdTools(int idTools) {
        this.idTools = idTools;
    }

    @Basic
    @Column(name = "Name_", nullable = true, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Time_to_create", nullable = true)
    public Integer getTimeToCreate() {
        return timeToCreate;
    }

    public void setTimeToCreate(Integer timeToCreate) {
        this.timeToCreate = timeToCreate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToolEntity that = (ToolEntity) o;

        if (idTools != that.idTools) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (timeToCreate != null ? !timeToCreate.equals(that.timeToCreate) : that.timeToCreate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTools;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (timeToCreate != null ? timeToCreate.hashCode() : 0);
        return result;
    }

    public static List<ToolEntity> show() throws Exception {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            List<ToolEntity> manufacturerEntities = session.createQuery("from ToolEntity").list();
            session.getTransaction().commit();
            return manufacturerEntities;
        } catch (Exception e) {
            throw e;
        }
    }
}
