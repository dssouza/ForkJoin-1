package szaqal.forkjoin;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * @author malczyk.pawel@gmail.com
 * 
 */
public class App {
	
	public static final int CORE_COUNT = Runtime.getRuntime().availableProcessors();

	public static ForkJoinPool POOL = new ForkJoinPool(CORE_COUNT);
	

	public static void main(String[] args) throws IOException {
		System.out.println("Application started with processors " + CORE_COUNT);
		List<String> result = POOL.invoke(new GenerateItemsTask(7_000_000));
		System.out.println("Done");
	}

}
