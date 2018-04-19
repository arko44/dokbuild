package fr.arko.dokbuild.enumeration;

public enum Rarity {
	N(0), R(1), SR(2), SSR(3), UR(4), LR(5);

	private int rarity = 0;

	Rarity(int rarity) {
		this.rarity = rarity;
	}

	public String toString() {
		return String.valueOf(rarity);
	}

	public int value() {
		return rarity;
	}
}
