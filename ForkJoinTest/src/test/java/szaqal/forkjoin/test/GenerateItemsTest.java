package szaqal.forkjoin.test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.junit.Test;

import szaqal.forkjoin.GenerateItemsTask;
import szaqal.forkjoin.enums.StringItemType;

/**
 * @author malczyk.pawel@gmail.com
 *
 */
public class GenerateItemsTest {

	@Test
	public void testGenerate() {
		List<String> generated =  new ForkJoinPool().invoke(new GenerateItemsTask(10, StringItemType.COMPANY));
		assertEquals(10, generated.size());
	}
}
