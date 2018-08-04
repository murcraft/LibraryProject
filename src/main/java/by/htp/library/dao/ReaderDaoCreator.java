package by.htp.library.dao;


public abstract class ReaderDaoCreator<T> implements DaoParam<T> {
	
	public abstract DaoParam<T> factoryMethod();

}
