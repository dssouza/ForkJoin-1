package szaqal.forkjoin.formatters;

/**
 * @author malczyk.pawel@gmail.com
 *
 */
public class PlainFormatter implements ItemFormatter<String> {

	@Override
	public String format(String item) {
		return item;
	}
}
