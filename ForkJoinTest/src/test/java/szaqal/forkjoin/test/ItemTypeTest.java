package szaqal.forkjoin.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import szaqal.forkjoin.ItemType;

/**
 * @author malczyk.pawel@gmail.com
 *
 */
public class ItemTypeTest {

	@Test
	public void testCount() {
		assertEquals(3, ItemType.values().length);
	}
	
	@Test
	public void testValueOf() {
		assertEquals(ItemType.COMPANY, ItemType.valueOf("COMPANY"));
	}
}
