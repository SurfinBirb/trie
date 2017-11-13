import javax.swing.text.html.HTMLDocument;
import java.util.*;

public class Trie extends AbstractSet<String> {

	Integer size = 0;

	private class Node{
		Map<Character, Node> children = new TreeMap<>();
	}

	private Node root = new Node();

	@Override
	public void clear(){
		root.children.clear();
		size = 0;
	}

	private Node findNode(String element){
		Node current = root;
		for(Character c: element.toCharArray()){
			Node node = current.children.get(c);
			if (node != null) current = node;
			else return null;
		}
		return current;
	}

	public boolean add(String element){
		Node current = root;
		boolean modified = false;
		for(Character c: withZero(element).toCharArray()) {
			Node child = current.children.get(c);
			if (child != null) {
				current = child;
			} else {
				modified = true;
				Node newChild = new Node();
				current.children.put(c, newChild);
				current = newChild;
			}
		}
			if(modified){
				size++;
			}
			return  modified;
	}


	boolean remove(String element){
		Node node = findNode(element);
		Node current;
		if (node != null) current = node;
		else return false;
		if (current.children.remove('0') != null) {
			size--;
			return true;
		}
		return false;
	}

	public boolean contains(String element) {
		return findNode(withZero(element)) != null;
	}

	@Override
	public Iterator<String> iterator() {
		return new TrieIterator();
	}

	@Override
	public int size() {
		return this.size;
	}



	private String withZero(String s){
		return s + '0';
	}

	private class TrieIterator implements  Iterator<String>{

		private ArrayDeque<Iterator<Map.Entry<Character, Node>>> childrenIteratorStack;
		private StringBuilder charBuffer;

		public TrieIterator(){
			this.childrenIteratorStack = new ArrayDeque<>();
			childrenIteratorStack.addLast(root.children.entrySet().iterator());
			charBuffer = new StringBuilder();
		}

		@Override
		public boolean hasNext() {
			return childrenIteratorStack.stream().anyMatch(Iterator::hasNext);
		}

		@Override
		public String next() {
			String stringForReturn = null;
			Iterator<Map.Entry<Character, Node>> childrenIterator = childrenIteratorStack.peekLast(); // get first iterator
			while(!childrenIterator.hasNext() && charBuffer.length() != 0){
				charBuffer.deleteCharAt(charBuffer.length() - 1);
				childrenIteratorStack.removeLast();
				childrenIterator = childrenIteratorStack.peekLast();
			}
			while (childrenIterator.hasNext()){
				Map.Entry<Character, Node> entry = childrenIterator.next();
				if(entry.getKey() != '0'){ // if not the end
					charBuffer.append(entry.getKey()); // add character
					childrenIterator = entry.getValue().children.entrySet().iterator();  // iterator for the children
					childrenIteratorStack.addLast(childrenIterator); // add last element to the iterator deque
				} else {
					stringForReturn = charBuffer.toString();
					break;
				}
			}
			if(stringForReturn == null) throw new NoSuchElementException();
			return stringForReturn;
		}
	}

}
