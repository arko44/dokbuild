package fr.arko.dokbuild.enumeration;

public enum Calc {
	ABSOLUTE(0), PERCENT(2);

	private int calc = 0;

	Calc(int calc) {
		this.calc = calc;
	}

	public String toString() {
		return String.valueOf(calc);
	}
}
