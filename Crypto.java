import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.util.ArrayList;


public class Crypto {

    public static void printMenu() {

        System.out.println("\nWelcome! Please select an option to get started with your crypto-currency.");
        System.out.println("\n1) Buy #");
        System.out.println("2) Sell #");
        System.out.println("3) Wallet");
        System.out.println("4) BTC Price");
        System.out.println("5) Trade History");
        System.out.println("\n6) Load");
        System.out.println("7) Save");
        System.out.println("\n8) Exit");
        System.out.print("\nEnter choice> ");
    } // end Menu

    public static String TimeandDate() {
        String mydatestring;
        Date Currentdate = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        mydatestring = dateFormat.format(Currentdate);
        return mydatestring;
    } // end Time & Date Getter

    public static class Wallet { // Stores the user's values
        double BTCBalance = 0.0F; // Starting with 0.0 BTC
        double USDBalance = 100000.0F; // Starting with $100,000

        public void AddCoins(double a) {
            BTCBalance = (double) (BTCBalance + a);
        }

        public void SubtractCoins(double a) {
            BTCBalance = (double) (BTCBalance - a);
        }

    } // end Wallet

    public static class GetLive {
        Random r = new Random();
        int low = 50000;
        int high = 60000;
        int result = r.nextInt(high = low + 1) + low;

        public double getResult() {
            return Double.valueOf(result);
        }

        public void Update() {
            result = r.nextInt(high = low + 1) + low;
            System.out.println("\nThe current BTC price is " + result + " BTC"); // Outputting current BTC Price
        }
    } //end BTC Update

    public static class Ledger {
        ArrayList<String> ledger = new ArrayList<String>(); // Creating Array

        void additem(String s) { // Creating String s in Ledger
            ledger.add(s);
        }

        void printit() {
            for (int i = 0; i < ledger.size(); i++) {
                System.out.println(ledger.get(i));
            }
        }

        void writeit(String filename) {

            try {
                FileWriter myWriter = new FileWriter(filename);
                // loop through the vector
                for (int i = 0; i < ledger.size(); i++) {
                    // writing every element out to the file
                    myWriter.write(ledger.get(i));
                    myWriter.write("\n");
                }
                myWriter.close(); // Closing File
                System.out.println("\nSuccessfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("\nAn error occurred in writeit.");
                e.printStackTrace();
            }

        }

        void readit(String filename) {

            Scanner file = new Scanner("ledger.txt");
            file.close(); // Closing File

        }
    } //end Ledger


    public static void main(String[] args) {


        int menu = 0; // Menu
        double buy = 0; // Buy function
        double sell = 0;  // Sell function
        GetLive g = new GetLive();  // Access Update BTC Price
        Wallet w = new Wallet(); // Access Wallet class
        Ledger l = new Ledger(); // Access Ledger class

        Scanner myscanner = new Scanner(System.in);

        while (true) {
            printMenu();
            menu = myscanner.nextInt();

            if (menu == 1) {

                System.out.println("\nOption #1 - Buy Crypto");
                g.Update();
                System.out.println("\nPlease enter the amount of BTC you would like to purchase.");
                buy = myscanner.nextDouble(); // Taking input
                System.out.println(g.getResult());
                w.USDBalance = w.USDBalance - (buy * g.getResult());  //Subtracting USDBalance
                w.BTCBalance = w.BTCBalance + (buy);  // Adding BTCBalance
                String s = "\nBUY | Purchasing of " + buy + "BTC @ " + TimeandDate();
                System.out.println(s);
                l.additem(s); //Adding to the ledger

                g.Update(); // Updates BTC price each time menu is initiated

            } else if (menu == 2) {

                System.out.println("Option #2 - Sell Crypto");
                g.Update();
                System.out.println("\nPlease enter the amount of BTC you would like to sell.");
                sell = myscanner.nextDouble(); // Taking input
                System.out.println(g.getResult());
                w.USDBalance = w.USDBalance + (sell * g.getResult());  //Adding USDBalance
                w.BTCBalance = w.BTCBalance - (sell);  // Adding BTCBalance
                String s = "\nSELL | Selling of " + sell + "BTC @ " + TimeandDate();
                l.additem(s); // Adding to the ledger

                g.Update(); // Updates BTC price each time menu is initiated

            } else if (menu == 3) {

                System.out.println("Option #3 - View Wallet");

                System.out.println("$" + w.USDBalance); // Printing USDBalance
                System.out.println(w.BTCBalance + "BTC"); // Printing BTCBalance

            } else if (menu == 4) {

                System.out.println("Option #4 - BTC Price");

                g.Update(); // Updates BTC price each time menu is initiated

            } else if (menu == 5) {

                System.out.println("Option #5 - Trade History");
                for (int i = 0; i < l.ledger.size(); i++) { // Array for Buying & Selling Functions
                    System.out.println(l.ledger.get(i));

                }

            } else if (menu == 6) {
                System.out.println("\nLoad File");

                l.readit("ledger.txt");
                l.printit();

            } else if (menu == 7) {

                System.out.println("\nSave File");

                System.out.println("Saving your ledger will overwrite the current file.");

                l.printit();
                l.writeit("ledger.txt");


            } else if (menu == 8) {

                System.out.println("\nExit");
                break; // Leaving

            } else {
                System.out.println("\nError: Please try again."); // Bad Entry
            }
        }
    }
}
