package com.sy.handler;

import com.sy.beans.Book;
import com.sy.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Book")
public class BookHandler
{
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/findAll/{page}/{size}")
    @ResponseBody
    public Page<Book> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size)
    {
        //  PageRequest.of(0,6)对象，第一个参数表示页数从0开始，第二个参数表示每一页的数据
        Pageable pageable = PageRequest.of(page-1,size);
        return bookRepository.findAll(pageable);
    }

    @GetMapping("/findById/{id}")
    @ResponseBody
    public Book findById(@PathVariable("id") Integer id )
    {
        return bookRepository.findById(id).get();
    }

    @PostMapping("/save")
    @ResponseBody
    public Boolean save(@RequestBody Book book )
    {
        Book result = bookRepository.save(book);
        //  如果result==null则表示添加失败
        return result==null;
    }

    @PostMapping("/update")
    @ResponseBody
    public Boolean update(@RequestBody Book book )
    {
        Book result = bookRepository.save(book);
        //  如果result==null则表示添加失败
        return result==null;
    }

    @DeleteMapping("/deleteById")
    @ResponseBody
    public Boolean deleteById( Integer id )
    {
        bookRepository.deleteById(id);
        return true;
    }


}
