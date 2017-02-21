package org.purl.beroca.mvp;

import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

import org.purl.beroca.common.MetaData;
import org.purl.beroca.common.Product;

public class MinimumViableProduct {

	SortedMap<Integer, Product> mapIDToProduct;
	int productID = 0;
	int sizeInSqrMeter = 0;
	
	public MinimumViableProduct() {
		
		mapIDToProduct = new TreeMap<>();		
		mapIDToProduct.put(1, new Product(1, "COMPACT", 650));
		mapIDToProduct.put(2, new Product(2, "OPTIMAL", 700));
		// mapIDToProduct.put(3, new Product(3, "PREMIUM", 750));
	}
	
	public static void main(String[] args) {
		
		MinimumViableProduct mvp = new MinimumViableProduct();
		
		int sum = 0;

		System.out.println("=====================================================");
		System.out.println("# HalloSumme - Minimum Viable Product " + MetaData.V_170221.getVERSION());
		System.out.println("=====================================================");
		System.out.println("+ Implementation:");
		System.out.println("+ Minimum Viable Product");
		System.out.println("# List of Products:");
		System.out.println("----------------------------------------------------");
		
		for (Product p : mvp.mapIDToProduct.values() ) {
			System.out.println( p.getProductID() + ": " + 
					p.getProductName() + ": " + 
					p.getProductPricePerSqrMeter() + " €/m^2" );
		}
		System.out.println("----------------------------------------------------");

		Scanner stdin = null;
		try {
			stdin = new Scanner(System.in);
			if (stdin != null) {
				
				mvp.validateProductID(stdin);
				mvp.validateSizeInSqrMeter(stdin);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			if (stdin != null) {
				stdin.close();
			}
		}
		
		if( mvp.mapIDToProduct.containsKey(mvp.productID) ) {
			final String name = mvp.mapIDToProduct.get(mvp.productID).getProductName();
			final int price = mvp.mapIDToProduct.get(mvp.productID).getProductPricePerSqrMeter();
			sum = price * mvp.sizeInSqrMeter;
			
			System.out.println("+ Product: " + name);
			System.out.println("+ Price: " + price + " €/m^2");
			System.out.println("+ Size: " + mvp.sizeInSqrMeter + " m^2");
			System.out.println("+ Total Insurance Sum: " + sum + " €");
		}
		else {
			System.out.println("- Error: " + mvp.productID + " is an INVALID Product Number");
		}
		System.out.println("=====================================================\n");
	}
	
	private void validateProductID(Scanner stdin) {
		
		System.out.print("# Please, enter a VALID Product Number: \n> ");

		boolean validInt = false; 	// Control if the user input is a VALID Product ID

		while (validInt == false) {

			while (!stdin.hasNextInt()) {
				// The next token on the stdin is NOT an Integer
				// Discard and scan next token
				System.out.println("- Error: INVALID Product Number");
				System.out.print("# Please, enter a VALID Product Number: \n> ");
				stdin.next();
			}

			// The token on the stdin IS an Integer 
			int input = stdin.nextInt(); 
			
			if( this.mapIDToProduct.containsKey(input) ) {
				// The token read IS an Integer AND
				// It is a VALID Product ID.
				// Pass token to the Controller AND
				// Exit the loop.
				this.productID = input;
				validInt = true;
			} else {
				// The token on the stdin IS an Integer BUT
				// It is NOT a VALID Product ID
				// Discard and scan the next token
				System.out.println("- Error: " + input + " is an INVALID Product Number");
				System.out.print("# Please, enter a VALID Product Number: \n> ");
			}
		}
	}
	
	private void validateSizeInSqrMeter(Scanner stdin) {
		
		System.out.println("# Please, enter Apartment size in m^2 (no decimals): ");
		System.out.print("> ");
		while (!stdin.hasNextInt()) {
			// The next token on the stdin is NOT an Integer
			// Discard and scan next token
			System.out.println("- Error: INVALID Apartement size");
			System.out.println("# Please, enter Apartment size in m^2 (no decimals): ");
			System.out.print("> ");
	        stdin.next();
	    }

		// The token read IS an Integer 
		// Pass token to the Controller 
		int input = stdin.nextInt();
		this.sizeInSqrMeter = input;
	}
}
