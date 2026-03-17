package lobiko.com.web;

import lobiko.com.dtos.MedecinRequestDto;
import lobiko.com.dtos.MedecinResponseDto;
import lobiko.com.entities.Medecin;
import lobiko.com.services.MedecinService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
@CrossOrigin("*")
public class MedecinController {
    private MedecinService medecinService;

    @PostMapping(path = "/medecins")
    public MedecinResponseDto save(@RequestBody MedecinRequestDto medecinRequestDto) {
        return  medecinService.saveMedecin(medecinRequestDto);
    }
    /*/
     Modifie la photo du medecin
    */

    @PostMapping(path="/medecins/{id}")
    public void savephoto(@PathVariable("id") Long id, MultipartFile file) throws Exception{
        medecinService.modifierPhoto(id,file);
    }
    /*
    /Retourne la photo du medecin par son ID
     */
    @GetMapping(path = "/medecinsphoto/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
        return medecinService.getPhoto(id);
    }
    @GetMapping(path = "/medecins")
    public List<MedecinResponseDto> findAll() {
        return medecinService.getAllMedecin();
    }
    @GetMapping(path = "/medecins/{id}")
    public List<MedecinResponseDto >findById(@PathVariable Long id) {
        return medecinService.getAllMedecinByid(id);
    }
}
