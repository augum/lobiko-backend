package lobiko.com.services;

import lobiko.com.dtos.MedecinRequestDto;
import lobiko.com.dtos.MedecinResponseDto;
import lobiko.com.entities.Medecin;
import lobiko.com.entities.Specialite;
import lobiko.com.mappers.MedecinMapper;
import lobiko.com.repositories.MedecinRepository;
import lobiko.com.repositories.SpecialiteRepository;
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
public class MedecinServiceImpl implements MedecinService {
    private MedecinRepository medecinRepository;
    private SpecialiteRepository specialiteRepository;
    private MedecinMapper medecinMapper;
    @Override
    public MedecinResponseDto saveMedecin(MedecinRequestDto medecinRequestDto) {
        Medecin medecin = medecinMapper.fromMedecinRequestDto(medecinRequestDto);
        Medecin saveMedecin = medecinRepository.save(medecin);
        MedecinResponseDto ResponseDto = medecinMapper.medecinToMedecinResponseDto(saveMedecin);
        return ResponseDto;

    }

    @Override
    public List<MedecinResponseDto> getAllMedecinByid(Long idSpecialite) {
        List<Medecin > list = medecinRepository.listeMedecin(idSpecialite);

        for(Medecin medecin : list){
            Specialite specialisation = specialiteRepository.findById(medecin.getId()).get();
            medecin.setSpecialisation(specialisation);
        }
        return list
                .stream()
                .map(medecin->medecinMapper.medecinToMedecinResponseDto(medecin) )
                .collect(Collectors.toList());
    }
    @Override
    public List<MedecinResponseDto> getAllMedecin() {
        List<Medecin > list = medecinRepository.findAll();
        /*for(Medecin medecin : list){
            Specialite specialisation = specialiteRepository.findById(medecin.getId()).get();
            medecin.setSpecialisation(specialisation);
        }*/
        return list
                .stream()
                .map(medecin->medecinMapper.medecinToMedecinResponseDto(medecin) )
                .collect(Collectors.toList());
    }
// retourne l'image enregistrée à partir de l'ID
    @Override
    public byte[] getPhoto(Long id) throws Exception {

        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/lobiko/img/"+"medecin"+id+".jpeg"));
    }
//modifie le medecin en ajoutant la photo à partir de son ID
    @Override
    public void modifierPhoto(Long id, MultipartFile file) throws Exception {
        Medecin medecin = medecinRepository.findById(id).orElse(null);
        medecin.setPhoto("medecin"+id+".jpeg");
        Files.write(Paths.get(System.getProperty("user.home")+"/lobiko/img/"+medecin.getPhoto()),file.getBytes());
        medecinRepository.save(medecin);
    }
}
