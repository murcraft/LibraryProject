package by.htp.kyzniatsova.controller;

import by.htp.kyzniatsova.controller.impl.ReaderControllerImpl;

public class ReaderController extends FactoryController {

	@Override
	public MainController factoryMethod() {
		return new ReaderControllerImpl();
	}

}
