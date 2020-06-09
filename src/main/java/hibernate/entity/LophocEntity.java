package hibernate.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lophoc", schema = "qlsv", catalog = "")
public class LophocEntity {
    @Id
    private String malop;

    public LophocEntity(){

    }

    public LophocEntity(String lop) {
        malop = lop;
    }


    @Column(name = "malop")
    public String getMalop() {
        return malop;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }

    @OneToMany(targetEntity =SinhvienEntity.class, mappedBy = "lophoc", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    List<SinhvienEntity> sinhvienList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LophocEntity that = (LophocEntity) o;

        if (malop != null ? !malop.equals(that.malop) : that.malop != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return malop != null ? malop.hashCode() : 0;
    }
}
