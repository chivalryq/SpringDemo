package com.example.demo.controller;

import com.example.demo.entity.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@RestController直接把json写入http相应
@RestController
//默认映射所有的动词
@RequestMapping(value = "/api")
public class BookController {

    @Value("${wuhan2020}")
    String wuhan2020;
    private List<Book> books = new ArrayList<>();

    //等价于@RequestMapping(method = RequestMethod.POST)
    //RequestEntity是表示整个Http reponse。包括状态码、header和正文
    @PostMapping("/book")
    public ResponseEntity<List<Book>> addBook(@RequestBody Book book) {
        // @RequestBody 可以将 HttpRequest body 中的 JSON 类型数据反序列化为合适的 Java 类型
        books.add(book);
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity deleteBookById(@PathVariable("id") int id) {
        books.remove(id);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/book")
    public ResponseEntity getBookByName(@RequestParam("name") String name) {
        List<Book> results = books.stream().filter(book -> book.getName().equals(name)).collect(Collectors.toList());
        return ResponseEntity.ok(results);
    }

    @GetMapping("/config")
    public ResponseEntity<String> getConfig(){
        return ResponseEntity.ok(wuhan2020);
    }
}