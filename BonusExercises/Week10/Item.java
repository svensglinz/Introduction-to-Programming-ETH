import java.util.HashSet;
import java.util.List;

public class Item {

	private Level level;
	private int id;
	private int age;
	private int health;
	private HashSet<Integer> links;
	Database d;

	public Item(Level level, int id, int age, int health, Database d) {
		this.level = level;
		this.id = id;
		this.age = age;
		this.health = health;
		this.d = d;
		this.links = new HashSet<>(List.of(id));
	}

	public Level getLevel() {return level;}

	public int getID() {return id;}

	public int getAge() {return age;}

	public int getHealth() {return health;}

	public void setHealth(int newHealth) {
		HashSet<Integer> healthSet = new HashSet<>();
		setHealthRec(healthSet, this, newHealth);
	}

	private void setHealthRec(HashSet<Integer> healthSet, Item item, int newHealth) {
		for (int i : item.links) {
			Item it = d.getItem(i);
			if (!healthSet.contains(it.getID())) {
				healthSet.add(it.getID());
				if (!(it instanceof DeclassItem))
					it.health = newHealth;
				setHealthRec(healthSet, it, newHealth);
			}
		}
	}

	public void setLinks(List<Integer> link) {
		links.addAll(link);
	}
}

class DeclassItem extends Item {
	public Item reference;

	public DeclassItem(Level level, int id, Item reference, Database d) {
		super(level, id, reference.getAge(), reference.getHealth(), d);
		this.reference = reference;
	}

	@Override
	public int getAge() {return reference.getAge();}

	@Override
	public int getHealth() {return reference.getHealth();}

	@Override
	public void setHealth(int newHealth) {return;}
}
