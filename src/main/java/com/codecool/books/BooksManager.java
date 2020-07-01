package com.codecool.books;

import com.codecool.books.model.Author;
import com.codecool.books.model.Book;
import com.codecool.books.model.Dao;
import com.codecool.books.view.UserInterface;


public class BooksManager extends Manager {
	Dao<Book>  booksDao;
	Dao<Author>  authorDao;

	public BooksManager(UserInterface ui, Dao<Book>  booksDao, Dao<Author> authorDao) {
		super(ui);
		this.booksDao = booksDao;
		this.authorDao = authorDao;
	}

	@Override
	protected String getName() {
		return "Books Manager";
	}

	@Override
	protected void list() {
		for (Book book : booksDao.getAll()) {
			ui.println(book.toString());
		}
	}

	@Override
	protected void add() {
		String title = ui.readString("Title", "X");
		int authorId = ui.readInt("Author ID", 0);
		booksDao.add(new Book(authorDao.get(authorId), title));
	}

	@Override
	protected void edit() {
		int id = ui.readInt("Book ID", 0);
		Book book = booksDao.get(id);
		if (book == null) {
			ui.println("Book not found!");
			return;
		}
		ui.println(book);

		String title = ui.readString("Title", book.getTitle());
		int authorid = ui.readInt("Author", book.getAuthor().getId());
		book.setTitle(title);
		book.setAuthor(authorDao.get(authorid));
		booksDao.update(book);
	}
}
