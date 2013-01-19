package szaqal.forkjoin.generators;

import java.util.List;


/**
 * @author malczyk.pawel@gmail.com
 *
 */
public final class MalePersonGenerator extends AbstractItemGenerator<String>  {
	
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
		
		return randomGet(firstNameList) + " " + randomGet(lastNameList);
	}
	
}