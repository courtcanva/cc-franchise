package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListGetDto;
import com.courtcanva.ccfranchise.mappers.SuburbMapper;
import com.courtcanva.ccfranchise.models.Suburb;
import com.courtcanva.ccfranchise.repositories.SuburbRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SuburbService {

    private final SuburbRepository suburbRepository;

    private final SuburbMapper suburbMapper;

    public SuburbListGetDto findAllSuburbs(){

        List<Suburb> allSuburbs = suburbRepository.findAll();

        return SuburbListGetDto.builder().suburbs(allSuburbs.stream().map(suburbMapper::suburbToGetDto).collect(Collectors.toList())).build();
    }


    public List<Suburb> findSuburbBySscCodes(List<Long> sscCodes) {

        return suburbRepository.findBySscCodeIn(sscCodes);

    }

    public Optional<Suburb> findAllByPostcode(int postcode){
        return suburbRepository.findAllByPostcode(postcode);
    }


}
