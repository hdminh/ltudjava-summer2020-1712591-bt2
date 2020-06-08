package hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "bangdiem", schema = "qlsv", catalog = "")
public class BangdiemEntity {
    private int mabang;
    private Double diemgk;
    private Double diemck;
    private Double diemkhac;
    private Double diemtong;
    private Byte dau;

    @Id
    @Column(name = "mabang")
    public int getMabang() {
        return mabang;
    }

    public void setMabang(int mabang) {
        this.mabang = mabang;
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

        if (mabang != that.mabang) return false;
        if (diemgk != null ? !diemgk.equals(that.diemgk) : that.diemgk != null) return false;
        if (diemck != null ? !diemck.equals(that.diemck) : that.diemck != null) return false;
        if (diemkhac != null ? !diemkhac.equals(that.diemkhac) : that.diemkhac != null) return false;
        if (diemtong != null ? !diemtong.equals(that.diemtong) : that.diemtong != null) return false;
        if (dau != null ? !dau.equals(that.dau) : that.dau != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mabang;
        result = 31 * result + (diemgk != null ? diemgk.hashCode() : 0);
        result = 31 * result + (diemck != null ? diemck.hashCode() : 0);
        result = 31 * result + (diemkhac != null ? diemkhac.hashCode() : 0);
        result = 31 * result + (diemtong != null ? diemtong.hashCode() : 0);
        result = 31 * result + (dau != null ? dau.hashCode() : 0);
        return result;
    }
}
