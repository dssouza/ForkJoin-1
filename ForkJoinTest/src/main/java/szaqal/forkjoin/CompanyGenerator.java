package szaqal.forkjoin;

import java.util.List;

/**
 * @author malczyk.pawel@gmail.com
 *
 */
public class CompanyGenerator extends AbstractItemGenerator {
	
	private final String companyNames;
	
	private final String companyTypes;
	
	private static List<String> companyNameList;
	
	private static List<String> companyTypeList;
	
	
	public CompanyGenerator(String companyNames, String companyTypes) {
		this.companyNames = companyNames;
		this.companyTypes = companyTypes;
	}

	@Override
	public String generateItem() {
		
		if(companyNameList == null) {
			companyNameList = loadAll(companyNames);
		}
		
		if(companyTypeList == null) {
			companyTypeList = loadAll(companyTypes);
		}
		return randomGet(companyNameList) + " " + randomGet(companyTypeList);
	}

}
