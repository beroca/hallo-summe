package org.purl.beroca.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.purl.beroca.mvc.model.ProductPriceEnum;
import org.purl.beroca.mvc.model.ProductPriceEnumMap;
import org.purl.beroca.mvc.model.ProductPriceModel;
import org.purl.beroca.mvc.model.ProductPriceSortedMap;
import org.purl.beroca.mvc.view.ProductPriceView;

public class Controller {

	ProductPriceModel model;
	ProductPriceView view;
	
	// Input params
	int productID = 0;  		// 1: compact
								// 2: optimal
	int sizeInSqrMeter = 0;
	
	// Output params
	int sum = 0;
	
	public Controller() {
		
		this.view = new ProductPriceView();
		this.model = ProductPriceEnum._NULL_;
	}

	public Controller( int productID, int sizeInSqrMeter ) {
		
		this.view = new ProductPriceView();
		this.model = ProductPriceEnum._NULL_;
		
		this.productID = productID;
		this.sizeInSqrMeter = sizeInSqrMeter;
	}

	public ProductPriceModel getModel() {
		return this.model;
	}
	
	public int getProductID() {
		return productID;
	}

	public int getSizeInSqrMeter() {
		return sizeInSqrMeter;
	}

	public int getSum() {
		return this.sum;
	}

	public void run() {

		// Initialize ArrayList with all Models that implements the HalloVersicherung  
		List<ProductPriceModel> listOfModels = new ArrayList<>();
		listOfModels.add(ProductPriceEnum._NULL_);
		listOfModels.add(new ProductPriceEnumMap());
		listOfModels.add(new ProductPriceSortedMap());
		
		this.view.open();
		
		for (ProductPriceModel model : listOfModels) { // for each implementation model
			this.model = model;
			this.view.show(this);
			this.view.requestInput(this); // should throw Exception (?)
			this.sum = this.model.getSum(this.productID, this.sizeInSqrMeter);
			this.view.responseOutput(this);
		}
		this.view.close();
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public void setSizeInSqrMeter(int sizeInSqrMeter) {
		this.sizeInSqrMeter = sizeInSqrMeter;
	}
}
