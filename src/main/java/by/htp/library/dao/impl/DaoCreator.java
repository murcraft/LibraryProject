package by.htp.library.dao.impl;

import by.htp.library.dao.DaoParam;

public abstract class DaoCreator<T> {
	
	public abstract DaoParam<T> factoryMethod();


}
