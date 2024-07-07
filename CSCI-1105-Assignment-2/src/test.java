import java.util.Scanner;
/*
Date: 10/5/2022
Author: Javier Wahn
Project: Assignment 2

*/
import java.util.Scanner;
import java.lang.Math;
class main {
    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        double bank = kb.nextDouble();
        double dollars = kb.nextDouble();
        double price = kb.nextDouble();
        double dollardue = dollars - price;
        double change = dollardue;
        kb.nextLine(); //buffer
        String cancel =  kb.nextLine();

        if (!cancel.contains("c")){
            if ((bank - Math.abs(change) > 0) && (change > 0)){ // if the payment is done in full then it has to check if theres money in the bank
                System.out.println("Transaction is Complete");
                System.out.println("The amount paid is: " + dollars + " dollars");
                System.out.println("The item cost is: " + price + " dollars");
            } else if (change < 0) { //if theres money missing in the payment, the cash register doesnt matter
                System.out.println("Transaction is Incomplete");
                System.out.println("The amount paid is: " + dollars + " dollars");
                System.out.println("The item cost is: " + price + " dollars");
            }
        }
        if (cancel.contains("c")) { //If the program is cancelled it will stop

            System.out.println("Transaction Cannot Be Completed");
            System.out.println("Transaction was cancelled");

        } else if ((bank - Math.abs(change) < 0) && !(dollars - price < 0)) { //if theres less money in the bank and the bank actually owes money program stops

            System.out.println("Transaction Cannot Be Completed");
            System.out.println("Insufficient amount in cash register");

        } else if (change < 0){ //the client owes money

            change = Math.abs(change); //this changes the normally negative value of change (because the cash the costumer had was smaller than the subtracting price
            System.out.printf("the amount due is: %.2f dollars",change);

        } else  if (change > 0 ){ // the client is owed change

            System.out.printf("the amount of change to return is: %.2f dollars",change);
        }

    }
}