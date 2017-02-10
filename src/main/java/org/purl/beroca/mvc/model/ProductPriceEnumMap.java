package org.purl.beroca.mvc.model;

import java.util.EnumMap;

public class ProductPriceEnumMap implements ProductPriceModel {
	
	EnumMap<ProductPriceEnum, Integer> mapProductEnumToPrice;
	
	public ProductPriceEnumMap() {
		
		this.mapProductEnumToPrice = new EnumMap<ProductPriceEnum, Integer>(ProductPriceEnum.class);

		for( ProductPriceEnum p : ProductPriceEnum.values() ) {
			this.mapProductEnumToPrice.put(p, p.getProductPricePerSqrMeter());
		}
	}
	
	public boolean containsOrdinal( int productID ) {
		
		if( productID == 0 ) { // 0 is a reserved kay and it should not be considered
			return false;
		}
		else {
			for( ProductPriceEnum p : this.mapProductEnumToPrice.keySet() ) {
				if( p.ordinal() == productID ) {
					return true;
				}
			}
			return false;
		}
	}
	
	public String getProductName( int productID ) {
		// TODO: IndexOutOfBoundException
		
		if( this.containsOrdinal(productID) ) {
			if( this.mapProductEnumToPrice.containsKey( ( ProductPriceEnum.values()[productID] ) ) ) {
				return( ProductPriceEnum.values()[productID].toString() );
			}
		}
		return null;
	}

	public int getProductPricePerSqrMeter( int productID ) {
		// TODO: IndexOutOfBoundException

		if( this.containsOrdinal(productID) ) {
			if( this.mapProductEnumToPrice.containsKey( ( ProductPriceEnum.values()[productID] ) ) ) {
				return( this.mapProductEnumToPrice.get( ( ProductPriceEnum.values()[productID] ) ) );
			}	
		}
		return 0;
	}
	
	public int getSum( int productID, int sizeSqrMeter ) {
		
		return( this.getProductPricePerSqrMeter(productID) * sizeSqrMeter );
	}

	public int size() {
		
		return mapProductEnumToPrice.size();
	}

	public boolean containsProductID(int productID) {
		
		return this.containsOrdinal(productID);
	}
}
