package szaqal.forkjoin;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * @author malczyk.pawel@gmail.com
 * 
 */
public class App {

	public static final ForkJoinPool POOL = new ForkJoinPool();
	
	public static final int CORE_COUNT = Runtime.getRuntime().availableProcessors();

	public static void main(String[] args) throws IOException {
		System.out.println("Application started with processors " + CORE_COUNT);
		List<String> result = POOL.invoke(new GenerateItemsTask());
		System.out.println("Done " + result);
	}

}
