package szaqal.forkjoin;

/**
 * @author malczyk.pawel@gmail.com
 *
 */
public final class MalePersonGenerator implements ItemGenerator {
	
	private final String firstNames;
	
	private final String lastNames;

	public MalePersonGenerator(String firstNames, String lastNames) {
		this.firstNames = firstNames;
		this.lastNames = lastNames;
	}

	@Override
	public String generateItem() {
		return "Male";
	}
	
}
