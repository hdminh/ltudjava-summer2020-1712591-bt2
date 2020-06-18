package hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "danhsachlop", schema = "qlsv")
@IdClass(DanhsachlopEntityPK.class)
public class DanhsachlopEntity {
    private String sinhvien;
    private String monhoc;
    private String hoten;
    private String gioitinh;
    private String cmnd;
    private LophocEntity lop;

    @Id
    @Column(name = "sinhvien")
    public String getSinhvien() {
        return sinhvien;
    }

    public void setSinhvien(String sinhvien) {
        this.sinhvien = sinhvien;
    }

    @Id
    @Column(name = "monhoc")
    public String getMonhoc() {
        return monhoc;
    }

    public void setMonhoc(String monhoc) {
        this.monhoc = monhoc;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DanhsachlopEntity that = (DanhsachlopEntity) o;

        if (sinhvien != null ? !sinhvien.equals(that.sinhvien) : that.sinhvien != null) return false;
        if (monhoc != null ? !monhoc.equals(that.monhoc) : that.monhoc != null) return false;
        if (hoten != null ? !hoten.equals(that.hoten) : that.hoten != null) return false;
        if (gioitinh != null ? !gioitinh.equals(that.gioitinh) : that.gioitinh != null) return false;
        if (cmnd != null ? !cmnd.equals(that.cmnd) : that.cmnd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sinhvien != null ? sinhvien.hashCode() : 0;
        result = 31 * result + (monhoc != null ? monhoc.hashCode() : 0);
        result = 31 * result + (hoten != null ? hoten.hashCode() : 0);
        result = 31 * result + (gioitinh != null ? gioitinh.hashCode() : 0);
        result = 31 * result + (cmnd != null ? cmnd.hashCode() : 0);
        return result;
    }

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "lop", referencedColumnName = "malop")
    public LophocEntity getLop() {
        return lop;
    }

    public void setLop(LophocEntity lop) {
        this.lop = lop;
    }
}
