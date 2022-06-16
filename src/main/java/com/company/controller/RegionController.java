package com.company.controller;

import com.company.dto.RegionDto;
import com.company.enums.ProfileRole;
import com.company.service.RegionService;
import com.company.util.HttpHeaderUtil;
import com.company.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/region")
@RestController
public class RegionController {
    @Autowired
    private RegionService regionService;

    // PUBLIC
    @GetMapping("getList")
    public ResponseEntity<List<RegionDto>> getListRegion() {
        List<RegionDto> list = regionService.getList();
        return ResponseEntity.ok().body(list);
    }

    // SECURE
    @PostMapping("/adm/create")
    public ResponseEntity<?> create(@RequestBody RegionDto regionDto, HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        regionService.create(regionDto);
        return ResponseEntity.ok().body("Successfully created");
    }

    @GetMapping("/adm/admin")
    public ResponseEntity<List<RegionDto>> getlist(HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        List<RegionDto> list = regionService.getListOnlyForAdmin();
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/adm/update/{id}")
    private ResponseEntity<?> update(@PathVariable("id") Integer id,
                                     @RequestBody RegionDto dto,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        regionService.update(id, dto);
        return ResponseEntity.ok().body("Succsessfully updated");
    }

    @DeleteMapping("/adm/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        regionService.delete(id);
        return ResponseEntity.ok().body("Successfully deleted");
    }


}
