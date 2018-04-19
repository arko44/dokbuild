package fr.arko.dokbuild.enumeration;

public enum Efficacy {
	ATT(1), DEF(2), ATT_DEF(3), RECOVER_HP(4), ADD_KI(5);

	private int efficacy = 0;

	Efficacy(int efficacy) {
		this.efficacy = efficacy;
	}

	public String toString() {
		return String.valueOf(efficacy);
	}
}
