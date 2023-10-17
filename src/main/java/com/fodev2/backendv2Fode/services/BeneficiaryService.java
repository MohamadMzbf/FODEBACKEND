package com.fodev2.backendv2Fode.services;

import com.fodev2.backendv2Fode.dto.BeneficiaryRequest;
import com.fodev2.backendv2Fode.dto.GenderRequest;
import com.fodev2.backendv2Fode.dto.ProfileRequest;
import com.fodev2.backendv2Fode.dto.UpdateBeneficiaryRequest;
import com.fodev2.backendv2Fode.models.*;
import com.fodev2.backendv2Fode.repositories.ApplicantRepository;
import com.fodev2.backendv2Fode.repositories.BeneficiaryRepository;
import com.fodev2.backendv2Fode.repositories.GenderRepository;
import com.fodev2.backendv2Fode.repositories.ProfileRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.fodev2.backendv2Fode.models.Profile;


import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Builder
public class BeneficiaryService {
    private final BeneficiaryRepository beneficiaryRepository;
    private final ApplicantRepository applicantRepository;
    private final GenderRepository genderRepository;
    private final ProfileRepository profileRepository;

    private final JdbcTemplate jdbcTemplate; // injecter...  nous permet d'utiliser le fichier sql


    public String IUF_generator(int profile, Long OrderNumber, int gender) {
        Calendar ok = Calendar.getInstance();
        int year = ok.get(Calendar.YEAR);
        int month = ok.get(Calendar.MONTH);

        String code = "";

        String formattedInput = String.format("%06d", OrderNumber);
        log.info("formattedInput est {} :" + formattedInput);

        String formattedInput2 = String.format("%02d", month + 1);


        if (profile == 1)
            code = "B";

        if (profile== 2)
            code = "M";

        if (profile== 3)
            code = "C";

        if (profile == 4)
            code = "E";

        if (profile == 5)
            code = "S";

        if (profile == 6)
            code = "P";


        String c = code + "-" + formattedInput + "-" + String.valueOf(year) +
                "-" + String.valueOf(formattedInput2) + "-" + String.valueOf(gender);
        return c;
    }

    //###################################################################

    //Appel la fonction ki permet de generer le numero d'ordre depuis DB distante (Beneficiary)
    public String callRemoteFunction() {
        String sql = "SELECT generate_numero_ordre()"; // fonction SQL depuis la Base de donnee Beneficiary
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    public void createBeneficiary(BeneficiaryRequest beneficiaryRequest)
    {
        Beneficiary beneficiary = Beneficiary.builder()
                .profile(beneficiaryRequest.getProfile())
                .firstname(beneficiaryRequest.getFirstname())
                .lastname(beneficiaryRequest.getLastname())
                .gender(beneficiaryRequest.getGender())
                .city(beneficiaryRequest.getCity())//add
                .country(beneficiaryRequest.getCountry())//add
                .email(beneficiaryRequest.getEmail())
                .phoneNumberOne(beneficiaryRequest.getPhoneNumberOne())
                .build();
//        int min = 1;
//        int max = 1000001;
//


        String ordreString = callRemoteFunction(); // Récupérer la chaîne de caractères de NumeroOrdre()
        Long ordreLong = Long.parseLong(ordreString); // Convertir la chaîne en Long

        Profile profile = beneficiary.getProfile();//on a cree une methode ToString dans Profile pour pour recuperer le profile en int au lieu de o'objet
        Gender gender = beneficiary.getGender();//on a cree une methode ToString dans Gender pour pour recuperer le gender en int au lieu de o'objet

//        String x = IUF_generator(beneficiary.getProfile(),ordreLong, beneficiary.getGender());
        String x = IUF_generator(Integer.parseInt(profile.toString()),ordreLong, Integer.parseInt(gender.toString()));//Baidy
//        String x = IUF_generator(Integer.parseInt(profile.toString()),ordreLong, beneficiary.getGender());



        // Créez un nouvel objet Applicant
        Applicant applicant = new Applicant();

        //enregistrer l'iuf generer dans iuf beneficiary
        beneficiary.setIUF(x);
        beneficiaryRepository.save(beneficiary);
        log.info("NumeroOdre est {} :" +ordreString);

        //enregistrer le meme l'iuf dans applicant
        applicant.setIUF(x);
        applicantRepository.save(applicant);
    }

    public List<BeneficiaryRequest> getAllBeneficiary()
    {
        List<Beneficiary> beneficiaries = beneficiaryRepository.findAll();
//        return beneficiaries.stream().map(this::mapToBeneficiaryRequest).toList();
        return beneficiaries.stream().map(this::mapToBeneficiaryRequest).collect(Collectors.toList());
    }

    public void updateBeneficiary(String IUF, UpdateBeneficiaryRequest updatedBeneficiaryRequest)
    {
        Optional<Beneficiary> optionalBeneficiary = Optional.ofNullable(beneficiaryRepository.findByIUF(IUF));
        if(optionalBeneficiary.isPresent()){
            Beneficiary beneficiary = optionalBeneficiary.get();

            // Mettez à jour les champs de l'entité Beneficiary à partir du DTO
            beneficiary.setFirstname(beneficiary.getFirstname());
            beneficiary.setLastname(beneficiary.getLastname());
            beneficiary.setDateBirth(updatedBeneficiaryRequest.getDateBirth());
            beneficiary.setEmail(updatedBeneficiaryRequest.getEmail());
            beneficiary.setPhoneNumberOne(updatedBeneficiaryRequest.getPhoneNumberOne());
            beneficiary.setPhoneNumberTwo(updatedBeneficiaryRequest.getPhoneNumberTwo());
            // Enregistrez la mise à jour dans le référentiel Beneficiary
            beneficiaryRepository.save(beneficiary);
        }else {
            throw new NotFoundException("Beneficiary not found with IUF: " + IUF);
        }

    }

    public List<ProfileRequest> getAllProfile(){

        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream().map(this::mapToProfileRequest).toList();
    }

    public List<GenderRequest> getAllGender(){
        List<Gender> genders = genderRepository.findAll();

        return genders.stream().map(this::mapToGnederRequest).toList();
    }

    //Mapping d'objet

    private ProfileRequest mapToProfileRequest(Profile profile){
        return ProfileRequest.builder()
                .id(profile.getId())
                .build();
    }

    private GenderRequest mapToGnederRequest(Gender gender){
        return GenderRequest.builder()
                .id(gender.getId())
                .build();
    }

    private BeneficiaryRequest mapToBeneficiaryRequest(Beneficiary beneficiary)
    {
        return BeneficiaryRequest.builder()
//                .id(beneficiary.getId())
                .IUF(beneficiary.getIUF())
                .firstname(beneficiary.getFirstname())
                .lastname(beneficiary.getLastname())
                .email(beneficiary.getEmail())
                .phoneNumberOne(beneficiary.getPhoneNumberOne())
                .dateBirth(beneficiary.getDateBirth())
//                .city(beneficiary.getCity().getId())
                .city(beneficiary.getCity())
                .email(beneficiary.getEmail())
                .phoneNumberTwo(beneficiary.getPhoneNumberTwo())
                .build();
    }

}
