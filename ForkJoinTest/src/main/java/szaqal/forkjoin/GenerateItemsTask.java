package szaqal.forkjoin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

import szaqal.forkjoin.enums.StringItemType;
import szaqal.forkjoin.formatters.ItemFormatter;

/**
 * @author malczyk.pawel@gmail.com
 * 
 */
public class GenerateItemsTask extends RecursiveTask<List<String>> {

	private static final long serialVersionUID = -3353190810405095690L;

	private final List<RecursiveTask<List<String>>> forks = new LinkedList<>();
	
	private final int itemQuantity;
	
	private final StringItemType itemType;
	
	private final ItemFormatter.TYPES formatterType;
	
	public GenerateItemsTask(int quantity, StringItemType itemType, ItemFormatter.TYPES formatterType) {
		this.itemQuantity = quantity;
		this.itemType = itemType;
		this.formatterType = formatterType;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<String> compute() {
		System.out.println("Item type : " + itemType);
		long start = System.currentTimeMillis();
		List<String> items = new ArrayList<>();
		System.out.println(String.format("Generating %s items with pool size %s", itemQuantity, App.CORE_COUNT) );
		ItemFormatter<String> formatter = null;
		
		try {
			formatter = (ItemFormatter<String>) formatterType.getClazz().newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
		for (int i = 0; i < App.CORE_COUNT; i++) {
			GenerateItemTask genTask = new GenerateItemTask(itemQuantity / App.CORE_COUNT, itemType, formatter);
			forks.add(genTask);
			genTask.fork();
		}
		System.out.println("Pools size " + App.POOL.getPoolSize());

		for (RecursiveTask<List<String>> task : forks) {
			try {
				items.addAll(task.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("Exec time " + (end - start));
		return items;
	}

}
