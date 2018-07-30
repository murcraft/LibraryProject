package by.htp.kyzniatsova.controller;

import by.htp.kyzniatsova.controller.impl.BookControllerImpl;

public class BookController extends FactoryController {

	@Override
	public MainController factoryMethod() {
		return new BookControllerImpl();
	}

}
