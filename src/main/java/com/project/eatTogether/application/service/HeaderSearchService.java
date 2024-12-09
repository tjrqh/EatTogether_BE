package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.HeaderSearchDTO;
import com.project.eatTogether.infrastructure.HeaderSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HeaderSearchService {

    @Autowired
    private HeaderSearchRepository headerSearchRepository;

    public List<HeaderSearchDTO> search(long rsId, String question) {
        System.out.println(rsId+ " " + question);
        List<Object[]> objects =  headerSearchRepository.custom(rsId, question);
        List<HeaderSearchDTO> headerSearchDTOList = new ArrayList<>();
        for(Object[] obj : objects){
            HeaderSearchDTO headerSearchDTO = new HeaderSearchDTO(obj);
            headerSearchDTOList.add(headerSearchDTO);
        }
        return headerSearchDTOList;
    }
}
