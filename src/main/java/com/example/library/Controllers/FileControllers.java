package com.example.library.Controllers;

import com.example.library.Facade.FileFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class FileControllers {

    private final FileFacade fileFacade;

    @PostMapping("/file")
    public ResponseEntity<?> saveFile(@RequestParam Long id,
                                      @RequestParam MultipartFile pdf,
                                      @RequestParam MultipartFile photo) throws IOException {
        fileFacade.saveBookFile(id, photo, pdf);
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
    public void getPdf(@RequestParam Long bookId,HttpServletRequest request, HttpServletResponse response) throws IOException {
        fileFacade.getPdfByBookId(request, response, bookId);
    }

    @GetMapping("/file/photo")
    public void getPhoto(@RequestParam Long bookId,HttpServletRequest request, HttpServletResponse response) throws IOException {
        fileFacade.getMainPhotoByBookId(request, response, bookId);
    }
}
