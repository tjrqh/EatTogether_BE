package com.project.eatTogether.presentation;

import com.project.eatTogether.application.dto.RsMenusDTO;
import com.project.eatTogether.application.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/queue/menu")  // 기본 경로 설정
public class MenuController {

    @Autowired
    private MenuService menuService;  // 서비스 클래스 주입

    @GetMapping("/details/{menuId}")
    public RsMenusDTO getMenuDetails(@PathVariable("menuId") Long menuId) {

        return menuService.getMenuDetails(menuId);  // 메뉴 정보를 반환
    }
}
