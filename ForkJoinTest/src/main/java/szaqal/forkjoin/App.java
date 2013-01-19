package szaqal.forkjoin;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import szaqal.forkjoin.enums.StringItemType;
import szaqal.forkjoin.formatters.ItemFormatter;

/**
 * @author malczyk.pawel@gmail.com
 * 
 */
@SuppressWarnings("static-access")
public class App {

	private static final Options OPTIONS = new Options();

	static {
		OPTIONS.addOption(OptionBuilder.withArgName("qty").hasArg().withDescription("items quantity").create("qty"));
		OPTIONS.addOption(OptionBuilder.withArgName("type").hasArg().withDescription("item type (MALE_PERSON,FEMALE_PERSON|COMPANY)")
				.create("type"));
		OPTIONS.addOption(OptionBuilder.withArgName("filename").hasArg().withDescription("result file name").create("filename"));
		OPTIONS.addOption(OptionBuilder.withArgName("format").hasArg().withDescription("result format").create("format"));
	}

	public static final int CORE_COUNT = Runtime.getRuntime().availableProcessors();

	public static ForkJoinPool POOL = new ForkJoinPool(CORE_COUNT);

	public static void main(String[] args) throws IOException {
		CommandLineParser parser = new PosixParser();
		try {
			CommandLine line = parser.parse(OPTIONS, args);
			String qty = line.getOptionValue("qty");
			if (qty == null) {
				throw new ParseException("Missing required parameter");
			}
			String type = line.getOptionValue("type");
			if (type == null) {
				throw new ParseException("Missing required parameter");
			}

			String fileName = line.getOptionValue("filename");
			if (fileName == null) {
				throw new ParseException("Missing required parameter");
			}

			String formatter = line.getOptionValue("format");
			ItemFormatter.TYPES formatterType = ItemFormatter.TYPES.PLAIN;
			if (formatter != null) {
				formatterType = ItemFormatter.TYPES.valueOf(formatter);
			}

			System.out.println("Application started with processors " + CORE_COUNT);
			List<String> generatedItems = POOL.invoke(new GenerateItemsTask((qty == null) ? 0 : Integer.valueOf(qty), StringItemType
					.valueOf(type), formatterType));

			storeFile(fileName, generatedItems);
		} catch (ParseException exp) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("ForkJoin", OPTIONS);
			System.out.println("Parsing failed.  Reason: " + exp.getMessage());
		} catch (URISyntaxException e) {
			System.out.println("Invalid filename given");
		}
		System.out.println("Done");
	}

	private static void storeFile(String fileName, List<String> result) throws IOException, URISyntaxException {
		Path filePath = Paths.get(new URI("file://" + fileName));
		Files.deleteIfExists(filePath);
		BufferedWriter writer = Files.newBufferedWriter(filePath, Charset.defaultCharset());
		for (String item : result) {
			writer.write(item);
			writer.newLine();
		}
		writer.flush();
	}

}
