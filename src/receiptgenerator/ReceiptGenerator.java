
package receiptgenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ReceiptGenerator {

  public static void main(String[] args) throws FileNotFoundException, IOException {
    Date myDate = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    String myDateString = sdf.format(myDate);

    ArrayList<String> itemList = new ArrayList<>();
    ArrayList<String> itemListToDisplay = new ArrayList<>();
    ArrayList<Double> priceList = new ArrayList<>();
    ArrayList<Double> currentPriceList = new ArrayList<>();
    ArrayList<Integer> quantityList = new ArrayList<>();
    BufferedReader iL = new BufferedReader(new FileReader("itemList.txt"));
    BufferedReader pL = new BufferedReader(new FileReader("priceList.txt"));

    String nameOfReceipt = myDateString + ".txt";
    File file = new File(nameOfReceipt);
    FileWriter fw = new FileWriter(file);

    Scanner s = new Scanner(System.in);
    char responseYN = 0;
    String lineiL, linepL;
    double payment, change;
    double total = 0;
    int ID;
    int quantity;
    System.out.println("==================== WELCOME TO ALYSHA'S BAKESHOP ====================\n");
    System.out.println("| ID/Bar Code |\t\t| BREAD NAME |\t\t\t| PRICE |");

    while ((lineiL = iL.readLine()) != null) {
      itemListToDisplay.add(lineiL);
    }
    while ((linepL = pL.readLine()) != null) {
      priceList.add(Double.parseDouble(linepL));
    }

    iL.close();
    pL.close();
    try {
      for (int i = 0; i <= itemListToDisplay.size(); i++) {
        for (int e = 0; e <= priceList.size(); e++) {
          System.out.println("");
          System.out.println(e + "\t\t\t  " + itemListToDisplay.get(e) + "\t\t\t  " + priceList.get(e));
        }
      }
    } catch (IndexOutOfBoundsException a) {

    }
    // Loop for getting the order

    do {
      try {
        System.out.print("ID/Bar Code: ");
        ID = s.nextInt();
        System.out.println(" ");

        itemList.add(itemListToDisplay.get(ID));
        currentPriceList.add(priceList.get(ID));
        System.out.print("Quantity: ");
        quantity = s.nextInt();
        quantityList.add(quantity);
        System.out.println(" ");

        System.out.print("Add more orders? Y/N: ");
        responseYN = s.next().charAt(0);
        System.out.println(" ");

      } catch (InputMismatchException k) {
        System.out.println("Invalid Input");
        System.out.println("Please try again...");
      } catch (IndexOutOfBoundsException e) {
        System.out.println("Invalid Input");
        System.out.println("Do you still want to continue? ");
        responseYN = s.next().charAt(0);
      }
    } while (responseYN == 'y' || responseYN == 'Y');

    try {
      iL.close();
      System.out.println("-------------------------Summary-------------------------\n");
      System.out.println("| Bread Name |\t\t| Price |\t| Quantity|\t| Total |\n");
      fw.write(" ---------------------Receipt----------------------------\n");
      fw.write("| Bread Name |\t\t| Price |\t\t| Quantity |\t\t|  Total |");

      total = 0;
      for (int i = 0; i <= itemList.size(); i++) {
        for (int e = 0; e <= quantityList.size(); e++) {
          total += quantityList.get(e) * currentPriceList.get(e);
          fw.write("\n" + itemList.get(e) + " \t\t\t\t " + currentPriceList.get(e) + " \t\t\t\t\t "
              + quantityList.get(e) + " \t\t\t\t\t " + (quantityList.get(e) * currentPriceList.get(e)));

          System.out.println(" ");
          System.out.println(itemList.get(e) + "\t\t  " + currentPriceList.get(e) + "\t\t  " + quantityList.get(e)
              + "\t\t  " + (quantityList.get(e) * currentPriceList.get(e)));
        }
      }
    } catch (IndexOutOfBoundsException e) {

    }

    System.out.println("\nTotal: " + total);

    System.out.println("-------------------------------------------------------");

    System.out.print("Payment: ");
    payment = s.nextDouble();
    change = payment - total;
    System.out.println(" ");

    System.out.print("Change: " + change);
    System.out.println("\nThank you for purchasing our product! ");
    System.out.println("Please come again! ");
    fw.write("\nTotal: " + String.valueOf(total));
    fw.write("\n----------------------------------------------------------");
    fw.write("\nPayment: " + String.valueOf(payment));
    fw.write("\nChange: " + String.valueOf(change));
    fw.write("\n\n\t\t\t\t\tThank you for purchasing our product!");
    fw.write("\n\t\t\t\t\t\t\t\t\t\tPlease come again!");
    fw.close();
  }
}
