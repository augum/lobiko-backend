package lobiko.com.web;

import lobiko.com.dtos.HopitalSpecialiteRequestDto;
import lobiko.com.dtos.HopitalSpecialiteResponseDto;
import lobiko.com.services.HopitalSpecialiteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
@CrossOrigin("*")
public class HopitalSpecialiteController {
    // Appel du service
    private HopitalSpecialiteService service;
    /*
     Sauvegarde d'un objet hopitalSpecialité
     */
    @PostMapping(path = "/hopitalspecialites")
    public HopitalSpecialiteResponseDto save(@RequestBody HopitalSpecialiteRequestDto requestDto){
        return service.save(requestDto);
    }
    @GetMapping(path = "/hopitalspecialites")
    public List<HopitalSpecialiteResponseDto> getAll(){
        return  service.listHopitalSpecialite();
    }

    @GetMapping(path = "/hopitalspecialites/{id}")
    public List<HopitalSpecialiteResponseDto> getAllByspecialite(@PathVariable Long id) {
        return  service.listHopitalSpecialite(id);
    }
}
