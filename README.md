# Exercise: Calculate Sum
**View on GitHub**: <https://github.com/beroca/hallo-summe.git>

## Data:

2 Products with different Price per m^2

* Compact: 650€ per m^2
* Optimal: 700€ per m^2

## Input:

* Product: "Compact" or "Optimal"
* Size in m^2

## Output:

* Total Cost Sum

# Development (in-progress)

Project structure.

```
./hallo-summe
$ tree
.
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   ├── java
│   │   │   └── org
│   │   │       └── purl
│   │   │           └── beroca
│   │   │               ├── common
│   │   │               │   ├── MetaData.java                   // Version Ctrl
│   │   │               │   └── Product.java
│   │   │               ├── mvc                                 // MVC Pattern
│   │   │               │   ├── controller
│   │   │               │   │   └── Controller.java
│   │   │               │   ├── HalloSumme.java                 // MVC main()
│   │   │               │   ├── model
│   │   │               │   │   ├── ProductPriceEnum.java       // Model 1
│   │   │               │   │   ├── ProductPriceEnumMap.java    // Model 2
│   │   │               │   │   ├── ProductPriceModel.java      // Interface
│   │   │               │   │   └── ProductPriceSortedMap.java  // Model 3
│   │   │               │   └── view
│   │   │               │       └── ProductPriceView.java
│---│---│---------------│------------------------------------------------------
│   │   │               └── mvp
│   │   │                   └── MinimumViableProduct.java       // MVP main()
│   │   └── resources
│   │       └── CatalogProductPrice.csv
│   └── test
│       ├── java
│       │   └── org
│       │       └── purl
│       │           └── beroca
│       │               └── mvc
│       │                   └── model
│       │                   │   └── ProductPriceEnumTest.java
│       │                   └── view
│       └── resources
└── target
```

The project includes 2 approaches to handle the exercise:

* A "minimum viable product" (MVP) approach (.mvp package).
  * The class `MinimumViableProduct` provides the main() entry point.
  * A direct solution to the exercise.
  * Everything is implemented in 2 classes.
  * Very customized but not optimal for reuse, extendibility, or software maintenance.

* An adapted version of the "model view controller" (MVC) pattern (.mvc package)
  * The class `HalloSumme` provides the main() entry point.
  * The MVC approach also provides multiple Model classes (i.e., Enum, EnumMap, SortedMap).
  * Each Model class provides a different implementation to the solution requested.
  * The Controller class uses polymorphism to instantiate every Model class at run-time.

## View

Example of the View class (`ProductPriceView`) on the Console:

```
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
```

* One execution of the application produces the following sequence of calls to the View class:
  * View.open()
  * View.show( Controller )
  * View.requestInput( Controller )
  * View.responseOutput( Controller )
  * View.close()

## Features

* Adapted MVC pattern for Console.
* Use multiple Model classes for multiple implementations of the same solution.
  * Strategy design pattern.
  * Polymorphism of Model classes.

## Improvements - Next Steps
* !!! Complete ALL JUnit tests !!!

* Support decimal numbers for apartment size in m^2 (?)
* Review use of exceptions.
* View class: decouple error messages from user input/interaction messages.
* Move `new Scanner(System.in);` inside a try{}/catch{} block
* Code repetition: `validateProductID`, `validateSizeInSqrMeter`
  * Move to .common package and reuse implementation on both main() methods (MVC and MVP).

* Special case when productID = 0
* Use of `_NULL_` element.
  * Functional dependency between Product ID and enum.ordinal() value.

* Init of data possible extensions:
  * File (eg. csv).
  * User input (eg. via console).

* Support CLI interface for App.
  * Example: HalloSumme --product 1 --size 50 --verbose  
  * Where should the input params `String[] args` be validated ?
  * HalloSumme.java or Controller.java (?)
  * The Controller has a handler to the Model so it can infer if the input params are valid.   

* Add LOGGER facility.
