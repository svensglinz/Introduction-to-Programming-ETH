public class ItemFactory {

	private Database d;

	public ItemFactory(Database d) {
		this.d = d;
	}

	public Item createItem(Level level, int id, int age, int health) {
		return new Item(level, id, age, health, d);
	}

	public Item createDeclass(Level level, int id, int targetId) {
		Item reference = d.getItem(targetId);

		if (reference instanceof DeclassItem)
			reference = ((DeclassItem) reference).reference;

		if (reference == null || isLevelRelated(reference.getLevel(), level))
			throw new IllegalArgumentException();

		return new DeclassItem(level, id, reference, d);
	}

	private boolean isLevelRelated(Level one, Level two) {
		int p1 = 0;
		int p2 = 0;
		for (int i : one.getPoints()) {
			p1 += i;
		}
		for (int i : two.getPoints()) {
			p2 += i;
		}
		return p2 == p1;
	}
}
