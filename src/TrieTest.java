import org.junit.Test;

import java.util.Iterator;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by SurfinBirb on 12.11.2017.
 */
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
		assertTrue(5 == trie.size());
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
		assertTrue(4 == trie.size());
		assertTrue(trie.contains("zyt"));
		assertFalse(trie.contains("zwv" ));

		trie.clear();
		assertTrue(0 == trie.size());
		assertFalse(trie.contains("zyx"));
	}

	@Test
	public void iterator() throws Exception {
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

	@Test
	public void treeSetComparisonTest(){
		TreeSet<String> testTreeSet = new TreeSet<>();
		Trie trie = new Trie();
		char[] alphabetArray = "0123456789qwertyuiopasdfghjklzxcvbnm".toCharArray();
		for(char c: alphabetArray){
			for(char c1: alphabetArray){
				for(char c2: alphabetArray){
					String s = String.valueOf(new char[]{c, c1, c2});
					trie.add(s);
					testTreeSet.add(s);
				}
			}
		}
		Iterator<String> testTreeSetIterator = testTreeSet.iterator();
		Iterator<String> testTrieIterator = trie.iterator();
		while(testTreeSetIterator.hasNext()){
			assertTrue(testTrieIterator.hasNext());
			assertEquals(testTreeSetIterator.next(),testTrieIterator.next());
		}
		assertFalse(testTreeSetIterator.hasNext());
		assertFalse(testTrieIterator.hasNext());
	}


	@Test
	public void zeroTest()throws Exception{
		Trie trie = new Trie();
		trie.add("somestring");
		trie.add("somestring0");
		trie.add("0somestring");
		trie.add("1somestring");
		Iterator<String> it = trie.iterator();
		assertTrue(it.hasNext());
		assertEquals("0somestring", it.next());
		assertTrue(it.hasNext());
		assertEquals("1somestring", it.next());
		assertTrue(it.hasNext());
		assertEquals("somestring", it.next());
		assertTrue(it.hasNext());
		assertEquals("somestring0", it.next());
		assertFalse(it.hasNext());
	}

}