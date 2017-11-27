package searching;

import java.util.Iterator;

public abstract class ST<Key, Value> implements Iterable<Key> {
	public <Key, Value> ST() {
	}
	public void put(Key key, Value value) {

	}

	public abstract Value get(Key key);

	public abstract boolean contains(Key key);

	public abstract void delete(Key key);

	public abstract boolean isEmpty();

	public abstract int size();

	@Override
	public Iterator<Key> iterator() {
		return null;
	}

}
