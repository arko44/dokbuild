package fr.arko.dokbuild.enumeration;

public enum Causality {
	NONE(0), HP_ABOVE(1), HP_BELOW(2), KI_ABOVE(3), KI_BELOW(4), ONE_ENEMY(16);

	private int causality = 0;

	Causality(int causality) {
		this.causality = causality;
	}

	public String toString() {
		return String.valueOf(causality);
	}
}
