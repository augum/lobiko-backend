package lobiko.com.dtos;


import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @JsonRootName(value = "medecin")
public class MedecinRequestDto {
    private Long id;
    private String nom;
    private String prenom;
    private String hopital;
    private String photo;
    private String cnom;
    private String tel;
    private String specialite;
    private String description;
    private Long idSpecialite;

}
