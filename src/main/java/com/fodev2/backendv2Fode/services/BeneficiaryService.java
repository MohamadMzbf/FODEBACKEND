package com.fodev2.backendv2Fode.services;

import com.fodev2.backendv2Fode.dto.BeneficiaryRequest;
import com.fodev2.backendv2Fode.dto.UpdateBeneficiaryRequest;
import com.fodev2.backendv2Fode.models.Beneficiary;
import com.fodev2.backendv2Fode.repositories.BeneficiaryRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Builder
public class BeneficiaryService {
    private final BeneficiaryRepository beneficiaryRepository;
    private final JdbcTemplate jdbcTemplate; // injecter...  nous permet d'utiliser le fichier sql

    //    public String IUF_generator(Long profile, Long id, Long gender) {
    public String IUF_generator(Long profile, Long OrderNumber, Long gender) {
//        public String IUF_generator(Long profile,String SQL, Long gender) {

        Calendar ok = Calendar.getInstance();
        int year = ok.get(Calendar.YEAR);
        int month = ok.get(Calendar.MONTH);

        String code = "";

//        String formattedInput = String.format("%07d", id);
        String formattedInput = String.format("%06d", OrderNumber);
        log.info("formattedInput est {} :" +formattedInput);


        String formattedInput2 = String.format("%02d", month+1);

        if (profile == Long.valueOf(1))
            code = "B";

        if (profile == Long.valueOf(2))
            code = "M";

        if (profile == Long.valueOf(3))
            code = "C";

        if (profile == Long.valueOf(4))
            code = "E";

        if (profile == Long.valueOf(5))
            code = "S";

        if (profile == Long.valueOf(6))
            code = "P";

//        String c = code + "-" + formattedInput + "-" + String.valueOf(year) + "-" + String.valueOf(formattedInput2) + "-" + String.valueOf(gender);
//        String c = code + "-" + formattedInput + "-"+ String.valueOf(year) + "-" +String.valueOf(formattedInput2) +"-"+ String.valueOf(gender);
        String c = code + "-" + formattedInput + "-" + String.valueOf(year) + "-" + String.valueOf(formattedInput2) + "-" + String.valueOf(gender);

        return c;
    }

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
                .email(beneficiaryRequest.getEmail())
                .phoneNumberOne(beneficiaryRequest.getPhoneNumberOne())
                .build();
//        int min = 1;
//        int max = 1000001;
//
//        int randomNumber = ThreadLocalRandom.current().nextInt(min, max);
//        String x = IUF_generator(beneficiary.getProfile(), (long) randomNumber, beneficiary.getGender());

        String ordreString = callRemoteFunction(); // Récupérer la chaîne de caractères de NumeroOrdre()
        Long ordreLong = Long.parseLong(ordreString); // Convertir la chaîne en Long

        String x = IUF_generator(beneficiary.getProfile(),ordreLong, beneficiary.getGender());
        beneficiary.setIUF(x);
        beneficiaryRepository.save(beneficiary);
        log.info("NumeroOdre est {} :" +ordreString);
    }

    public List<BeneficiaryRequest> getAllBeneficiary()
    {
        List<Beneficiary> beneficiaries = beneficiaryRepository.findAll();
        return beneficiaries.stream().map(this::mapToBeneficiaryRequest).toList();
    }

    public void updateBeneficiary(int beneficiaryId, UpdateBeneficiaryRequest updatedBeneficiaryRequest)
    {
        Beneficiary existingBeneficiary = beneficiaryRepository
                .findById((long) beneficiaryId)
                .orElseThrow(() -> new NotFoundException("Beneficiary not found with ID: " + beneficiaryId));

        // Copy updated fields from updatedBeneficiaryRequest to existingBeneficiary
        BeanUtils.copyProperties(updatedBeneficiaryRequest, existingBeneficiary);

        beneficiaryRepository.save(existingBeneficiary);
    }

    private BeneficiaryRequest mapToBeneficiaryRequest(Beneficiary beneficiary)
    {
        return BeneficiaryRequest.builder()
                .id(beneficiary.getId())
                .IUF(beneficiary.getIUF())
                .firstname(beneficiary.getFirstname())
                .lastname(beneficiary.getLastname())
                .email(beneficiary.getEmail())
                .phoneNumberOne(beneficiary.getPhoneNumberOne())
                .dateBirth(beneficiary.getDateBirth())
                .city(beneficiary.getCity())
                .email(beneficiary.getEmail())
                .phoneNumberTwo(beneficiary.getPhoneNumberTwo())
                .build();
    }
}
