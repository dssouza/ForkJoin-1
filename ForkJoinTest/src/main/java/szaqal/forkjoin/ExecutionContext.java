package szaqal.forkjoin;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

import szaqal.forkjoin.formatters.ItemFormatter;

/**
 * @author malczyk.pawel@gmail.com
 *
 */
public final class ExecutionContext {
	
	private String type;
	
	private String fileName;
	
	private String qty;
	
	private String formatter;
	
	private ItemFormatter.TYPES formatterType;
	
	public static ExecutionContext build(CommandLine line) throws ParseException {
		String qty = line.getOptionValue("qty");
		if (qty == null) {
			throw new ParseException("Missing required parameter");
		}
		
		String type = line.getOptionValue("type");
		if (type == null) {
			throw new ParseException("Missing required parameter");
		}
		
		String fileName = line.getOptionValue("filename");
		if (fileName == null) {
			throw new ParseException("Missing required parameter");
		}

		String formatter = line.getOptionValue("format");
		ItemFormatter.TYPES formatterType = ItemFormatter.TYPES.PLAIN;
		if (formatter != null) {
			formatterType = ItemFormatter.TYPES.valueOf(formatter);
		}
		
		ExecutionContext context = new ExecutionContext();
		context.setFileName(fileName);
		context.setType(type);
		context.setQty(qty);
		context.setFormatter(formatter);
		context.setFormatterType(formatterType);
		return context;
	}
	
	public int getQuantity() {
		return Integer.valueOf(getQty());
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getQty() {
		return qty;
	}


	public void setQty(String qty) {
		this.qty = qty;
	}


	public String getFormatter() {
		return formatter;
	}


	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}


	public ItemFormatter.TYPES getFormatterType() {
		return formatterType;
	}


	public void setFormatterType(ItemFormatter.TYPES formatterType) {
		this.formatterType = formatterType;
	}
}
