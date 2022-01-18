package hr.kresod.springbootingemark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import hr.kresod.springbootingemark.utils.BigDecimalConverter;

public class BigDecimalConverterTest {

	@Test
	void testBigDecimalConverter() {
		
		assertEquals(BigDecimalConverter.parseHnbString("7,56782"), new BigDecimal("7.56782"));
		assertEquals(BigDecimalConverter.parseHnbString("7,56"), new BigDecimal("7.56"));
		assertEquals(BigDecimalConverter.parseHnbString(null), null);
		assertNotEquals(BigDecimalConverter.parseHnbString("7.56783"), new BigDecimal("7.56782"));
		assertEquals(BigDecimalConverter.parseHnbString("21217,56782"), new BigDecimal("021217.56782"));
		
		
		
	}
	
}
