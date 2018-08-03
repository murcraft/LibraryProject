package by.htp.collections.stores;

import java.util.ArrayList;
import java.util.List;

import by.htp.library.domain.entity.Reader;

public class ReadersStore {
	
	private List<Reader> readers = new ArrayList<Reader>();
	

	public ReadersStore() {

	}

	public ReadersStore(List<Reader> readers) {
		super();
		this.readers = readers;
	}


	public List<Reader> getReaderBase() {
		return readers;
	}

	public void setReaderBase(List<Reader> readerBase) {
		this.readers = readerBase;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((readers == null) ? 0 : readers.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReadersStore other = (ReadersStore) obj;
		if (readers == null) {
			if (other.readers != null)
				return false;
		} else if (!readers.equals(other.readers))
			return false;
		return true;
	}

}
