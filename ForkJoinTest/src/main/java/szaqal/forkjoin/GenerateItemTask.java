package szaqal.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * @author malczyk.pawel@gmail.com
 * 
 */
public class GenerateItemTask extends RecursiveTask<List<String>> {

	private static final long serialVersionUID = 8933537008111986053L;

	private int quantity;

	private ItemType itemType;

	private GenerateItemTask() {
		super();
	}

	public GenerateItemTask(int quantity, ItemType itemType) {
		this();
		this.quantity = quantity;
		this.itemType = itemType;
	}

	@Override
	protected List<String> compute() {
		List<String> result = new ArrayList<>();
		for (int i = 0; i < quantity; i++) {
			result.add(itemType.generateItem());
		}
		return result;
	}

}
