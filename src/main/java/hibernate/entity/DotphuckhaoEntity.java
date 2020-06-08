package hibernate.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "dotphuckhao", schema = "qlsv", catalog = "")
public class DotphuckhaoEntity {
    private int madot;
    private Date ngaybatdau;
    private Date ngayketthuc;

    @Id
    @Column(name = "madot")
    public int getMadot() {
        return madot;
    }

    public void setMadot(int madot) {
        this.madot = madot;
    }

    @Basic
    @Column(name = "ngaybatdau")
    public Date getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(Date ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    @Basic
    @Column(name = "ngayketthuc")
    public Date getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(Date ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DotphuckhaoEntity that = (DotphuckhaoEntity) o;

        if (madot != that.madot) return false;
        if (ngaybatdau != null ? !ngaybatdau.equals(that.ngaybatdau) : that.ngaybatdau != null) return false;
        if (ngayketthuc != null ? !ngayketthuc.equals(that.ngayketthuc) : that.ngayketthuc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = madot;
        result = 31 * result + (ngaybatdau != null ? ngaybatdau.hashCode() : 0);
        result = 31 * result + (ngayketthuc != null ? ngayketthuc.hashCode() : 0);
        return result;
    }
}
