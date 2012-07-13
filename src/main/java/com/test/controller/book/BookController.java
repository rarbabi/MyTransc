package com.test.controller.book;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.test.bean.AddBookResponse;
import com.test.bean.Book;
import com.test.service.book.BookService;

@Controller
public class BookController {
	private static final Logger LOGGER = Logger.getLogger(BookController.class);

	@Resource
	private BookService bookService;

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public ModelAndView getAllBooks(HttpServletRequest httpServletRequest)
			throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("book", bookService.getBooks());

		return modelAndView;
	}

	@RequestMapping(value = "/books/{isbn}", method = RequestMethod.GET)
	public ModelAndView getBookByIsbn(@PathVariable String isbn,
			HttpServletRequest request) throws Exception {

		LOGGER.info("retrieved customHeaderParameter: "
				+ request.getHeader("customHeaderParameter"));

		Book book = new Book();
		if (isbn.equals("123")) {
			book.setTitle("The Lion, the Witch and the Wardrobe");
		}
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("book", book);

		return modelAndView;
	}

	@RequestMapping(value = "/books/add", method = RequestMethod.POST)
	public ModelAndView add(@RequestParam("isbn") String isbn,
			@RequestParam("title") String title) throws Exception {

		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle(title);

		ModelAndView modelAndView = new ModelAndView();
		LOGGER.info("adding " + isbn + isbn + " title " + title);

		AddBookResponse addBookResponse = new AddBookResponse();
		addBookResponse.setIsbn(isbn);
		addBookResponse.setResult("success");
		modelAndView.addObject("addBookResponse", addBookResponse);

		return modelAndView;
	}

}
