package fr.arko.dokbuild.enumeration;

public enum Classe {
	SUPER(10), EXTREME(20);

	private int classe = 0;

	Classe(int classe) {
		this.classe = classe;
	}

	public String toString() {
		return String.valueOf(classe);
	}

	public int value() {
		return classe;
	}
}
