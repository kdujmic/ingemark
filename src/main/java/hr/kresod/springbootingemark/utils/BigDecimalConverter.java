package hr.kresod.springbootingemark.utils;



import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class BigDecimalConverter {
	
	public static BigDecimal parseHnbString(String inputString) {
		
		if(inputString == null) return null;
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(',');
		String pattern = "#.######";
		DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
		decimalFormat.setParseBigDecimal(true);

		// parse the string value
		BigDecimal parsedStringValue;
		try {
			parsedStringValue = (BigDecimal) decimalFormat.parse(inputString);
		} catch (ParseException e) {
			throw new  IllegalArgumentException(inputString); 
		}

	
		return parsedStringValue;
		
	}

}
