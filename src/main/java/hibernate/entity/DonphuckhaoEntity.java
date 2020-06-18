package hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "donphuckhao", schema = "qlsv", catalog = "")
public class DonphuckhaoEntity {
    private int madon;
    private String sinhvien;
    private String monhoc;
    private String lydo;
    private Double diemgktruoc;
    private Double diemcktruoc;
    private Double diemkhactruoc;
    private Double diemtongtruoc;
    private Double diemgksau;
    private Double diemcksau;
    private Double diemkhacsau;
    private Double diemtongsau;
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
    @Column(name = "diemgktruoc")
    public Double getDiemgktruoc() {
        return diemgktruoc;
    }

    public void setDiemgktruoc(Double diemgktruoc) {
        this.diemgktruoc = diemgktruoc;
    }

    @Basic
    @Column(name = "diemcktruoc")
    public Double getDiemcktruoc() {
        return diemcktruoc;
    }

    public void setDiemcktruoc(Double diemcktruoc) {
        this.diemcktruoc = diemcktruoc;
    }

    @Basic
    @Column(name = "diemkhactruoc")
    public Double getDiemkhactruoc() {
        return diemkhactruoc;
    }

    public void setDiemkhactruoc(Double diemkhactruoc) {
        this.diemkhactruoc = diemkhactruoc;
    }

    @Basic
    @Column(name = "diemtongtruoc")
    public Double getDiemtongtruoc() {
        return diemtongtruoc;
    }

    public void setDiemtongtruoc(Double diemtongtruoc) {
        this.diemtongtruoc = diemtongtruoc;
    }

    @Basic
    @Column(name = "diemgksau")
    public Double getDiemgksau() {
        return diemgksau;
    }

    public void setDiemgksau(Double diemgksau) {
        this.diemgksau = diemgksau;
    }

    @Basic
    @Column(name = "diemcksau")
    public Double getDiemcksau() {
        return diemcksau;
    }

    public void setDiemcksau(Double diemcksau) {
        this.diemcksau = diemcksau;
    }

    @Basic
    @Column(name = "diemkhacsau")
    public Double getDiemkhacsau() {
        return diemkhacsau;
    }

    public void setDiemkhacsau(Double diemkhacsau) {
        this.diemkhacsau = diemkhacsau;
    }

    @Basic
    @Column(name = "diemtongsau")
    public Double getDiemtongsau() {
        return diemtongsau;
    }

    public void setDiemtongsau(Double diemtongsau) {
        this.diemtongsau = diemtongsau;
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
        if (diemgktruoc != null ? !diemgktruoc.equals(that.diemgktruoc) : that.diemgktruoc != null) return false;
        if (diemcktruoc != null ? !diemcktruoc.equals(that.diemcktruoc) : that.diemcktruoc != null) return false;
        if (diemkhactruoc != null ? !diemkhactruoc.equals(that.diemkhactruoc) : that.diemkhactruoc != null)
            return false;
        if (diemtongtruoc != null ? !diemtongtruoc.equals(that.diemtongtruoc) : that.diemtongtruoc != null)
            return false;
        if (diemgksau != null ? !diemgksau.equals(that.diemgksau) : that.diemgksau != null) return false;
        if (diemcksau != null ? !diemcksau.equals(that.diemcksau) : that.diemcksau != null) return false;
        if (diemkhacsau != null ? !diemkhacsau.equals(that.diemkhacsau) : that.diemkhacsau != null) return false;
        if (diemtongsau != null ? !diemtongsau.equals(that.diemtongsau) : that.diemtongsau != null) return false;
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
        result = 31 * result + (diemgktruoc != null ? diemgktruoc.hashCode() : 0);
        result = 31 * result + (diemcktruoc != null ? diemcktruoc.hashCode() : 0);
        result = 31 * result + (diemkhactruoc != null ? diemkhactruoc.hashCode() : 0);
        result = 31 * result + (diemtongtruoc != null ? diemtongtruoc.hashCode() : 0);
        result = 31 * result + (diemgksau != null ? diemgksau.hashCode() : 0);
        result = 31 * result + (diemcksau != null ? diemcksau.hashCode() : 0);
        result = 31 * result + (diemkhacsau != null ? diemkhacsau.hashCode() : 0);
        result = 31 * result + (diemtongsau != null ? diemtongsau.hashCode() : 0);
        result = 31 * result + (trangthai != null ? trangthai.hashCode() : 0);
        result = 31 * result + (dotphuckhao != null ? dotphuckhao.hashCode() : 0);
        return result;
    }
}
