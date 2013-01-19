package szaqal.forkjoin.enums;

import szaqal.forkjoin.itemgenerators.CompanyGenerator;
import szaqal.forkjoin.itemgenerators.FemalePersonGenerator;
import szaqal.forkjoin.itemgenerators.ItemGenerator;
import szaqal.forkjoin.itemgenerators.MalePersonGenerator;


/**
 * @author malczyk.pawel@gmail.com
 *
 */
public enum StringItemType implements ItemGenerator<String> {
	
	MALE_PERSON(new MalePersonGenerator("firstnames_male.txt","lastnames.txt")),
	FEMALE_PERSON(new FemalePersonGenerator("firstnames_female.txt", "lastnames.txt")),
	COMPANY(new CompanyGenerator("company_names.txt","company_types.txt"));
	
	private final ItemGenerator<String> generator;

	StringItemType(ItemGenerator<String> itemGenerator) {
		this.generator = itemGenerator;
	}

	@Override
	public String generateItem() {
		return generator.generateItem();
	}
}
