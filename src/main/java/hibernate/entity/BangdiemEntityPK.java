package hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class BangdiemEntityPK implements Serializable {
    private String sinhvien;
    private String monhoc;

    @Column(name = "sinhvien")
    @Id
    public String getSinhvien() {
        return sinhvien;
    }

    public void setSinhvien(String sinhvien) {
        this.sinhvien = sinhvien;
    }

    @Column(name = "monhoc")
    @Id
    public String getMonhoc() {
        return monhoc;
    }

    public void setMonhoc(String monhoc) {
        this.monhoc = monhoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BangdiemEntityPK that = (BangdiemEntityPK) o;

        if (sinhvien != null ? !sinhvien.equals(that.sinhvien) : that.sinhvien != null) return false;
        if (monhoc != null ? !monhoc.equals(that.monhoc) : that.monhoc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sinhvien != null ? sinhvien.hashCode() : 0;
        result = 31 * result + (monhoc != null ? monhoc.hashCode() : 0);
        return result;
    }
}
