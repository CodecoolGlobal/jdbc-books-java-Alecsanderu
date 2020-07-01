package com.codecool.books.model;

import lombok.Data;

public @Data class Book {
	private Author author;
	private String title;
	private Integer id;

	public Book(Author author, String title) {
		this.author = author;
		this.title = title;
	}


}
