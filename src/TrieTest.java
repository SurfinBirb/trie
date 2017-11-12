import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TrieTest {

	@Test
	public void generalTest(){
		Trie trie = new Trie();
		trie.add("abcdefg");
		assertTrue(trie.contains("abcdefg"));
		assertFalse(trie.contains("abcdef"));
		assertFalse(trie.contains("a"));
		assertFalse(trie.contains("g"));

		trie.add("zyx");
		trie.add("zwv");
		trie.add("zyt");
		trie.add("abcde");
		assertTrue(5 == trie.size);
		assertTrue(trie.contains("abcdefg"));
		assertFalse(trie.contains("abcdef"));
		assertTrue(trie.contains("abcde"));
		assertTrue(trie.contains("zyx"));
		assertTrue(trie.contains("zyt"));
		assertTrue(trie.contains("zwv"));
		assertFalse(trie.contains("zy"));
		assertFalse(trie.contains("zv"));

		trie.remove("zwv");
		trie.remove("zy");
		assertTrue(4 == trie.size);
		assertTrue(trie.contains("zyt"));
		assertFalse(trie.contains("zwv" ));

		trie.clear();
		assertTrue(0 == trie.size);
		assertFalse(trie.contains("zyx"));
	}

	@Test
	public void iteratorTest() throws Exception {
		Trie trie = new Trie();
		trie.add("tst");
		trie.add("tsta");
		trie.add("tstab");
		trie.add("tstb");
		Iterator<String> it = trie.iterator();
		assertTrue(it.hasNext());
		assertEquals("tst", it.next());
		assertTrue(it.hasNext());
		assertEquals("tsta", it.next());
		assertTrue(it.hasNext());
		assertEquals("tstab", it.next());
		assertTrue(it.hasNext());
		assertEquals("tstb", it.next());
		assertFalse(it.hasNext());
	}

}