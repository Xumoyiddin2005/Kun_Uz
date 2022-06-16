package com.company.controller;

import com.company.dto.ProfileDTO;
import com.company.enums.ProfileRole;
import com.company.service.ProfileService;
import com.company.util.HttpHeaderUtil;
import com.company.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/profile")
@RestController
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody ProfileDTO profileDto,
                                    HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        profileService.create(profileDto);
        return ResponseEntity.ok().body("Successfully created");
    }

    @GetMapping("auth")
    public ResponseEntity<List<ProfileDTO>> getProfileList(HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        List<ProfileDTO> list = profileService.getList();
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/detail")
    public ResponseEntity<?> update(@RequestBody ProfileDTO dto,
                                    HttpServletRequest request) {
        Integer profileId = HttpHeaderUtil.getId(request);
        profileService.update(profileId, dto);
        return ResponseEntity.ok().body("Successfully updated");
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody ProfileDTO dto, HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        profileService.update(id, dto);
        return ResponseEntity.ok().body("Succsessfully updated");
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Integer id, HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        profileService.delete(id);
        return ResponseEntity.ok().body("Successfully deleted");
    }

    @GetMapping("/pagination")
    public ResponseEntity<PageImpl> pagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                               @RequestParam(value = "size", defaultValue = "2") int size) {
        PageImpl response = profileService.pagination(page, size);
        return ResponseEntity.ok().body(response);
    }


}
