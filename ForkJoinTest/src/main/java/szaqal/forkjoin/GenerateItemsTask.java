package szaqal.forkjoin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;


/**
 * @author malczyk.pawel@gmail.com
 * 
 */
public class GenerateItemsTask extends RecursiveTask<List<String>> {

	private static final long serialVersionUID = -3353190810405095690L;

	List<RecursiveTask<List<String>>> forks = new LinkedList<>();

	@Override
	protected List<String> compute() {

		List<String> items = new ArrayList<>();

		for (int i = 0; i < App.POOL.getPoolSize(); i++) {
			GenerateItemTask genTask = new GenerateItemTask(10, ItemType.MALE_PERSON);
			forks.add(genTask);
			genTask.fork();
		}

		for (RecursiveTask<List<String>> task : forks) {
			try {
				items.addAll(task.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		return items;
	}

}
