package lobiko.com.dtos;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@JsonRootName(value = "items")
public class MedecinSpecialiteRequestDto {
    private Long id;
    private Long idMedecin;
    private Long idSpecialite;
}
