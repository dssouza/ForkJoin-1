package szaqal.forkjoin.formatters;

/**
 * @author malczyk.pawel@gmail.com
 *
 */
public class XmlFormatter implements ItemFormatter<String> {

	@Override
	public String format(String item) {
		return String.format("<item>%s</item>", item);
	}

}
