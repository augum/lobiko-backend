package lobiko.com.services;

import lobiko.com.dtos.HopitalRequestDto;
import lobiko.com.dtos.HopitalResponseDto;
import lobiko.com.entities.Banque;
import lobiko.com.entities.Hopital;
import lobiko.com.mappers.HopitalMapper;
import lobiko.com.repositories.HopitalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class HopitalServiceImpl implements HopitalService {
    private HopitalRepository repository;
    private HopitalMapper mapper ;
    @Override
    public HopitalResponseDto save(HopitalRequestDto requestDto) {
        /*Méthode pour l'enregistrement de l'hopital*/
        Hopital hopital = mapper.fromHopitalRequestDto(requestDto);
        Hopital saveHopital = repository.save(hopital);
        HopitalResponseDto hopitalResponseDto = mapper.hopitalToHopitalResponseDto(saveHopital);
        return hopitalResponseDto;
    }

    @Override
    public HopitalResponseDto update(Long id, HopitalRequestDto requestDto) {
        Hopital hopital = mapper.fromHopitalRequestDto(requestDto);
        Hopital getHopital = repository.findById(id).get();
         getHopital.setAdresse(hopital.getAdresse());
         getHopital.setMail(hopital.getMail());
         getHopital.setNom(hopital.getNom());
         getHopital.setTel(hopital.getTel());
       Hopital updateHopital = repository.save(getHopital);
        return mapper.hopitalToHopitalResponseDto(updateHopital);
    }

    @Override
    public HopitalResponseDto getHopital(Long id) {
        Hopital hopital = repository.findById(id).get();
        return mapper.hopitalToHopitalResponseDto(hopital);
    }

    @Override
    public List<HopitalResponseDto> listHopital() {
        List<Hopital> hopitalList = repository.findAll();
        return hopitalList.stream()
                .map(hopital -> mapper.hopitalToHopitalResponseDto(hopital))
                .collect(Collectors.toList());
    }
/*
 Enregistrement et modification de l'image
 */
    @Override
    public void modifierPhoto(Long id, MultipartFile file) throws Exception {
        Hopital getHopital = repository.findById(id).orElse(null);
        getHopital.setLocalisation("hopital"+id+".jpg");
        Files.write(Paths.get(System.getProperty("user.home")+"/lobiko/img/"+getHopital.getLocalisation()),file.getBytes());
        Hopital updateBanque = repository.save(getHopital);
    }

    @Override
    public byte[] getphoto(Long id) throws Exception {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/lobiko/img/"+"hopital"+id+".jpg"));
    }
}
