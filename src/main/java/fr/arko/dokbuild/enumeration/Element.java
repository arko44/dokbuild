package fr.arko.dokbuild.enumeration;

public enum Element {
	AGL(0), TEQ(1), INT(2), STR(3), PHY(4),
	SAGI(10), STEC(11), SINT(12), SPUI(13), SEND(14),
	EAGI(20), ETEC(21), EINT(22), EPUI(23), EEND(24);

	private int element = 0;

	Element(int element) {
		this.element = element;
	}

	public String toString() {
		return String.valueOf(element);
	}
}
