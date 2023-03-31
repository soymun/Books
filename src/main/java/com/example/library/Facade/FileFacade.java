package com.example.library.Facade;

import com.example.library.Dto.Author.AuthorDto;
import com.example.library.Dto.Author.AuthorUpdateDto;
import com.example.library.Dto.Book.BookDto;
import com.example.library.Dto.Book.BookUpdateDto;
import com.example.library.Service.Imp.AuthorServiceImp;
import com.example.library.Service.Imp.BookServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;

@Component
@Slf4j
@RequiredArgsConstructor
public class FileFacade {

    @Value("${content.url}")
    private String url;

    private final BookServiceImp bookServiceImp;

    private final AuthorServiceImp authorServiceImp;

    public void saveBookFile(Long id, MultipartFile photo, MultipartFile pdf) throws IOException {

        BookUpdateDto bookUpdateDto = new BookUpdateDto();

        File filePdf = new File(url + pdf.getOriginalFilename());
        if (filePdf.createNewFile()) {
            byte[] bytes = pdf.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(filePdf));
            stream.write(bytes);
            stream.close();
            bookUpdateDto.setUrlToPdfFile(filePdf.getAbsolutePath());
        }

        File filePhoto = new File(url + photo.getOriginalFilename());
        if (filePhoto.createNewFile()) {
            byte[] bytes = photo.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(filePdf));
            stream.write(bytes);
            stream.close();
            bookUpdateDto.setUrlToMainPhoto(filePhoto.getAbsolutePath());
        }
        bookUpdateDto.setId(id);

        bookServiceImp.updateBook(bookUpdateDto);
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
            authorUpdateDto.setUrlToPhoto(fileAuthor.getAbsolutePath());
        }

        authorServiceImp.updateAuthor(authorUpdateDto);
    }

    public void updateBookFiles(Long id, MultipartFile photo, MultipartFile pdf) throws IOException {
        BookDto bookDto = bookServiceImp.getBookById(id);
        updateBook(pdf, bookDto.getUrlToPdfFile());
        updateBook(photo, bookDto.getUrlToMainPhoto());
    }

    public void getPdfByBookId(HttpServletRequest request, HttpServletResponse response, Long bookId) throws IOException {
        BookDto bookDto = bookServiceImp.getBookById(bookId);
        File file = new File(bookDto.getUrlToPdfFile());
        setFile(file, response);
    }

    public void getPhotoByAuthorId(HttpServletRequest request, HttpServletResponse response, Long authorId) throws IOException {
        AuthorDto authorDto = authorServiceImp.getAuthor(authorId);
        File file = new File(authorDto.getUrlToPhoto());
        setFile(file, response);
    }

    public void getMainPhotoByBookId(HttpServletRequest request, HttpServletResponse response,Long id) throws IOException {
        BookDto bookDto = bookServiceImp.getBookById(id);
        File file = new File(bookDto.getUrlToMainPhoto());
        setFile(file, response);
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
}
