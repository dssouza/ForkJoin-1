package szaqal.forkjoin.formatters;


/**
 * @author malczyk.pawel@gmail.com
 *
 */
public class CsvFormatter implements ItemFormatter<String> {

	@Override
	public String format(String item) {
		return "\""+item+"\"";
	}

}
