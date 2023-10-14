package com.fodev2.backendv2Fode.controllers;

import com.fodev2.backendv2Fode.dto.BeneficiaryRequest;
import com.fodev2.backendv2Fode.dto.UpdateBeneficiaryRequest;
import com.fodev2.backendv2Fode.services.BeneficiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/fodev2/api/beneficiary")
@RequiredArgsConstructor
public class BeneficiaryController {
    private final BeneficiaryService beneficiaryService;
    @PostMapping(path = "/createBeneficiary")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBeneficiary(@RequestBody BeneficiaryRequest beneficiaryRequest)
    {
        beneficiaryService.createBeneficiary(beneficiaryRequest);
    }

    @PutMapping("updateBeneficiary/{beneficiaryId}")
    public ResponseEntity<String> updateBeneficiary(
            @PathVariable Integer beneficiaryId,
            @RequestBody UpdateBeneficiaryRequest updateBeneficiaryRequest)
    {
        beneficiaryService.updateBeneficiary(beneficiaryId, updateBeneficiaryRequest);
        return new ResponseEntity<>("Beneficiary updated successfully", HttpStatus.OK);
    }

    @GetMapping(path = "/listBeneficiary")
    @ResponseStatus(HttpStatus.OK)
    public List<BeneficiaryRequest> getAllBeneficiary()
    {
        return beneficiaryService.getAllBeneficiary();
    }

}
