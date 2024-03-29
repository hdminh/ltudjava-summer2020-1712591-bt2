package hibernate.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sinhvien", schema = "qlsv")
public class SinhvienEntity {
    @Id
    private String mssv;
    private String hoten;
    private String gioitinh;
    private String cmnd;


    @Column(name = "mssv")
    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    @Basic
    @Column(name = "hoten")
    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    @Basic
    @Column(name = "gioitinh")
    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    @Basic
    @Column(name = "cmnd")
    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lop")
    private LophocEntity lophoc;
    public LophocEntity getLop() {
        return lophoc;
    }

    public void setLop(LophocEntity lop) {
        this.lophoc = lop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SinhvienEntity that = (SinhvienEntity) o;

        if (mssv != null ? !mssv.equals(that.mssv) : that.mssv != null) return false;
        if (hoten != null ? !hoten.equals(that.hoten) : that.hoten != null) return false;
        if (gioitinh != null ? !gioitinh.equals(that.gioitinh) : that.gioitinh != null) return false;
        if (cmnd != null ? !cmnd.equals(that.cmnd) : that.cmnd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mssv != null ? mssv.hashCode() : 0;
        result = 31 * result + (hoten != null ? hoten.hashCode() : 0);
        result = 31 * result + (gioitinh != null ? gioitinh.hashCode() : 0);
        result = 31 * result + (cmnd != null ? cmnd.hashCode() : 0);
        return result;
    }
}
