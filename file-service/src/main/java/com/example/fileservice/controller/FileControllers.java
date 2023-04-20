package com.example.fileservice.controller;

import com.example.fileservice.facade.FileFacade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class FileControllers {

    private final FileFacade fileFacade;

    @PostMapping("/file")
    public ResponseEntity<?> saveFile(@RequestParam Long id,
                                      @RequestParam(required = false) MultipartFile pdf,
                                      @RequestParam(required = false) MultipartFile photo) throws IOException {
        fileFacade.saveBookFile(id, photo, pdf);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/author/file")
    public ResponseEntity<?> saveAuthorFile(@RequestParam Long id,
                                      @RequestParam MultipartFile author) throws IOException {
        fileFacade.saveAuthorFile(id, author);
        return ResponseEntity.status(201).build();
    }

    @PatchMapping("/file")
    public ResponseEntity<?> updateFile(@RequestParam Long id,
                                      @RequestParam(required = false) MultipartFile pdf,
                                      @RequestParam(required = false) MultipartFile photo) throws IOException {
        fileFacade.updateBookFiles(id, photo, pdf);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/file/pdf")
    public void getPdf(@RequestParam Long bookId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        fileFacade.getPdfByBookId(request, response, bookId);
    }

    @GetMapping("/file/photo")
    public void getPhoto(@RequestParam Long bookId,HttpServletRequest request, HttpServletResponse response) throws IOException {
        fileFacade.getMainPhotoByBookId(request, response, bookId);
    }

    @GetMapping("/author/file/photo")
    public void getAuthorPhoto(@RequestParam Long authorId,HttpServletRequest request, HttpServletResponse response) throws IOException {
        fileFacade.getPhotoByAuthorId(request, response, authorId);
    }
}
