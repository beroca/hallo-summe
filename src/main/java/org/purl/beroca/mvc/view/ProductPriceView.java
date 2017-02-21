package org.purl.beroca.mvc.view;

/* Example of 1 full iteration cycle of this View class on the Console:

====================================================
# HalloSumme - Model View Controller 17.02.09
====================================================
+ Implementation: 
+ ProductPriceEnum
# List of Products:
----------------------------------------------------
1: COMPACT: 650 €/m^2
2: OPTIMAL: 700 €/m^2
----------------------------------------------------
# Please, enter a VALID Product Number: 
> 1
# Please, enter Apartment size in m^2 (no decimals): 
> 1
+ Product: COMPACT
+ Price: 650 €/m^2
+ Size: 1 m^2
+ Total Insurance Sum: 650 €
====================================================

* The intended sequence of View method calls from the Controller is as follows:
* - View.open();
* - View.show( Controller );
* - View.requestInput( Controller );
* - View.responseOutput( Controller );
* - View.close();
*/
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.purl.beroca.common.MetaData;
import org.purl.beroca.mvc.controller.Controller;

public class ProductPriceView {

	// Remove View <=> Scanner dependency
	// Make InputStream of the Scanner AND
	//      OutputStream 
	// configurable via constructor
	//
	Scanner stdin;
	PrintStream stdout;
	
	// By default the View class uses:
	// - STDIN, and 
	// - STDOUT
	public ProductPriceView() {
		this.stdin = new Scanner(System.in);
		this.stdout = new PrintStream(System.out);
	}

	// Constructor to enable "dependency injection" of:
	// InputStream that the Scanner will use
	// OutputStream that the View class will use
	// For example:
	// - Human-end-user: console input/output (default)
	// - JUnit-test: customized strings
	public ProductPriceView(InputStream inputStream, PrintStream stdout) {
		this.stdin = new Scanner(inputStream);
		this.stdout = new PrintStream(System.out);
	}

	public void close() {
		// This View instance is no longer needed.
		// Therefore, close the Scanner.
		if (stdin != null) {
			stdin.close();
		}
	}
	
	public void open() {
		this.stdout.println("====================================================");
		this.stdout.println("# HalloSumme - Model View Controller " + MetaData.V_170221.getVERSION());
		this.stdout.println("====================================================");
	}
	
	public void requestInput( Controller c ) { //should throw Exception (?)
		
		try {
			if (stdin != null) {
				this.validateProductID(c);
				this.validateSizeInSqrMeter(c);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		// Do not close the Scanner yet.
		// This method is called by the Controller multiple times.
		// 1 time for each Model class that implements our solution.
		// If we close the System.in after 1 method call, subsequent calls will not be able to read from the STDIN 
//		finally {
//			if (stdin != null) {
//				stdin.close();
//			}
//		}
	}
	
	public void responseOutput( Controller c ) {
		final String name = c.getModel().getProductName( c.getProductID() );
		final int price = c.getModel().getProductPricePerSqrMeter( c.getProductID() );
		
		this.stdout.println("+ Product: " + name);
		this.stdout.println("+ Price: " + price + " €/m^2");
		this.stdout.println("+ Size: " + c.getSizeInSqrMeter() + " m^2");
		this.stdout.println("+ Total Insurance Sum: " + c.getSum() + " €");
		this.stdout.println("====================================================\n");
	}

	public void show( Controller c ) {

		this.stdout.println("+ Implementation: ");
		this.stdout.println("+ " + c.getModel().getClass().getSimpleName());		
		
		this.stdout.println("# List of Products:");
		this.stdout.println("----------------------------------------------------");

		for (int i = 1; i < c.getModel().size(); i++) { 
			// For each product in the model:
			// compose a human readable description in 1 line  
			final String name = c.getModel().getProductName(i);
			final int price = c.getModel().getProductPricePerSqrMeter(i);
			this.stdout.println( i + ": " + name + ": " + price + " €/m^2" );
		}
		this.stdout.println("----------------------------------------------------");
	}
	
	private void validateProductID( Controller c ) {
		
		this.stdout.print("# Please, enter a VALID Product Number: \n> ");

		boolean validInt = false; 	// Control if the user input is a VALID Product ID

		while (validInt == false) {

			while (!stdin.hasNextInt()) {
				// The next token on the stdin is NOT an Integer
				// Discard and scan next token
				this.stdout.println("- Error: INVALID Product Number");
				this.stdout.print("# Please, enter a VALID Product Number: \n> ");
				stdin.next();
			}

			// The token on the stdin IS an Integer 
			int input = stdin.nextInt(); 
			
			if (c.getModel().containsProductID(input)) {
				// The token read IS an Integer AND
				// It is a VALID Product ID.
				// Pass token to the Controller AND
				// Exit the loop.
				c.setProductID(input);
				validInt = true;
			} else {
				// The token on the stdin IS an Integer BUT
				// It is NOT a VALID Product ID
				// Discard and scan the next token
				this.stdout.println("- Error: " + input + " is an INVALID Product Number");
				this.stdout.print("# Please, enter a VALID Product Number: \n> ");
			}
		}
	}
	
	private void validateSizeInSqrMeter( Controller c ) {
		
		this.stdout.println("# Please, enter Apartment size in m^2 (no decimals): ");
		this.stdout.print("> ");
		while (!stdin.hasNextInt()) {
			// The next token on the stdin is NOT an Integer
			// Discard and scan next token
			this.stdout.println("- Error: INVALID Apartement size");
			this.stdout.println("# Please, enter Apartment size in m^2 (no decimals): ");
			this.stdout.print("> ");
	        stdin.next();
	    }

		// The token read IS an Integer 
		// Pass token to the Controller 
		int input = stdin.nextInt();
		c.setSizeInSqrMeter(input);
	}
}
