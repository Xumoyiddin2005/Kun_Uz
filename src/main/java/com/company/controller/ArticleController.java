package com.company.controller;


import com.company.dto.article.ArticleCreateDTO;
import com.company.dto.article.ArticleDTO;
import com.company.enums.ProfileRole;
import com.company.service.ArticleService;
import com.company.util.HttpHeaderUtil;
import com.company.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/article")
@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @PostMapping("adm/create")
    public ResponseEntity<?> create(@RequestBody ArticleCreateDTO articleDTO, HttpServletRequest request) {
        Integer profileId = HttpHeaderUtil.getId(request, ProfileRole.MODERATOR);
        articleService.create(articleDTO, profileId);
        return ResponseEntity.ok().body("Successfully created");
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")String id, @RequestHeader("Authorization") String jwt,
                                    @RequestBody ArticleCreateDTO articleDTO) {
        JwtUtil.decode(jwt, ProfileRole.MODERATOR);
        articleService.update(id,articleDTO);
        return ResponseEntity.ok().body("Successfully updated");
    }


    @GetMapping("/getList")
    public ResponseEntity<?> getArticleList() {
        List<ArticleDTO> getList = articleService.getArticles();
        return ResponseEntity.ok().body(getList);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        articleService.delete(id);
        ResponseEntity<Object> build = ResponseEntity.ok().body("Successfully deleted");
        return build;
    }
}
