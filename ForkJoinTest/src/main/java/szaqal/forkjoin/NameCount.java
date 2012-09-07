package szaqal.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author malczyk.pawel@gmail.com
 *
 */
public class NameCount extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 5728221547947568730L;
	
	private final  Document document;
	
	private final String letter;
	

	public NameCount(Document document, String letter) {
		super();
		this.document = document;
		this.letter = letter;
	}


	@Override
	protected Integer compute() {
		int count = 0;
		for(String line:document.getLines()) {
			if(line.startsWith(letter)) {
				count ++;
			}
		}
//		System.out.println(letter + " count -> " + count);
		return count;
	}


	/**
	 * Getter method.
	 * @return the letter
	 */
	public String getLetter() {
		return letter;
	}

}
