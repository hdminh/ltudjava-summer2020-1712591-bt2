package hibernate.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "monhoc", schema = "qlsv", catalog = "")
public class MonhocEntity {
    private String mamon;
    private String tenmonhoc;
    private String phonghoc;
    private LophocEntity lophoc;

    public MonhocEntity getMonhocById(String monhoc) {
        return this;
    }

    @Id
    @Column(name = "mamon")
    public String getMamon() {
        return mamon;
    }

    public void setMamon(String mamon) {
        this.mamon = mamon;
    }

    @Basic
    @Column(name = "tenmonhoc")
    public String getTenmonhoc() {
        return tenmonhoc;
    }

    public void setTenmonhoc(String tenmonhoc) {
        this.tenmonhoc = tenmonhoc;
    }

    @Basic
    @Column(name = "phonghoc")
    public String getPhonghoc() {
        return phonghoc;
    }

    public void setPhonghoc(String phonghoc) {
        this.phonghoc = phonghoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonhocEntity that = (MonhocEntity) o;

        if (mamon != null ? !mamon.equals(that.mamon) : that.mamon != null) return false;
        if (tenmonhoc != null ? !tenmonhoc.equals(that.tenmonhoc) : that.tenmonhoc != null) return false;
        if (phonghoc != null ? !phonghoc.equals(that.phonghoc) : that.phonghoc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mamon != null ? mamon.hashCode() : 0;
        result = 31 * result + (tenmonhoc != null ? tenmonhoc.hashCode() : 0);
        result = 31 * result + (phonghoc != null ? phonghoc.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lophoc", referencedColumnName = "malop")
    public LophocEntity getLophoc() {
        return lophoc;
    }

    public void setLophoc(LophocEntity lophoc) {
        this.lophoc = lophoc;
    }
}
