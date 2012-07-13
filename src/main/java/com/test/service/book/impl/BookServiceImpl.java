package com.test.service.book.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.test.bean.Book;
import com.test.bean.Books;
import com.test.service.book.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Override
	public Books getBooks() {
		
		Books books = new Books();
		
		List<Book> bookList = new ArrayList<Book>();

		Book book1 = new Book();
		book1.setIsbn("9780446365383");
		book1.setTitle("Gone with the Wind");

		Book book2 = new Book();
		book2.setIsbn("0307408841");
		book2.setTitle("In the Garden of Beasts: Love, Terror, and an American Family in Hitler's Berlin");

		bookList.add(book1);
		bookList.add(book2);
		
		books.setBooks(bookList);
		
		return books;

	}
}
