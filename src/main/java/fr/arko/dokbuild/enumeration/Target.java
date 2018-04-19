package fr.arko.dokbuild.enumeration;

public enum Target {
	SELF(1), ENEMY(4), ALLIES(2);

	private int target = 0;

	Target(int target) {
		this.target = target;
	}

	public String toString() {
		return String.valueOf(target);
	}
}
