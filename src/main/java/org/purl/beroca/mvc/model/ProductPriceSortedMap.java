package org.purl.beroca.mvc.model;

import java.util.SortedMap;
import java.util.TreeMap;

import org.purl.beroca.common.Product;

public class ProductPriceSortedMap implements ProductPriceModel {

	SortedMap<Integer, Product> mapIDToProduct;

	public ProductPriceSortedMap(SortedMap<Integer, Product> mapIDToProduct) {

		this.mapIDToProduct = mapIDToProduct;
	}

	public ProductPriceSortedMap() {

		this.mapIDToProduct = new TreeMap<>();

		this.mapIDToProduct.put(0, new Product(0, "_NULL_", 0)); 	// The Key =  0 of the Map is reserved ! 
		this.mapIDToProduct.put(1, new Product(1, "COMPACT", 650));
		this.mapIDToProduct.put(2, new Product(2, "OPTIMAL", 700));
		// this.mapIDToProduct.put(2, new Product(3, "PREMIUM", 750));
	}


	public boolean containsProductID(int productID) {

		if( productID == 0 ) { // 0 is a reserved key and should not be considered
			return false;
		}
		else {
			return this.mapIDToProduct.containsKey(productID);
		}
	}

	public String getProductName(int productID) {
		// TODO: IndexOutOfBoundException

		return( this.mapIDToProduct.containsKey(productID) ? 
				this.mapIDToProduct.get(productID).getProductName() : 
				null );
	}

	public int getProductPricePerSqrMeter(int productID) {
		// TODO: IndexOutOfBoundException

		return( this.mapIDToProduct.containsKey(productID) ?
				this.mapIDToProduct.get(productID).getProductPricePerSqrMeter() :
				0 );
	}

	public int getSum(int productID, int sizeSqrMeter) {

		return( this.getProductPricePerSqrMeter(productID) * sizeSqrMeter );
	}

	public int size() {

		return this.mapIDToProduct.size();
	}
}
