package com.codecool.books.view;

import com.codecool.books.Manager;
import com.codecool.books.model.Author;
import com.codecool.books.model.Book;
import com.codecool.books.model.Dao;


public class BooksManager extends Manager {
	Dao<Book>  booksDao;
	Dao<Author>  authorDao;

	public BooksManager(UserInterface ui, Dao<Book>  booksDao) {
		super(ui);
		this.booksDao = booksDao;
	}

	@Override
	protected String getName() {
		return "Books Manager";
	}

	@Override
	protected void list() {

	}

	@Override
	protected void add() {
		String title = ui.readString("Title", "X");
		int authorid = ui.readInt("Autor ID", 0);
		booksDao.add(new Book());
	}

	@Override
	protected void edit() {

	}
}
