package nsk.cath.com.model.contact;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Deanery{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String deaneryCode;

    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DioceseId", referencedColumnName = "Id")
    private Diocese diocese;

    public Deanery(Long id,String name, String deaneryCode) {
        this.id =id;
        this.name = name;
        this.deaneryCode = deaneryCode;
    }
}
