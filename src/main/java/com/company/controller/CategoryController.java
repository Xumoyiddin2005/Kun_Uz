package com.company.controller;

import com.company.dto.CategoryDTO;
import com.company.dto.JwtDTO;
import com.company.enums.Language;
import com.company.enums.ProfileRole;
import com.company.service.CategoryService;
import com.company.util.HttpHeaderUtil;
import com.company.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    // PUBLIC
    @GetMapping("getList")
    public ResponseEntity<List<CategoryDTO>> getListCategory(@RequestHeader(value = "Accept-Language", defaultValue = "uz")
                                                             Language language) {
        List<CategoryDTO> list = categoryService.getList(language);
        return ResponseEntity.ok().body(list);
    }

    // SECURED
    @PostMapping("/adm/create")
    public ResponseEntity<?> create(@RequestBody CategoryDTO categoryDto,
                                    HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        categoryService.create(categoryDto);
        return ResponseEntity.ok().body("Successfully created");
    }

    @GetMapping("/adm/admin")
    public ResponseEntity<List<CategoryDTO>> getList(HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        List<CategoryDTO> list = categoryService.getListOnlyForAdmin();
        return ResponseEntity.ok().body(list);
    }


    @PutMapping("/adm/update/{id}")
    private ResponseEntity<?> update(@PathVariable("id") Integer id,
                                     @RequestBody CategoryDTO dto,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        categoryService.update(id, dto);
        return ResponseEntity.ok().body("Succsessfully updated");
    }

    @DeleteMapping("/adm/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        categoryService.delete(id);
        return ResponseEntity.ok().body("Sucsessfully deleted");
    }

    @GetMapping("/pagination")
    public ResponseEntity<PageImpl> pagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                               @RequestParam(value = "size", defaultValue = "2") int size) {
        PageImpl response = categoryService.pagination(page, size);
        return ResponseEntity.ok().body(response);
    }


}
