package by.htp.library.controller.factory;

import by.htp.library.controller.FactoryController;
import by.htp.library.controller.MainController;
import by.htp.library.controller.impl.RegistReadersControllerImpl;

public class RegistReadersController extends FactoryController  {

	@Override
	public MainController factoryMethod() {
		return new RegistReadersControllerImpl();
	}

}
