package szaqal.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import szaqal.forkjoin.enums.StringItemType;

/**
 * @author malczyk.pawel@gmail.com
 * 
 */
public class GenerateItemTask extends RecursiveTask<List<String>> {

	private static final long serialVersionUID = 8933537008111986053L;

	private int quantity;

	private StringItemType itemType;

	private GenerateItemTask() {
		super();
	}

	public GenerateItemTask(int quantity, StringItemType itemType) {
		this();
		this.quantity = quantity;
		this.itemType = itemType;
	}

	@Override
	protected List<String> compute() {
		System.out.println(String.format("Generating %s  items", quantity));
		List<String> result = new ArrayList<>();
		for (int i = 0; i < quantity; i++) {
			result.add((String)itemType.generateItem());
		}
		System.out.println("Task Done");
		return result;
	}

}
