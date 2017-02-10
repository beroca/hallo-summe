package org.purl.beroca.mvc.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProductPriceEnumTest {

	ProductPriceEnum testOfProductPriceEnum = ProductPriceEnum._NULL_;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testContainsOrdinal() {
		
		int size = testOfProductPriceEnum.size();
		
		// Positive tests
		assertEquals("", true, testOfProductPriceEnum.containsOrdinal(0));
		assertEquals("", true, testOfProductPriceEnum.containsOrdinal(1));
		assertEquals("", true, testOfProductPriceEnum.containsOrdinal(2));
		assertEquals("", true, testOfProductPriceEnum.containsOrdinal(size-1));

		// Negative tests
		assertEquals("", false, testOfProductPriceEnum.containsOrdinal(-1));
		assertEquals("", false, testOfProductPriceEnum.containsOrdinal(3));
		assertEquals("", false, testOfProductPriceEnum.containsOrdinal(size));
		assertEquals("", false, testOfProductPriceEnum.containsOrdinal(size+1));
		assertEquals("", false, testOfProductPriceEnum.containsOrdinal(size+10));
	}

	@Test
	public void testGetProductName() {

		int size = testOfProductPriceEnum.size();

		// Positive tests
		assertEquals("", "_NULL_", testOfProductPriceEnum.getProductName(0));
		assertEquals("", "COMPACT", testOfProductPriceEnum.getProductName(1));
		assertEquals("", "OPTIMAL", testOfProductPriceEnum.getProductName(2));
		assertEquals("", "OPTIMAL", testOfProductPriceEnum.getProductName(size-1));
		
		// Negative tests
		assertEquals("", null, testOfProductPriceEnum.getProductName(-1));
		assertEquals("", null, testOfProductPriceEnum.getProductName(3));
		assertEquals("", null, testOfProductPriceEnum.getProductName(size));
		assertEquals("", null, testOfProductPriceEnum.getProductName(size+1));
		assertEquals("", null, testOfProductPriceEnum.getProductName(size+10));
	}

	@Test
	public void testGetProductPricePerSqrMeterInt() {
		
		// Iterate thru many Enum index values: 
		// - inbound (correct) and 
		// - out of bound (incorrect)
		for( int i = -10; i < (ProductPriceEnum.values().length + 10); i++ ) {

			// Check if the index of the Enum is inbound (correct)
			if( testOfProductPriceEnum.containsOrdinal(i) ) {
				
				// The index of the Enum is correct
				switch( ProductPriceEnum.values()[i] ) {
				case _NULL_:
					assertEquals("", 0, testOfProductPriceEnum.getProductPricePerSqrMeter(i));
					break;
				case COMPACT:
					assertEquals("", 650, testOfProductPriceEnum.getProductPricePerSqrMeter(i));
					break;
				case OPTIMAL:
					assertEquals("", 700, testOfProductPriceEnum.getProductPricePerSqrMeter(i));
					break;
				default: 
					// Code path should never get here
					fail();
				}	
			}
			else {
				// For an Enum index that is out of bound the expected return is always 0
				// Negative Test
				assertEquals("", 0, testOfProductPriceEnum.getProductPricePerSqrMeter(i));
			}
		}
	}
		
	@Test
	public void testGetSumInt() {
		
		int sizeInSqrMeter = 10;
		
		// Option 1:
		// Only Enum Types that are defined (correct) are accessed
		for( ProductPriceEnum e : ProductPriceEnum.values() ) {
			
			// Positive Tests
			switch(e) {
			case _NULL_:
				assertEquals("", 0, e.getSum(ProductPriceEnum._NULL_.ordinal(), sizeInSqrMeter));
				break;
			case COMPACT:
				assertEquals("", 6500, e.getSum(ProductPriceEnum.COMPACT.ordinal(), sizeInSqrMeter));
				break;
			case OPTIMAL:
				assertEquals("", 7000, e.getSum(ProductPriceEnum.OPTIMAL.ordinal(), sizeInSqrMeter));
				break;
			default: 
				// Code path should never get here
				fail();
			}	
		}
		
		// Option 2:
		// Iterate thru many Enum index values: 
		// - inbound (correct) and 
		// - out of bound (incorrect)
		for( int i = -10; i < (ProductPriceEnum.values().length + 10); i++ ) {

			// Check if the index of the Enum is inbound (correct)
			if( testOfProductPriceEnum.containsOrdinal(i) ) {
				
				// The index of the Enum is correct
				switch( ProductPriceEnum.values()[i] ) {
				case _NULL_:
					assertEquals("", 0, testOfProductPriceEnum.getSum(i, sizeInSqrMeter));
					break;
				case COMPACT:
					assertEquals("", 6500, testOfProductPriceEnum.getSum(i, sizeInSqrMeter));
					break;
				case OPTIMAL:
					assertEquals("", 7000, testOfProductPriceEnum.getSum(i, sizeInSqrMeter));
					break;
				default: 
					// Code path should never get here
					fail();
				}	
			}
			else {
				// For an Enum index that is out of bound the expected return is always 0   
				assertEquals("", 0, testOfProductPriceEnum.getSum(i, sizeInSqrMeter));
			}
		}
	}
}
