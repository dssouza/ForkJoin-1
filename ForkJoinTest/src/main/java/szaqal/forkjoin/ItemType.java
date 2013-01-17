package szaqal.forkjoin;

/**
 * @author malczyk.pawel@gmail.com
 *
 */
public enum ItemType implements ItemGenerator {
	
	MALE_PERSON(new MalePersonGenerator("firstnames_male.txt","lastnames.txt")),
	FEMALE_PERSON(new FemalePersonGenerator("firstnames_female.txt", "lastnames.txt")),
	COMPANY(new CompanyGenerator("company_names.txt","company_types.txt"));
	
	private ItemGenerator generator;

	ItemType(ItemGenerator itemGenerator) {
		this.generator = itemGenerator;
	}

	@Override
	public String generateItem() {
		return generator.generateItem();
	}
}
