package szaqal.forkjoin;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

/**
 * @author malczyk.pawel@gmail.com
 * 
 */
@SuppressWarnings("static-access")
public class App {

	private static final Options OPTIONS = new Options();

	static {
		Option qty = OptionBuilder.withArgName("qty").hasArg()
				.withDescription("items quantity").create("qty");
		OPTIONS.addOption(qty);
	}

	public static final int CORE_COUNT = Runtime.getRuntime()
			.availableProcessors();

	public static ForkJoinPool POOL = new ForkJoinPool(CORE_COUNT);

	public static void main(String[] args) throws IOException {
		CommandLineParser parser = new PosixParser();
		try {
			CommandLine line = parser.parse(OPTIONS, args);
			String qty = line.getOptionValue("qty");
			if (qty == null) {
				throw new ParseException("Missing required parameter");
			}
			System.out.println("Application started with processors "
					+ CORE_COUNT);
			List<String> result = POOL.invoke(new GenerateItemsTask(
					(qty == null) ? 0 : Integer.valueOf(qty)));
		} catch (ParseException exp) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("ForkJoin", OPTIONS);
			System.out.println("Parsing failed.  Reason: " + exp.getMessage());
		}

		System.out.println("Done");
	}

}
