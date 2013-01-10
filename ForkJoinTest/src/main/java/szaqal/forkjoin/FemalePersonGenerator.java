package szaqal.forkjoin;

/**
 * @author malczyk.pawel@gmail.com
 * 
 */
public class FemalePersonGenerator implements ItemGenerator {

	private final String firstNames;

	private final String lastNames;

	public FemalePersonGenerator(String firstNames, String lastNames) {
		this.firstNames = firstNames;
		this.lastNames = lastNames;
	}

	@Override
	public String generateItem() {
		return "Female";
	}

}
