package szaqal.forkjoin;

import java.util.List;

/**
 * @author malczyk.pawel@gmail.com
 *
 */
public final class MalePersonGenerator extends AbstractItemGenerator  {
	
	private final String firstNames;
	
	private final String lastNames;
	
	private static List<String> firstNameList;
	
	private static List<String> lastNameList;

	public MalePersonGenerator(String firstNames, String lastNames) {
		this.firstNames = firstNames;
		this.lastNames = lastNames;
	}

	@Override
	public String generateItem() {
		if(firstNameList == null) {
			firstNameList = loadAll(firstNames);
		}
		
		if(lastNameList == null) {
			lastNameList = loadAll(lastNames);
		}
		
		int fNameidx = RANDOM.nextInt(firstNameList.size());
		int lNameIdx = RANDOM.nextInt(lastNameList.size());
		
		return firstNameList.get(fNameidx) + " " + lastNameList.get(lNameIdx);
	}
	
}
