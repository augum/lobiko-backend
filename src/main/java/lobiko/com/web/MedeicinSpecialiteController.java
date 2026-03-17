package lobiko.com.web;

import lobiko.com.dtos.HopitalSpecialiteRequestDto;
import lobiko.com.dtos.HopitalSpecialiteResponseDto;
import lobiko.com.dtos.MedecinSpecialiteRequestDto;
import lobiko.com.dtos.MedecinSpecialiteResponseDto;
import lobiko.com.services.HopitalSpecialiteService;
import lobiko.com.services.MedecinSpecialiteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
@CrossOrigin("*")
public class MedeicinSpecialiteController {
    // Appel du service
    private MedecinSpecialiteService service;
    /*
     Sauvegarde d'un objet hopitalSpecialité
     */
    @PostMapping(path = "/medecinspecialites")
    public MedecinSpecialiteResponseDto save(@RequestBody MedecinSpecialiteRequestDto requestDto){
        return service.save(requestDto);
    }
    @GetMapping(path = "/medecinspecialites")
    public List<MedecinSpecialiteResponseDto> getAll(){
        return  service.listMedecinSpecialite();
    }

    @GetMapping(path = "/medecinspecialites/{id}")
    public List<MedecinSpecialiteResponseDto> getAllByspecialite(@PathVariable Long id) {
        return  service.listMedecinSpecialite(id);
    }
}
