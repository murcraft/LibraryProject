package by.htp.library.domain.entity;

public enum LibrarianEnum {
	LOGIN("librarian"), PASSWORD("12345q");
	
	private String value;

	private LibrarianEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
