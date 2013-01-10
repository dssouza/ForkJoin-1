package szaqal.forkjoin.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

import org.junit.Test;

import szaqal.forkjoin.GenerateItemTask;
import szaqal.forkjoin.ItemType;

/**
 * @author malczyk.pawel@gmail.com
 * 
 */
public class GenerateItemTaskTest {

	@Test
	public void testGenerateItemTask() {
		GenerateItemTask itemTask = new GenerateItemTask(10,ItemType.MALE_PERSON);
		try {
			new ForkJoinPool().execute(itemTask);
			List<String> result = itemTask.get();
			assertNotNull(result);
			assertEquals(10, result.size());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
