package szaqal.forkjoin;

import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

/**
 * @author malczyk.pawel@gmail.com
 * 
 */
public class App {

	private static final int REP_COUNT = 1000;

	public static final String[] STARTNG_LETTERS = { "A", "B", "C", "D", "E",
			"F", "G", "H", "I", "J", "K", "L", "M", "N" };

	private static final ForkJoinPool forkJoinPool = new ForkJoinPool();

	public static void main(String[] args) throws IOException {
		Document document = Document.fromFile();
		runForkJoin(document);
		runSequential(document);

	}

	private static void runForkJoin(Document document) {

		long start = System.currentTimeMillis();
		RunTask runTask = new RunTask(document);
		Integer result = 0;
		for (int i = 0; i < 1000; i++) {
			result = forkJoinPool.invoke(runTask);
		}
		System.out.println("Result -> " + result);
		long end = System.currentTimeMillis();

		System.out.println("Time par : " + (end - start));
	}

	private static void runSequential(Document document) {
		long start = System.currentTimeMillis();
		Integer result =0;
		for (int i = 0; i < REP_COUNT; i++) {
			result = 0;
			for (String letter : STARTNG_LETTERS) {
				result += new NameCount(document, letter).compute();
			}
		}
		System.out.println("Result -> " + result);
		long end = System.currentTimeMillis();
		System.out.println("Time Seq : " + (end - start));
	}
}
