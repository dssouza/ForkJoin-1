package szaqal.forkjoin.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.junit.Test;

import szaqal.forkjoin.AbstractItemGenerator;
import szaqal.forkjoin.GenerateItemTask;
import szaqal.forkjoin.ItemType;

/**
 * @author malczyk.pawel@gmail.com
 * 
 */
public class GenerateItemTaskTest {

	@Test
	public void testGenerateItemMale() throws Exception {
		GenerateItemTask itemTask = new GenerateItemTask(10, ItemType.MALE_PERSON);
		new ForkJoinPool().execute(itemTask);
		List<String> result = itemTask.get();
		assertNotNull(result);
		assertEquals(10, result.size());
	}
	
	@Test
	public void testGenerateItemFemale() throws Exception {
		GenerateItemTask itemTask = new GenerateItemTask(5, ItemType.FEMALE_PERSON);
		new ForkJoinPool().execute(itemTask);
		List<String> result = itemTask.get();
		assertNotNull(result);
		assertEquals(5, result.size());
	}
	
	@Test
	public void testGenerateItemCompany() throws Exception {
		GenerateItemTask itemTask = new GenerateItemTask(2, ItemType.COMPANY);
		new ForkJoinPool().execute(itemTask);
		List<String> result = itemTask.get();
		assertNotNull(result);
		assertEquals(2, result.size());
	}
	
	@Test(expected=NullPointerException.class)
	public void testException() {
		AbstractItemGenerator itemGenerator = new AbstractItemGenerator() {
			
			@Override
			public String generateItem() {
				loadAll("invalidFileName");
				return "";
			}
		};
		assertNotNull(itemGenerator.generateItem());
	}
}
