/*
 * CSCI 1105 - Assignment 3 - Problem 3
 * Author: Javier Wahn
 * Date: 23/10/22
 * Banner ID: B00935168
 */

import java.util.Scanner;
public class Problem3 {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        //variables used
        int playerscore1 = 0;
        int playerscore2 = 0;
        int tiebreaker = 0;
        int player1Outside=0;
        int player2Outside=0;
        int winner =0;
        //problem 2 value
        int round;
        int counter =1;
        //problem 3 values
        int playertie1=0;
        int playertie2=0;

        //main loop

        while (winner == 0){
            tiebreaker =0; //resetting loop checker each round
            playertie1 =0;
            playertie2 =0;
            while (tiebreaker == 0){ //messy check for tie situation
                int player1 = in.nextInt(); //scan values entered here
                int player2 = in.nextInt();
                player1Outside=player1; //transfers value so it works outside the loop
                player2Outside=player2;
                tiebreaker = player2-player1;

                if (tiebreaker == 0){ // main tiebreaker game block
                    System.out.println("Round tied, starting the tie-breaker game!");

                    for (int i =0 ; i<10 ;i++){ //tiebreaker game block
                        int dice1= in.nextInt();
                        playertie1+= dice1;
                    }
                    System.out.println("Player 1 tie-breaker total is " + playertie1);
                    for (int i =0; i<10; i++){
                        int dice2= in.nextInt();
                        playertie2+=dice2;
                    }
                    System.out.println("Player 2 tie-breaker total is " + playertie2);

                    if (playertie1 > playertie2){ //check for who won tiebreaker
                        playerscore1++;
                        playerscore1++;
                        playerscore1++;
                        System.out.println("Player 1 wins the tie-breaker!");
                        tiebreaker =1;
                    } else if (playertie2 > playertie1) {
                        playerscore2++;
                        playerscore2++;
                        playerscore2++;
                        System.out.println("Player 2 wins the tie-breaker!");
                        tiebreaker =1;
                    }

                    if (playerscore1 >=10){ //block checks if it needs to end the main loop in the middle of the code after a tie
                        System.out.println("Player 1 wins the game!");
                        winner=1;
                        break;
                    } else if (playerscore2 >=10){
                        System.out.println("Player 2 wins the game!");
                        winner =1;
                        break;
                    }

                }
            }
            if (winner ==1 ){ //in case the game ends during tiebreaker
                break;
            }

            if(player2Outside < player1Outside){ // block checks who won the round
                playerscore1++;
                System.out.println("Player 1 wins the round");
            } else if (player1Outside < player2Outside){
                playerscore2++;
                System.out.println("Player 2 wins the round");
            }

            if (playerscore1 >=10){ //block checks if it needs to end the main loop
                winner =1;
                System.out.println("Player 1 wins the game!");
            } else if (playerscore2 >=10){
                winner =1;
                System.out.println("Player 2 wins the game!");
            }

        }


    }
}
