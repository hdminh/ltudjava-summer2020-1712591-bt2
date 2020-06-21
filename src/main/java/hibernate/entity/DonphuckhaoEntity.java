package hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "donphuckhao", schema = "qlsv")
public class DonphuckhaoEntity {
    private int madon;
    private String sinhvien;
    private String monhoc;
    private String lydo;
    private Integer cotdiem;
    private Double diemtruocpk;
    private Double diemsaupk;
    private String trangthai;
    private Integer dotphuckhao;

    @Id
    @Column(name = "madon")
    public int getMadon() {
        return madon;
    }

    public void setMadon(int madon) {
        this.madon = madon;
    }

    @Basic
    @Column(name = "sinhvien")
    public String getSinhvien() {
        return sinhvien;
    }

    public void setSinhvien(String sinhvien) {
        this.sinhvien = sinhvien;
    }

    @Basic
    @Column(name = "monhoc")
    public String getMonhoc() {
        return monhoc;
    }

    public void setMonhoc(String monhoc) {
        this.monhoc = monhoc;
    }

    @Basic
    @Column(name = "lydo")
    public String getLydo() {
        return lydo;
    }

    public void setLydo(String lydo) {
        this.lydo = lydo;
    }

    @Basic
    @Column(name = "cotdiem")
    public Integer getCotdiem() {
        return cotdiem;
    }

    public void setCotdiem(Integer cotdiem) {
        this.cotdiem = cotdiem;
    }

    @Basic
    @Column(name = "diemtruocpk")
    public Double getDiemtruocpk() {
        return diemtruocpk;
    }

    public void setDiemtruocpk(Double diemtruocpk) {
        this.diemtruocpk = diemtruocpk;
    }

    @Basic
    @Column(name = "diemsaupk")
    public Double getDiemsaupk() {
        return diemsaupk;
    }

    public void setDiemsaupk(Double diemsaupk) {
        this.diemsaupk = diemsaupk;
    }

    @Basic
    @Column(name = "trangthai")
    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    @Basic
    @Column(name = "dotphuckhao")
    public Integer getDotphuckhao() {
        return dotphuckhao;
    }

    public void setDotphuckhao(Integer dotphuckhao) {
        this.dotphuckhao = dotphuckhao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DonphuckhaoEntity that = (DonphuckhaoEntity) o;

        if (madon != that.madon) return false;
        if (sinhvien != null ? !sinhvien.equals(that.sinhvien) : that.sinhvien != null) return false;
        if (monhoc != null ? !monhoc.equals(that.monhoc) : that.monhoc != null) return false;
        if (lydo != null ? !lydo.equals(that.lydo) : that.lydo != null) return false;
        if (cotdiem != null ? !cotdiem.equals(that.cotdiem) : that.cotdiem != null) return false;
        if (diemtruocpk != null ? !diemtruocpk.equals(that.diemtruocpk) : that.diemtruocpk != null) return false;
        if (diemsaupk != null ? !diemsaupk.equals(that.diemsaupk) : that.diemsaupk != null) return false;
        if (trangthai != null ? !trangthai.equals(that.trangthai) : that.trangthai != null) return false;
        if (dotphuckhao != null ? !dotphuckhao.equals(that.dotphuckhao) : that.dotphuckhao != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = madon;
        result = 31 * result + (sinhvien != null ? sinhvien.hashCode() : 0);
        result = 31 * result + (monhoc != null ? monhoc.hashCode() : 0);
        result = 31 * result + (lydo != null ? lydo.hashCode() : 0);
        result = 31 * result + (cotdiem != null ? cotdiem.hashCode() : 0);
        result = 31 * result + (diemtruocpk != null ? diemtruocpk.hashCode() : 0);
        result = 31 * result + (diemsaupk != null ? diemsaupk.hashCode() : 0);
        result = 31 * result + (trangthai != null ? trangthai.hashCode() : 0);
        result = 31 * result + (dotphuckhao != null ? dotphuckhao.hashCode() : 0);
        return result;
    }
}
