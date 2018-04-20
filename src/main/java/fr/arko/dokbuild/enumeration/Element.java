package fr.arko.dokbuild.enumeration;

public enum Element {
	AGL(0), TEQ(1), INT(2), STR(3), PHY(4);

	private int element = 0;

	Element(int element) {
		this.element = element;
	}

	public String toString() {
		return String.valueOf(element);
	}

	public int value() {
		return element;
	}
}
