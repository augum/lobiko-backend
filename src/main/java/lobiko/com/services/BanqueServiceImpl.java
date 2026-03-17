package lobiko.com.services;

import lobiko.com.dtos.BanqueRequestDto;
import lobiko.com.dtos.BanqueResponseDto;
import lobiko.com.entities.Banque;
import lobiko.com.mappers.BanqueMapper;
import lobiko.com.repositories.BanqueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class BanqueServiceImpl implements BanqueService {
    private BanqueMapper banqueMapper;
    private BanqueRepository BanqueRepository;
    /*
    * *la methode save permet d'enregistrer une banque de sang
    * */
    @Override
    public BanqueResponseDto save(BanqueRequestDto requestDto) {
        Banque banque = banqueMapper.fromBanqueRequestDto(requestDto);
        Banque banquesave= BanqueRepository.save(banque);
        return banqueMapper.banqueToBanqueResponseDto(banquesave);
    }
   /*
    la methode update met modifie une banque de sang existant
   * */
    @Override
    public BanqueResponseDto update(Long id, BanqueRequestDto requestDto) {
        Banque banque = banqueMapper.fromBanqueRequestDto(requestDto);
        Banque getBanque = BanqueRepository.findById(id).orElse(null);
          getBanque.setMail(banque.getNom());
          getBanque.setAdresse(banque.getAdresse());
          getBanque.setMail(getBanque.getMail());
          getBanque.setLocalisation(getBanque.getLocalisation());
          getBanque.setLatitude(banque.getLatitude());
          getBanque.setLongitude(banque.getLongitude());
          Banque updateBanque = BanqueRepository.save(getBanque);
        return banqueMapper.banqueToBanqueResponseDto(updateBanque);
    }
   // modifier le nom de l'image et enregistrer l'image dans le dossier de l'utilisateur
    @Override
    public void modifierPhoto(Long id, MultipartFile file) throws Exception {
        Banque getBanque = BanqueRepository.findById(id).orElse(null);
        getBanque.setLocalisation("banque"+id+".jpg");
        Files.write(Paths.get(System.getProperty("user.home")+"/lobiko/img/"+getBanque.getLocalisation()),file.getBytes());
        Banque updateBanque = BanqueRepository.save(getBanque);
    }

    @Override
    public byte[] getphoto(Long id) throws Exception {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/lobiko/img/"+"banque"+id+".jpg"));
    }


    /*
   * Selectionne une banque de sang à partir de son identifiant
   * */
    @Override
    public BanqueResponseDto getBanque(Long id) {
        Banque banque = BanqueRepository.findById(id).orElse(null);
        return banqueMapper.banqueToBanqueResponseDto(banque);
    }
   /*
   retourne une liste des banques de sang
    */
    @Override
    public List<BanqueResponseDto> listBanque() {
        List<Banque> banques = BanqueRepository.findAll();
        return banques.stream()
                .map(banque -> banqueMapper.banqueToBanqueResponseDto(banque))
                .collect(Collectors.toList());
    }
}
