import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Database {

	private List<Item> items = new ArrayList<>();
	private ItemFactory factory = new ItemFactory(this);
	private HashMap<Integer, Item> itemMap = new HashMap<>();

	public void add(Item item) {
		items.add(item);
		itemMap.put(item.getID(), item);
	}

	public List<Item> getItems() {
		return new ArrayList<>(items);
	}

	public Item getItem(int id) {
		return itemMap.get(id);
	}

	public ItemFactory getItemFactory() {
		return factory;
	}

	public void createLink(List<Integer> ids) {
		if (!itemMap.keySet().containsAll(ids))
			throw new IllegalArgumentException();
		for (int i : ids) {
			getItem(i).setLinks(ids);
		}
	}
}
