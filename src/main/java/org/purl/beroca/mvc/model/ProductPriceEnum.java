package org.purl.beroca.mvc.model;

public enum ProductPriceEnum implements ProductPriceModel {
	_NULL_(0),
	COMPACT(650), 
	OPTIMAL(700);
	// PREMIUM(750);

	private final int PRODUCT_PRICE_PER_SQR_METER;

	private ProductPriceEnum(int productPricePerSqrMeter) {
		this.PRODUCT_PRICE_PER_SQR_METER = productPricePerSqrMeter;
	}

	public boolean containsOrdinal( int productID ) {
		
		if( productID == 0 ) { 	// 0 is reserved and should not be considered
			return false;
		}
		else {
			for( ProductPriceEnum p : ProductPriceEnum.values() ) {
				if( p.ordinal() == productID ) {
					return true;
				}
			}
			return false;
		}
	}
	
	public int getProductID() {
		return this.ordinal();
	}

	public String getProductName( int productID ) {
		// TODO: IndexOutOfBoundException
		
		if( this.containsOrdinal(productID) ) {
			return ProductPriceEnum.values()[productID].toString();
		}
		return null;
	}

	public int getProductPricePerSqrMeter() {
		return this.PRODUCT_PRICE_PER_SQR_METER;
	}
	
	public int getProductPricePerSqrMeter( int productID ) {
		// TODO: IndexOutOfBoundException
		
		if( this.containsOrdinal(productID) ) {
			return values()[productID].getProductPricePerSqrMeter();
		}
		return 0;
	}
	
	public int getSum( int productID, int sizeInSqrMeter ) {
		// TODO: IndexOutOfBoundException
		
		if( this.containsOrdinal(productID) ) {
			return ( ( values()[productID].PRODUCT_PRICE_PER_SQR_METER ) * sizeInSqrMeter );
		}
		return 0;
	}

	public int size() {
		return  values().length;
	}

	public boolean containsProductID(int productID) {

		return this.containsOrdinal(productID);
	}
}
