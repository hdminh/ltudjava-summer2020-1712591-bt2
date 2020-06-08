package hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "donphuckhao", schema = "qlsv", catalog = "")
public class DonphuckhaoEntity {
    private int madon;
    private String lydo;
    private String trangthai;
    private ChitietphuckhaoEntity chitiet;
    private DotphuckhaoEntity dotphuckhao;
    private MonhocEntity monhoc;
    private SinhvienEntity sinhvien;

    @Id
    @Column(name = "madon")
    public int getMadon() {
        return madon;
    }

    public void setMadon(int madon) {
        this.madon = madon;
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
    @Column(name = "trangthai")
    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DonphuckhaoEntity that = (DonphuckhaoEntity) o;

        if (madon != that.madon) return false;
        if (lydo != null ? !lydo.equals(that.lydo) : that.lydo != null) return false;
        if (trangthai != null ? !trangthai.equals(that.trangthai) : that.trangthai != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = madon;
        result = 31 * result + (lydo != null ? lydo.hashCode() : 0);
        result = 31 * result + (trangthai != null ? trangthai.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "chitiet", referencedColumnName = "machitiet")
    public ChitietphuckhaoEntity getChitiet() {
        return chitiet;
    }

    public void setChitiet(ChitietphuckhaoEntity chitiet) {
        this.chitiet = chitiet;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dotphuckhao", referencedColumnName = "madot")
    public DotphuckhaoEntity getDotphuckhao() {
        return dotphuckhao;
    }

    public void setDotphuckhao(DotphuckhaoEntity dotphuckhao) {
        this.dotphuckhao = dotphuckhao;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "monhoc", referencedColumnName = "mamon")
    public MonhocEntity getMonhoc() {
        return monhoc;
    }

    public void setMonhoc(MonhocEntity monhoc) {
        this.monhoc = monhoc;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sinhvien", referencedColumnName = "mssv")
    public SinhvienEntity getSinhvien() {
        return sinhvien;
    }

    public void setSinhvien(SinhvienEntity sinhvien) {
        this.sinhvien = sinhvien;
    }
}
