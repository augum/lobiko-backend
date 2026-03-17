package lobiko.com.web;

import lobiko.com.dtos.SpecialiteRequestDto;
import lobiko.com.dtos.SpecialiteResponseDto;
import lobiko.com.services.SpecialiteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
@CrossOrigin("*")
public class SpecialisationController {
    private SpecialiteService service;

    @PostMapping(path = "/specialites")
    public SpecialiteResponseDto save(@RequestBody SpecialiteRequestDto requestDto){
        return  service.save(requestDto);
    }
    @PutMapping(path = "/specialites/{id}")
    public SpecialiteResponseDto update(@PathVariable Long id ,@RequestBody SpecialiteRequestDto requestDto){
        return  service.update(id,requestDto);
    }
    @GetMapping(path = "/specialites")
    public List<SpecialiteResponseDto> getAll(){
        return service.specialiteList();
    }
    @GetMapping(path = "/specialites/{id}")
    public SpecialiteResponseDto getone(@PathVariable Long id){
       return  service.getSpecialite(id) ;
    }
}
