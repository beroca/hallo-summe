package org.purl.beroca.common;

public class Product {
	
	int productID;
	String productName;
	int productPricePerSqrMeter;
	
	public Product() {
		
		this.productID = 0;
		this.productName = null;
		this.productPricePerSqrMeter = 0;
	}

	public Product( final int pID, final String pName, final int pPrice ) {
		
		this.productID = pID;
		this.productName = pName;
		this.productPricePerSqrMeter = pPrice;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPricePerSqrMeter() {
		return productPricePerSqrMeter;
	}

	public void setProductPricePerSqrMeter(int productPricePerSqrMeter) {
		this.productPricePerSqrMeter = productPricePerSqrMeter;
	}
}
