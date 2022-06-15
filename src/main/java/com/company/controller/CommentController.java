package com.company.controller;


import com.company.dto.comment.CommentCreateDTO;
import com.company.dto.comment.CommentDTO;
import com.company.enums.ProfileRole;
import com.company.service.CommentService;
import com.company.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/comment")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CommentCreateDTO articleDTO,
                                    @RequestHeader("Authorization") String jwt) {
        Integer profileId = JwtUtil.decode(jwt, ProfileRole.USER);
        commentService.create(articleDTO, profileId);
        return ResponseEntity.ok().body("Successfully created");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")Integer id , @RequestBody CommentDTO commentDTO) {
        commentService.update(id,commentDTO);
        return ResponseEntity.ok().body("Successfully updated");
    }


    @GetMapping("/getList")
    public ResponseEntity<?> getArticleList() {
        List<CommentDTO> list = commentService.getList();
        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        commentService.delete(id);
        ResponseEntity<Object> build = ResponseEntity.ok().body("Successfully deleted");
        return build;
    }
}
