package hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "bangdiem", schema = "qlsv", catalog = "")
@IdClass(BangdiemEntityPK.class)
public class BangdiemEntity {
    private String sinhvien;
    private String hoten;
    private String lophoc;
    private String monhoc;
    private Double diemgk;
    private Double diemck;
    private Double diemkhac;
    private Double diemtong;
    private Byte dau;

    @Id
    @Column(name = "sinhvien")
    public String getSinhvien() {
        return sinhvien;
    }

    public void setSinhvien(String sinhvien) {
        this.sinhvien = sinhvien;
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
    @Column(name = "lophoc")
    public String getLophoc() {
        return lophoc;
    }

    public void setLophoc(String lophoc) {
        this.lophoc = lophoc;
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
    @Column(name = "diemgk")
    public Double getDiemgk() {
        return diemgk;
    }

    public void setDiemgk(Double diemgk) {
        this.diemgk = diemgk;
    }

    @Basic
    @Column(name = "diemck")
    public Double getDiemck() {
        return diemck;
    }

    public void setDiemck(Double diemck) {
        this.diemck = diemck;
    }

    @Basic
    @Column(name = "diemkhac")
    public Double getDiemkhac() {
        return diemkhac;
    }

    public void setDiemkhac(Double diemkhac) {
        this.diemkhac = diemkhac;
    }

    @Basic
    @Column(name = "diemtong")
    public Double getDiemtong() {
        return diemtong;
    }

    public void setDiemtong(Double diemtong) {
        this.diemtong = diemtong;
    }

    @Basic
    @Column(name = "dau")
    public Byte getDau() {
        return dau;
    }

    public void setDau(Byte dau) {
        this.dau = dau;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BangdiemEntity that = (BangdiemEntity) o;

        if (sinhvien != null ? !sinhvien.equals(that.sinhvien) : that.sinhvien != null) return false;
        if (hoten != null ? !hoten.equals(that.hoten) : that.hoten != null) return false;
        if (lophoc != null ? !lophoc.equals(that.lophoc) : that.lophoc != null) return false;
        if (monhoc != null ? !monhoc.equals(that.monhoc) : that.monhoc != null) return false;
        if (diemgk != null ? !diemgk.equals(that.diemgk) : that.diemgk != null) return false;
        if (diemck != null ? !diemck.equals(that.diemck) : that.diemck != null) return false;
        if (diemkhac != null ? !diemkhac.equals(that.diemkhac) : that.diemkhac != null) return false;
        if (diemtong != null ? !diemtong.equals(that.diemtong) : that.diemtong != null) return false;
        if (dau != null ? !dau.equals(that.dau) : that.dau != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sinhvien != null ? sinhvien.hashCode() : 0;
        result = 31 * result + (hoten != null ? hoten.hashCode() : 0);
        result = 31 * result + (lophoc != null ? lophoc.hashCode() : 0);
        result = 31 * result + (monhoc != null ? monhoc.hashCode() : 0);
        result = 31 * result + (diemgk != null ? diemgk.hashCode() : 0);
        result = 31 * result + (diemck != null ? diemck.hashCode() : 0);
        result = 31 * result + (diemkhac != null ? diemkhac.hashCode() : 0);
        result = 31 * result + (diemtong != null ? diemtong.hashCode() : 0);
        result = 31 * result + (dau != null ? dau.hashCode() : 0);
        return result;
    }
}
