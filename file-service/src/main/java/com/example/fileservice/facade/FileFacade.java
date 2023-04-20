package com.example.fileservice.facade;

import com.example.fileservice.filters.SecurityTokenContext;
import com.example.fileservice.model.author.AuthorDto;
import com.example.fileservice.model.author.AuthorUpdateDto;
import com.example.fileservice.model.book.BookDto;
import com.example.fileservice.model.book.BookUpdateDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLConnection;

@Component
@Slf4j
@RequiredArgsConstructor
public class FileFacade {

    @Value("${content.url}")
    private String url;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SecurityTokenContext securityTokenContext;

    public void saveBookFile(Long id, MultipartFile photo, MultipartFile pdf) throws IOException {

        BookUpdateDto bookUpdateDto = new BookUpdateDto();

        File filePdf = new File(url + pdf.getOriginalFilename());
        if (filePdf.createNewFile()) {
            byte[] bytes = pdf.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(filePdf));
            stream.write(bytes);
            stream.close();
            bookUpdateDto.setUrlToPdfFile(pdf.getOriginalFilename());
        }

        File filePhoto = new File(url + photo.getOriginalFilename());
        if (filePhoto.createNewFile()) {
            byte[] bytes = photo.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(filePdf));
            stream.write(bytes);
            stream.close();
            bookUpdateDto.setUrlToMainPhoto(photo.getOriginalFilename());
        }
        bookUpdateDto.setId(id);

        restTemplate.put("http://localhost:8072/book/v1/book",  getHeaders(bookUpdateDto));
    }

    public void saveAuthorFile(Long authorId, MultipartFile author) throws IOException {
        AuthorUpdateDto authorUpdateDto = new AuthorUpdateDto();
        authorUpdateDto.setId(authorId);

        File fileAuthor = new File(url + author.getOriginalFilename());
        if (fileAuthor.createNewFile()) {
            byte[] bytes = author.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileAuthor));
            stream.write(bytes);
            stream.close();
            authorUpdateDto.setUrlToPhoto(author.getOriginalFilename());
        }

        restTemplate.put("http://localhost:8072/author/v1/author",  getHeaders(authorUpdateDto));
    }

    public void updateBookFiles(Long id, MultipartFile photo, MultipartFile pdf) throws IOException {
        BookDto bookDto = restTemplate.exchange("http://localhost:8072/book/v1/book/{id}", HttpMethod.GET, getHeaders(), BookDto.class, id).getBody();
        if(bookDto != null) {
            updateBook(pdf, bookDto.getUrlToPdfFile());
            updateBook(photo, bookDto.getUrlToMainPhoto());
        }
    }

    public void getPdfByBookId(HttpServletRequest request, HttpServletResponse response, Long bookId) throws IOException {
        BookDto bookDto = restTemplate.exchange("http://localhost:8072/book/v1/book/{bookId}", HttpMethod.GET, getHeaders(), BookDto.class, bookId).getBody();
        if(bookDto != null) {
            File file = new File(url + bookDto.getUrlToPdfFile());
            setFile(file, response);
        }
    }

    public void getPhotoByAuthorId(HttpServletRequest request, HttpServletResponse response, Long authorId) throws IOException {
        AuthorDto authorDto = restTemplate.exchange("http://localhost:8072/book/v1/book/{authorId}", HttpMethod.GET, getHeaders(), AuthorDto.class, authorId).getBody();
        if(authorDto != null) {
            File file = new File(url + authorDto.getUrlToPhoto());
            setFile(file, response);
        }
    }

    public void getMainPhotoByBookId(HttpServletRequest request, HttpServletResponse response,Long id) throws IOException {
        BookDto bookDto = restTemplate.exchange("http://localhost:8072/book/v1/book/{id}", HttpMethod.GET, getHeaders(), BookDto.class, id).getBody();
        if(bookDto != null) {
            File file = new File(url + bookDto.getUrlToMainPhoto());
            setFile(file, response);
        }
    }

    private void setFile(File file, HttpServletResponse response) throws IOException {
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        response.setContentType(mimeType);

        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

        response.setContentLength((int) file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }

    private void updateBook(MultipartFile file, String urlToFile) throws IOException {
        if (file != null) {
            File filePdf = new File(urlToFile);
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(filePdf));
            stream.write(bytes);
            stream.close();
        }
    }

    private HttpEntity<String> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", securityTokenContext.getToken());

        return new HttpEntity<>(headers);
    }


    private HttpEntity<String> getHeaders(BookUpdateDto bookUpdateDto) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", securityTokenContext.getToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper mapper = new ObjectMapper();

        return new HttpEntity<>(mapper.writeValueAsString(bookUpdateDto), headers);
    }

    private HttpEntity<String> getHeaders(AuthorUpdateDto authorUpdateDto) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", securityTokenContext.getToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper mapper = new ObjectMapper();

        return new HttpEntity<>(mapper.writeValueAsString(authorUpdateDto), headers);
    }
}
