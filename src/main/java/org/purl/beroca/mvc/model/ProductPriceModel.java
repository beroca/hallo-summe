package org.purl.beroca.mvc.model;

public interface ProductPriceModel {
	
	public boolean containsProductID( int productID );

	public String getProductName( int productID );

	public int getProductPricePerSqrMeter( int productID );
	
	public int getSum( int productID, int sizeInSqrMeter );
	
	public int size(); // return the number of elements in the model
}
