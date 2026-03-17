package lobiko.com.web;

import com.fasterxml.jackson.annotation.JsonRootName;
import lobiko.com.dtos.HopitalRequestDto;
import lobiko.com.dtos.HopitalResponseDto;
import lobiko.com.services.HopitalService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
@CrossOrigin("*")
public class HopitalController {

    private HopitalService service;
    @PostMapping(path = "/hopitals")
    public HopitalResponseDto saveHopital(@RequestBody HopitalRequestDto requestDto){
        return service.save(requestDto);
    }
    @PutMapping(path = "/hopitals/{id}")
    public HopitalResponseDto updateHopital(@PathVariable Long id, @RequestBody HopitalRequestDto requestDto){

        return service.update(id,requestDto);
    }
    @GetMapping(path = "/hopitals")
     public List<HopitalResponseDto> findAllHopi(){

        return  service.listHopital();
     }

     @GetMapping(path = "/hopitals/{id}")
     public  HopitalResponseDto getHopital(@PathVariable Long id){
        return service.getHopital(id);
     }
     /*/
     traitement image
      */

    @PostMapping(path = "/hopitals/{id}")
    public void saveHopitalPicture(@PathVariable Long id, MultipartFile file) throws Exception {
        System.out.println("+++++++++++++++++"+"****************************");
        service.modifierPhoto(id,file);
    }
    /*
     * recuperation photo
     * */
    @GetMapping(path = "/hopitalsphoto/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getphoto(@PathVariable("id") Long id) throws  Exception{
        return  service.getphoto(id);
    }
}
