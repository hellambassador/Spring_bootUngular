package org.bdHibernate.bd;

import org.hibernate.Session;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "manufacturer", schema = "store1", catalog = "")
public class ManufacturerEntity {
    private int idManufacturer;
    private String name;
    private String address;

    @Id
    @Column(name = "ID_manufacturer", nullable = false)
    public int getIdManufacturer() {
        return idManufacturer;
    }

    public void setIdManufacturer(int idManufacturer) {
        this.idManufacturer = idManufacturer;
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
    @Column(name = "Address", nullable = true, length = 20)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManufacturerEntity that = (ManufacturerEntity) o;

        if (idManufacturer != that.idManufacturer) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    public static List<ManufacturerEntity> show() {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            List<ManufacturerEntity> manufacturerEntities = session.createQuery( "from ManufacturerEntity").list();
            session.getTransaction().commit();
            return manufacturerEntities;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<ManufacturerEntity>();
    }
    public static void delete(int id) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            List<ManufacturerEntity> manufacturerEntities = session.createQuery("From ManufacturerEntity ").list();

            for (ManufacturerEntity manuf : manufacturerEntities) {
                if (id ==manuf.getIdManufacturer())
                {
                    session.delete(manuf);
                }
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void add(String name, String address) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            ManufacturerEntity manufacturerEntity = new ManufacturerEntity();
            manufacturerEntity.setName(name);
            manufacturerEntity.setAddress(address);
            session.save(manufacturerEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void update(int id,String name,String address) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            List<ManufacturerEntity> manufacturerEntities = session.createQuery("From ManufacturerEntity ").list();
            for (ManufacturerEntity manuf : manufacturerEntities) {
                if (id ==manuf.getIdManufacturer())
                {
                    manuf.setName(name);
                    manuf.setAddress(address);
                    session.update(manuf);
                }
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public int hashCode() {
        int result = idManufacturer;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
