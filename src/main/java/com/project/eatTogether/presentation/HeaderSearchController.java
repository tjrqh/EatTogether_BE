package com.project.eatTogether.presentation;

import com.project.eatTogether.application.dto.HeaderSearchDTO;
import com.project.eatTogether.application.service.HeaderSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class HeaderSearchController {

    @Autowired
    private HeaderSearchService headerSearchService;

    @GetMapping
    public List<HeaderSearchDTO> search(@RequestParam long rsId, @RequestParam String question) {
        return headerSearchService.search(rsId, question);
    }
}
