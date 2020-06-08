package hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "sinhvienhocmon", schema = "qlsv", catalog = "")
public class SinhvienhocmonEntity {
    private int id;
    private Integer bangdiem;
    private MonhocEntity monhoc;
    private SinhvienEntity sinhvien;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bangdiem")
    public Integer getBangdiem() {
        return bangdiem;
    }

    public void setBangdiem(Integer bangdiem) {
        this.bangdiem = bangdiem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SinhvienhocmonEntity that = (SinhvienhocmonEntity) o;

        if (id != that.id) return false;
        if (bangdiem != null ? !bangdiem.equals(that.bangdiem) : that.bangdiem != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (bangdiem != null ? bangdiem.hashCode() : 0);
        return result;
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
