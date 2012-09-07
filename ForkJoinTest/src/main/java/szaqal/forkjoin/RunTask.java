package szaqal.forkjoin;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * @author malczyk.pawel@gmail.com
 * 
 */
public class RunTask extends RecursiveTask<Integer> {

	private static final long serialVersionUID = -330258922391398670L;
	
	
	private final Document document;

	public RunTask(Document document) {
		this.document = document;
	}

	@Override
	protected Integer compute() {
		Integer count = 0;
		List<RecursiveTask<Integer>> forks = new LinkedList<>();


//		int size = document.getLines().size();
//		System.out.println("Lines count " + size);

		for (String letter : App.STARTNG_LETTERS) {
			NameCount searchTask = new NameCount(document, letter);
			forks.add(searchTask);
			searchTask.fork();
		}

		for (RecursiveTask<Integer> task : forks) {
			count = count + task.join();
		}

		return count;
	}

}
