///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            p2
// Files:            DLinkedList, InsufficientCreditException, ListADT,
//					 Listnode, Product, User
// Semester:         CS302 Spring 2015
//
// Author:           Adam Converse
// Email:            acconverse@wisc.edu
// CS Login:         adam
// Lecturer's Name:  Jim Skrentny
// Lab Section:      105
//
// Pair Partner:     Brandon Lapp
// Email:            blapp@wisc.edu
// CS Login:         brendon
// Lecturer's Name:  Jim Skrentny
// Lab Section:      105

//package p2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main class that runs the amazon store.  Handles user input and validation.
 *
 */
public class AmazonStore {

	//Store record of users and products
	private static ListADT<Product> products = new DLinkedList<Product>();
	private static ListADT<User> users = new DLinkedList<User>();
	private static User currentUser = null;//current user logged in

	//scanner for console input
	public static final Scanner stdin= new Scanner(System.in);


	//main method
	public static void main(String args[]) {


		//Populate the two lists using the input files: Products.txt User1.txt 
		//User2.txt ... UserN.txt
		if (args.length < 2) {
			System.out.println("Usage: java AmazonStore [PRODUCT_FILE] "
					+ "[USER1_FILE] [USER2_FILE] ...");
			System.exit(0);
		}

		//load store products
		loadProducts(args[0]);
		
		//load users one file at a time
		for(int i=1; i<args.length; i++)
			loadUser(args[i]);

		//User Input for login
		boolean done = false;
		while (!done) 
		{
			System.out.print("Enter username : ");
			String username = stdin.nextLine();
			System.out.print("Enter password : ");
			String passwd = stdin.nextLine();

			if(login(username, passwd)!=null)
			{
				//generate random items in stock based on this user's wish list
				ListADT<Product> inStock=currentUser.generateStock();
				//show user menu
				userMenu(inStock);
			}
			else
				System.out.println("Incorrect username or password");

			System.out.println("Enter 'exit' to exit program or anything else "
					+ "to go back to login");
			if(stdin.nextLine().equals("exit"))
				done = true;
		}

	}

	/**
	 * Tries to login for the given credentials. Updates the currentUser if 
	 * successful login
	 * 
	 * @param username name of user
	 * @param passwd password of user
	 * @returns the currentUser 
	 */
	public static User login(String username, String passwd){
		for(int i = 0; i < users.size(); i++){
			if(users.get(i).checkLogin(username, passwd)){
				currentUser = users.get(i);
				return currentUser;
			}
		}
		return null;
	}

	/**
	 * Reads the specified file to create and load products into the store.
	 * Every line in the file has the format: <NAME>#<CATEGORY>#<PRICE>#<RATING>
	 * Create new products based on the attributes specified in each line and 
	 * insert them into the products list
	 * Order of products list should be the same as the products in the file
	 * For any problem in reading the file print: 'Error: Cannot access file'
	 * 
	 * @param fileName name of the file to read
	 */
	public static void loadProducts(String fileName){
		Scanner text = null;
		try {
			//creates the file 
			File inFile = new File(fileName);
			//creates scanner for the file
			text = new Scanner(inFile);

		} catch (FileNotFoundException e) {
			//catches the bad file and exits program accordingly
			System.out.println("Error: Cannot access file");
			return;
		}
		while(text.hasNextLine()){
		String str = text.nextLine();
		String[] strArray = str.split("#");
		products.add(new Product(strArray[0],strArray[1],
				Integer.parseInt(strArray[2]),Float.parseFloat(strArray[3])));
		}
		
		
		
	}

	/**
	 * Reads the specified file to create and load a user into the store.
	 * The first line in the file has the format:<NAME>#<PASSWORD>#<CREDIT>
	 * Every other line after that is a name of a product in the user's 
	 * wishlist, format:<NAME>
	 * For any problem in reading the file print: 'Error: Cannot access file'
	 * 
	 * @param fileName name of the file to read
	 */
	public static void loadUser(String fileName){
		Scanner text = null;
		try {
			//creates the file 
			File inFile = new File(fileName);
			//creates scanner for the file
			text = new Scanner(inFile);

		} catch (FileNotFoundException e) {
			//catches the bad file and exits program accordingly
			System.out.println("Error: Cannot access file");
			return;
		}
		
		String str = text.nextLine();
		
		String[] strArray = str.split("#");
		currentUser = new User(strArray[0],strArray[1],Integer.
				parseInt(strArray[2]));
		users.add(currentUser);
		/*
		str = text.nextLine();
		strArray = str.split("#");
		products.add(new Product(strArray[0],strArray[1],
				Integer.parseInt(strArray[2]),Float.parseFloat(strArray[3])));
		*/
		String productName;
		while(text.hasNextLine()){
			productName = text.nextLine();
			for(int i = 0; i < products.size(); i++){
			if(products.get(i).getName().equals(productName)){
				currentUser.addToWishList(products.get(i));
			}
			}
			
		}

		//currentUser = null;
	}

	/**
	 * See sample outputs
     * Prints the entire store inventory formatted by category
     * The input text file for products is already grouped by category, use the
     *  same order as given in the text file 
     * format:
     * <CATEGORY1>
     * <NAME> [Price:$<PRICE> Rating:<RATING> stars]
     * ...
     * <NAME> [Price:$<PRICE> Rating:<RATING> stars]
     * 
     * <CATEGORY2>
     * <NAME> [Price:$<PRICE> Rating:<RATING> stars]
     * ...
     * <NAME> [Price:$<PRICE> Rating:<RATING> stars]
     */
	public static void printByCategory(){
		String category = null;
		for(int i = 0; i < products.size(); i++){
			if(!(products.get(i).getCategory().equals(category))){
				System.out.println();
				System.out.println(products.get(i).getCategory());
				category = products.get(i).getCategory();
			}
			System.out.println(products.get(i).toString());
		}
	}

	
	/**
	 * Interacts with the user by processing commands
	 * 
	 * @param inStock list of products that are in stock
	 */
	public static void userMenu(ListADT<Product> inStock){

		boolean done = false;
		while (!done) 
		{
			System.out.print("Enter option : ");
			String input = stdin.nextLine();

			//only do something if the user enters at least one character
			if (input.length() > 0) 
			{
				String[] commands = input.split(":");
				if(commands[0].length()>1)
				{
					System.out.println("Invalid Command");
					continue;
				}
				if(commands.length > 2){
					System.out.println("Invalid Command");
					continue;
				}
				switch(commands[0].charAt(0)){
				case 'v':
					if(commands[1].equals("all")){
						printByCategory();
					}
					if(commands[1].equals("wishlist")){
						currentUser.printWishList(System.out);
					}
					if(commands[1].equals("instock")){
						for(int i = 0; i < inStock.size(); i++){
						System.out.println(inStock.get(i));
						}
					}
					break;

				case 's':
					String string = commands[1];
					for(int i=0; i<products.size(); i++){
						Product currProduct = products.get(i);
						if(currProduct.getName().contains(string) || 
								currProduct.getCategory().contains(string)){
							System.out.println(currProduct);
						}
					}
					break;

				case 'a':
					String product = commands[1];
					boolean notFound = true;
					for(int i=0; i<products.size(); i++){
						Product currProduct = products.get(i);
						if(currProduct.getName().equals(product)){
							currentUser.addToWishList(currProduct);
							notFound = false;
							System.out.println("Added to wishlist");
						} 
					}
					if(notFound){
						System.out.println("Product not found");
					}
					break;

				case 'r':
					String product1 = commands[1];
					boolean notFound1 = true;
					for(int i=0; i<products.size(); i++){
						Product currProduct = products.get(i);
						if(currProduct.getName().equals(product1)){
							currentUser.removeFromWishList(product1);
							notFound1 = false;
							System.out.println("Removed from wishlist");
						}
					}
					if(notFound1){
						System.out.println("Product not found");
					}
					break;

				case 'b':
						for(int i=0; i<inStock.size(); i++){
							String productName = inStock.get(i).getName();
							try{
								if(currentUser.buy(productName)){
									System.out.println("Bought " + productName);
								}
							} catch(InsufficientCreditException ic){
								System.out.println("Insufficient funds for " + 
							productName );
							}
						}
					break;

				case 'c':
					int credit = currentUser.getCredit();
					System.out.println("$"+credit);
					break;

				case 'l':
					done = true;
					System.out.println("Logged Out");
					break;

				default:  //a command with no argument
					System.out.println("Invalid Command");
					break;
				}
			}
		}
	}

}
