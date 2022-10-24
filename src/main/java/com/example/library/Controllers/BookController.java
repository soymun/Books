package com.example.library.Controllers;

import com.example.library.Dto.Book.BookDto;
import com.example.library.Dto.Book.BookDtoSave;
import com.example.library.Dto.Response.FactoryResponse.FactoryResponse;
import com.example.library.Facade.BookFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;

@RestController
@RequestMapping("/sibrary")
@Slf4j
public class BookController {

    private final BookFacade bookFacade;

    private final FactoryResponse factoryResponse;

    public BookController(BookFacade bookFacade, FactoryResponse factoryResponse) {
        this.bookFacade = bookFacade;
        this.factoryResponse = factoryResponse;
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<?> getBook(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) throws IOException {
        log.info("Get file with id");
        BookDto bookDto = bookFacade.getBook(id);
        String url = "D:\\Users\\rcfef\\Library\\src\\main\\resources\\books\\";
        File file = new File(url + bookDto.getUrlToPdf());
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        response.setContentType(mimeType);

        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

        response.setContentLength((int) file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        FileCopyUtils.copy(inputStream, response.getOutputStream());

        return ResponseEntity.ok(factoryResponse.getResponse(bookDto));
    }

    @PostMapping("/book")
    public ResponseEntity<?> saveBook(@RequestBody BookDtoSave bookDtoSave, MultipartFile multipartFile) throws IOException {
        log.info("Save book with bookdtosave and multipart");
        return bookFacade.saveBook(bookDtoSave, multipartFile);
    }

    @PutMapping("/book")
    public ResponseEntity<?> updateBook(@RequestBody BookDto bookDto){
        log.info("Update book in controller");
        return bookFacade.updateBook(bookDto);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        log.info("Delete book in controller");
        return bookFacade.deleteBook(id);
    }
}
