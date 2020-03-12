package org.bdHibernate.bd;

import org.hibernate.Session;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "material", schema = "store1", catalog = "")
public class MaterialEntity {
    private int idMaterial;
    private String name;
    private Double cost;
    private ManufacturerEntity manufacturerByIdManufacturer;

    private Set<ToolEntity> tools = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "material_tool",
            //foreign key for CarsEntity in employee_car table
            joinColumns = @JoinColumn(name = "material_id"),
            //foreign key for other side - EmployeeEntity in employee_car table
            inverseJoinColumns = @JoinColumn(name = "tool_id"))
    public Set<ToolEntity> getTool() {
        return tools;
    }


    public void setTool(Set<ToolEntity> cars) {
        this.tools = cars;
    }

    public void addtool(ToolEntity tool) {
        tools.add(tool);
    }

    public void removeAllTools() {
        //tools.remove(tool);
        tools.clear();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Material", nullable = false)
    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
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
    @Column(name = "Cost", nullable = true, precision = 0)
    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public static List<MaterialEntity> show() {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            List<MaterialEntity> manufacturerEntities = session.createQuery("from MaterialEntity").list();
            for (MaterialEntity q : manufacturerEntities) {
                q.getManufacturerByIdManufacturer().getName();
                for (ToolEntity w : q.getTool()) {
                    w.getName();
                }
            }
            session.getTransaction().commit();
            return manufacturerEntities;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<MaterialEntity>();
    }

    public static List<MaterialEntity> showFilter(Filter filter) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            List<MaterialEntity> manufacturerEntities = session.createQuery("from MaterialEntity").list();
            List<MaterialEntity> manufacturerEntities1 = session.createQuery("from MaterialEntity").list();
            for (MaterialEntity q : manufacturerEntities) {
                q.getManufacturerByIdManufacturer().getName();
                for (ToolEntity w : q.getTool()) {
                    w.getName();
                }
            }
            if (filter.Name != "")
                for (MaterialEntity q : manufacturerEntities) {
                    if (!filter.Name.equals(q.name)) {
                        manufacturerEntities1.remove(q);
                    }
                }
            if (filter.minCost != null)
                for (MaterialEntity q : manufacturerEntities) {
                    if (filter.minCost > q.cost) {
                        manufacturerEntities1.remove(q);
                    }
                }
            if (filter.maxCost != null)
                for (MaterialEntity q : manufacturerEntities) {
                    if (filter.maxCost < q.cost) {
                        manufacturerEntities1.remove(q);
                    }
                }
            if (filter.toolSort != "")
                for (MaterialEntity q : manufacturerEntities) {
                boolean b=false;
                    for (ToolEntity w : q.getTool()) {
                        if (filter.toolSort.equals(w.getName()))
                            b=true;
                    }
                    if (!b)
                        manufacturerEntities1.remove(q);
                }
            manufacturerEntities = manufacturerEntities1;
            session.getTransaction().commit();
            return manufacturerEntities;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<MaterialEntity>();
    }

    public MaterialEntity add() {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(this);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return this;
    }

    public void update() {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.update(this);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            List<MaterialEntity> manufacturerEntities = session.createQuery("From MaterialEntity ").list();
            for (MaterialEntity manuf : manufacturerEntities) {
                if (id == manuf.getIdMaterial()) {
                    session.delete(manuf);
                }
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaterialEntity that = (MaterialEntity) o;

        if (idMaterial != that.idMaterial) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMaterial;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ID_manufacturer", referencedColumnName = "ID_manufacturer")
    public ManufacturerEntity getManufacturerByIdManufacturer() {
        return manufacturerByIdManufacturer;
    }

    public void setManufacturerByIdManufacturer(ManufacturerEntity manufacturerByIdManufacturer) {
        this.manufacturerByIdManufacturer = manufacturerByIdManufacturer;
    }
}
