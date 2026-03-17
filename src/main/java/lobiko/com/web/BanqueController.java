package lobiko.com.web;

import lobiko.com.dtos.BanqueRequestDto;
import lobiko.com.dtos.BanqueResponseDto;
import lobiko.com.dtos.HopitalResponseDto;
import lobiko.com.entities.Banque;
import lobiko.com.services.BanqueService;
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
public class BanqueController {

    private BanqueService  banqueService;


    /*
     la methode saveBanque recupère un objet de type banquerequestDto depuis la requette http
     et l'envoie au service pour le traitement
     */
    @PostMapping(path = "/banques")
    public BanqueResponseDto saveBanque(@RequestBody BanqueRequestDto requestDto) {
        System.out.println("+++++++++++++++++"+requestDto+"****************************");

        return banqueService.save(requestDto);
    }
   // debut traitement photo
    /***
     *
     *  sauvegarde ^photo
     * */
    @PostMapping(path = "/banques/{id}")
    public void saveBanquePicture(@PathVariable Long id, MultipartFile file) throws Exception {
        System.out.println("+++++++++++++++++"+"****************************");
        banqueService.modifierPhoto(id,file);
    }
    /*
    * recuperation photo
    * */
    @GetMapping(path = "/banques/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
   public byte[] getphoto(@PathVariable("id") Long id) throws  Exception{
      return  banqueService.getphoto(id);
   }
    @GetMapping(path = "/banques")
    public List<BanqueResponseDto> findAllBanque(){

        return  banqueService.listBanque();
    }
}
