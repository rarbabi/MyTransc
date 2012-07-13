package com.test.controller.book;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.xpath;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.RequestBuilder;
import org.springframework.test.web.server.request.DefaultRequestBuilder;
import org.springframework.test.web.server.samples.context.GenericWebXmlContextLoader;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.test.bean.Book;
import com.test.bean.Books;
import com.test.service.book.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = TestGenericWebXmlContextLoader.class, locations = {
		"classpath:applicationContext.xml", "classpath:mockitoTestContext.xml" })
public class BookControllerTestContextTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	private BookService bookService;

	@Before
	public void setup() {

		this.mockMvc = MockMvcBuilders.webApplicationContextSetup(this.wac)
				.build();

		bookService = wac.getBean(BookService.class);

		Books books = new Books();

		List<Book> bookList = new ArrayList<Book>();

		Book book2 = new Book();
		book2.setIsbn("0307408841");
		book2.setTitle("In the Garden of Beasts: Love, Terror, and an American Family in Hitler's Berlin");

		bookList.add(book2);

		books.setBooks(bookList);
		when(bookService.getBooks()).thenReturn(books);
	}

	@Test
	public void testGetBooks() throws Exception {
	

		mockMvc.perform(get("/books").header("foo","bar"))
				.andExpect(status().isOk())
				.andExpect(content().type(MediaType.APPLICATION_XML))
				.andExpect(
						xpath("/books/books/title")
								.string(equalTo("In the Garden of Beasts: Love, Terror, and an American Family in Hitler's Berlin")));
	}

}

class TestGenericWebXmlContextLoader extends GenericWebXmlContextLoader {

	public TestGenericWebXmlContextLoader() {
		super("src/main/resources", false);
	}

}
