package co.copper.test.datamodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "copper_person")
public class CopperPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String first;
    private String last;
    private String password;
    private String email;

}
