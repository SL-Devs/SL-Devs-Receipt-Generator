
//import Buffer Reader - Reads text from a character-input stream, buffering characters so as to provide for the efficient reading of characters, arrays, and lines.
import java.io.BufferedReader;
// import File -  defines interfaces and classes for the Java virtual machine to access files, file attributes, and file systems
import java.io.File;
//import FileNotFoundExeception - This exception will be thrown by the FileInputStream, FileOutputStream, and RandomAccessFile constructors when a file with the specified pathname does not exist. 
import java.io.FileNotFoundException;
//import File Reader - Convenience class for reading character files.
import java.io.FileReader;
//import FileWriter - Convenience class for writing character files.
import java.io.FileWriter;
//import IOException - Signals that an I/O exception of some sort has occurred.
import java.io.IOException;
//import SimpleDateFormat - SimpleDateFormat is a concrete class for formatting and parsing dates in a locale-sensitive manner.
import java.text.SimpleDateFormat;
//import ArrayList - Resizable-array implementation of the List interface. Implements all optional list operations, and permits all elements, including null.
import java.util.ArrayList;
//import Date - It allowed the interpretation of dates as year, month, day, hour, minute, and second values.
import java.util.Date;
//import InputMismatchException - Thrown by a Scanner to indicate that the token retrieved does not match the pattern for the expected type, or that the token is out of range for the expected type.
import java.util.InputMismatchException;
//import Scanner -  A simple text scanner which can parse primitive types and strings using regular expressions.
import java.util.Scanner;

public class ReceiptGenerator {
  // Main method
  public static void main(String[] args) throws FileNotFoundException, IOException {

    Date myDate = new Date(); // Create object of Date
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy"); // Set date to format and assign it to SimpleDateFormat
    String myDateString = sdf.format(myDate); // Format date to string and assign it to myDateString

    SimpleDateFormat sdfDay = new SimpleDateFormat("E"); // Set day to format. E is for the name of the week
    String myDayString = sdfDay.format(myDate);// Format date to string and pass it to myDayString variable

    SimpleDateFormat sdfHour = new SimpleDateFormat("hh:mm aa"); // Set hour format
    String myHourString = sdfHour.format(myDate); // Format hour to string and pass it to myHourString variable

    SimpleDateFormat sdfHourr = new SimpleDateFormat("hh-mm-ss aa"); // Set hour format //this one is seperated for the
                                                                     // purpose of txt file or for the receipt name
    String myHourStringForFile = sdfHourr.format(myDate); // Format hour to string and pass it to myHourStringForFile
                                                          // variable

    ArrayList<String> itemList = new ArrayList<>(); // Create an ArrayList object for itemList
    ArrayList<String> itemListToDisplay = new ArrayList<>(); // Create an ArrayList object for itemListToDisplay
    ArrayList<Double> priceList = new ArrayList<>(); // Create an ArrayList object for priceList
    ArrayList<Double> currentPriceList = new ArrayList<>(); // Create an ArrayList for currentPriceList
    ArrayList<Integer> quantityList = new ArrayList<>(); // Create an ArrayList object for quantityList
    /*
     * Creates a buffering character-input stream that uses a default-sized input
     * buffer. And Creates a new FileReader, given the name of the file to read and
     * assign to a variable called iL
     */
    BufferedReader iL = new BufferedReader(new FileReader("itemList.txt"));
    /*
     * Creates a buffering character-input stream that uses a default-sized input
     * buffer. And Creates a new FileReader, given the name of the file to read and
     * assign to a variable called pL
     */
    BufferedReader pL = new BufferedReader(new FileReader("priceList.txt"));

    String nameOfReceipt = myDateString + " " + myHourStringForFile + ".txt"; // Adds myDateString to a txt file and
                                                                              // assign it to nameOfReceipt

    /*
     * Creates a new File instance by converting the given pathname string into an
     * abstract pathname
     */
    File file = new File(nameOfReceipt);
    // Constructs a FileWriter given the File to write
    FileWriter fw = new FileWriter(file);

    /*
     * Constructs a new Scanner that produces values scanned from the specified
     * input stream
     */
    Scanner s = new Scanner(System.in);
    char responseYN = 0; // Initialize variable(responseYn) in char
    String lineiL, linepL; // Initialize variables(LineiL, linepL) in String
    double payment, change; // Initialize variables(payment, change) in double
    double total = 0; // Initialize variable(total) in double
    int ID; // Initialize variable(ID) in int
    int quantity; // Initialize variable(quantity) in int
    // Prints this text for console UI
    System.out.println("==================== WELCOME TO ALYSHA'S BAKESHOP ====================\n");

    System.out.println("Day: " + myDayString); // Prints the current Day
    System.out.println("Date: " + myDateString); // Prints the current Date
    System.out.println("Time: " + myHourString + "\n"); // Prints the current Hour

    System.out.println("|     ID     |\t\t| BREAD NAME |\t\t\t| PRICE |"); // Prints for console UI

    /*
     * While assign iL(BufferReader) to lineiL(String) and reads the line of
     * text(readLine) where it is not equal to null
     */
    while ((lineiL = iL.readLine()) != null) {
      // Appends the specified element to the end of the priceList
      itemListToDisplay.add(lineiL);
    }
    /*
     * While assign pL(BufferReader) to linepL(String) and reads the line of
     * text(readLine) where it is not equal to null
     */
    while ((linepL = pL.readLine()) != null) {
      /*
       * Appends the specified element to the end of the priceList and Double class
       * wraps a value of the primitive type double in an object.
       */
      priceList.add(Double.parseDouble(linepL));
    }

    iL.close(); // Closes the stream and releases any system resources associated with it.
    pL.close(); // Closes the stream and releases any system resources associated with it.
    try { // try to test this block of code

      // 2d Array
      for (int i = 0; i <= itemListToDisplay.size(); i++) { // For loop the number of elements in the itemList
        for (int e = 0; e <= priceList.size(); e++) { // For Loop the number of elements in the priceList
          System.out.println(""); // Add a new line

          // \t - for tab spacing
          // Prints and returns(.get) the element/item at the specified position of these
          // lists.
          System.out.println(e + "\t\t\t  " + itemListToDisplay.get(e) + "\t\t\t  " + priceList.get(e));
        }
      }
    } catch (IndexOutOfBoundsException a) { // Catch any exceptions if possible
    }

    try { // Try to test this block of code
      // Loop for getting the order
      do { // This is a do while loop
        System.out.print("ID: "); // Prints this text
        ID = s.nextInt(); // Read user input
        System.out.println(" "); // Add a new line

        itemList.add(itemListToDisplay.get(ID)); /*
                                                  * Appends(.add) the specify element/item to the end of itemList and
                                                  * returns(.get) the specified position in itemListToDsiplay
                                                  */

        currentPriceList.add(priceList.get(ID));/*
                                                 * Appends(.add) the specify element/item to the end of currentPriceList
                                                 * and returns(.get) the specified position in priceList
                                                 */
        System.out.print("Quantity: "); // Prints this text
        quantity = s.nextInt(); // Read user input
        quantityList.add(quantity); // Appends the specified element/item to the end of quantityList
        System.out.println(" "); // Add a new line

        System.out.print("Add more orders? Y - yes/ Any key - no: "); // prints this text
        responseYN = s.next().charAt(0); // Read user input and return the char at the specific index in a string
        System.out.println(" "); // Add a new line

        // Both y and Y are accepted in user input simply it's not case-sensitive
      } while (responseYN == 'y' || responseYN == 'Y');
    } catch (InputMismatchException k) { // Catch a input mismatch by a user
      // And perform this command after mismatch
      System.out.println("Invalid Input");
      System.out.println("Please try again...");
      System.exit(0);
    } catch (IndexOutOfBoundsException e) { // Catch a exception which is about the limit of ID by a user
      // And perform this command after exception
      System.out.println("ID doesn't exist");
      System.out.println("Please try again...");
      System.exit(0);
    }
    try { // Try to test this block of code
      iL.close(); // Closes the stream and releases any system resources associated with it.
                  // Prints this text for console UI
      System.out.println("-------------------------Summary-------------------------");

      System.out.println("Day: " + myDayString); // Prints Day
      System.out.println("Date: " + myDateString); // Prints Date
      System.out.println("Time: " + myHourString); // Prints Time
      System.out.println("\n"); // This is a line break

      // Prints this text for console UI
      System.out.println("| Bread Name |\t\t| Price |\t| Quantity|\t| Total |");

      fw.write("Day: " + myDayString); // Writes Day in a text file
      fw.write("\nDate: " + myDateString); // Writes Date in a text file
      fw.write("\nTime: " + myHourString); // Writes Day in a text file

      // Prints this text for console UI
      fw.write("\n\n---------------------Receipt----------------------------\n");
      // Prints this text for console UI
      fw.write("| Bread Name |\t\t| Price |\t\t| Quantity |\t\t|  Total |");

      total = 0; // Initialize total to 0
      // 2d Array
      for (int i = 0; i <= itemList.size(); i++) { // for loop the number of elements in the itemList
        for (int e = 0; e <= quantityList.size(); e++) { // for loop the number of elements in the quantityList
          /*
           * adds(+) the total of specified elements in the quantityList times(x) the
           * currentPriceList and assign(=) it to the total variable
           */
          total += quantityList.get(e) * currentPriceList.get(e);

          /*
           * Writes the string of itemList, currentPriceList, and quantityList x
           * currentPriceList to a txt file
           */
          fw.write("\n" + itemList.get(e) + " \t\t\t\t  " + currentPriceList.get(e) + "\t\t\t\t\t  "
              + quantityList.get(e) + " \t\t\t\t\t " + (quantityList.get(e) * currentPriceList.get(e)));

          System.out.println(" "); // Add a new line

          /*
           * Prints the string of of itemList, currentPriceList, and quantityList x
           * currentPriceList to the console
           */
          System.out.println(itemList.get(e) + "\t\t  " + currentPriceList.get(e) + "\t\t    " + quantityList.get(e)
              + "  \t\t  " + (quantityList.get(e) * currentPriceList.get(e)));
        }
      }
    } catch (IndexOutOfBoundsException e) { // Catch exception if possible

    }

    System.out.println("\nTotal: " + total); // Prints the total

    // Prints for console UI
    System.out.println("-------------------------------------------------------");

    System.out.print("Payment: "); // Prints this text
    payment = s.nextDouble(); // Initialized payment to double
    change = payment - total; // Subtract payment to total and assign it to variable change
    if (change < 0) {
      System.out.println("Warning: Payment is inadequate!!!");

    }
    System.out.println(" "); // Added new line

    System.out.print("Change: " + change); // Prints change
    System.out.println("\nThank you for purchasing our product! "); // prints this text for Console UI
    System.out.println("Please come again! "); // Prints this text for Console UI
    fw.write("\nTotal: " + String.valueOf(total)); // Converts the value of total to String and writes it in a txt file

    // Writes this text for Console UI in a txt file
    fw.write("\n----------------------------------------------------------");

    // Converts the value of payment to String and writes it in a txt file
    fw.write("\nPayment: " + String.valueOf(payment));

    if (change < 0) {
      // Writes this text for Console UI in a txt file
      fw.write("\nWarning: Payment is inadequate!!!");
    }

    // Converts the value of change to String and writes it in a txt file
    fw.write("\nChange: " + String.valueOf(change));

    // note: "\n" = new line & "\t" for tab spacing
    fw.write("\n\n\t\t\t\t\t\tThank you for purchasing our product!"); // Writes this text in a txt file
    fw.write("\n\t\t\t\t\t\t\t Please come again!");// Writes this text in a txt file
    fw.close(); // Closed the writing in a txt file
  }
}
