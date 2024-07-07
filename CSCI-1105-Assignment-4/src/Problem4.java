
import java.util.Scanner;
public class Problem4  {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int[][] TicTacToe = {{0,0,0},{0,0,0},{0,0,0}}; // ehh definitely should've done 1d array
        int counter = 0;
        int subToInt;
        int Printer;

        String input;     // variable where the scanner will go into
        String sub = "1"; //storage for the substring array coordinate

        //variable problem 2
        int playertoken = 0;
        int match;

        //variable problem 3
        int pointToken = 300; // it starts from 300 as it's the max number and then goes down as the counter variable goes up

        //game happens in this main loop
        while (!(sub.equals("-"))){ //to make it easier we only look for the minus sign in the same String coordinate as a termination condition

            //loop variables used and user input

            input = in.nextLine();      //input received as a "PlayerX N" format
            sub = input.substring(8,9); // N >> into substring "sub"
            counter++;                  // determines player turn

            //block checks for termination condition on input
            if(sub.equals("-")){
                subToInt = 0;  //no case will be done
            }else{
                subToInt = Integer.parseInt(sub);
            }

            //player point system block (removing 1 from the counter because it would be 1 point over when the players input -1 at the end of the game)
            if(((counter-1) >5)&&((counter-1) <= 7) ){ //if the game ends after 5 turns and under 7 turns
                pointToken = 200;
            } else if ((counter-1) ==5){               //if the game ends at exactly 5 turns
                pointToken = 300;
            } else {
                pointToken = 100;                      // after 7 turns It's locked at 100
            }

           // they are in reverse to compensate for counter being == 1 in first turn and to compensate for there being an extra turn at the end where the final player types "-1"
            if(counter %2 ==  0) { //modulo determines turn
                playertoken = 1;//player 1
                Printer = 2;
            }else{
                playertoken = 2; //player 2
                Printer =1;
            }

            //Function inputs the players number (either 1 or 2) on the desired array position
            switch (subToInt) { //the substring from the original input turned into int I used here
                case 1 -> TicTacToe[0][0] = Printer;
                case 2 -> TicTacToe[0][1] = Printer;
                case 3 -> TicTacToe[0][2] = Printer;
                case 4 -> TicTacToe[1][0] = Printer;
                case 5 -> TicTacToe[1][1] = Printer;
                case 6 -> TicTacToe[1][2] = Printer;
                case 7 -> TicTacToe[2][0] = Printer;
                case 8 -> TicTacToe[2][1] = Printer;
                case 9 -> TicTacToe[2][2] = Printer;
            }
           //function that checks for matches after all moves are done and will determine if it's a draw (in a row and column)

            // vertical match check
            if((TicTacToe[0][0] == TicTacToe[0][1])&&(TicTacToe[0][0] == (TicTacToe[0][2]))&&(TicTacToe[0][0] != 0)){
                match = 1;
            }else if ((TicTacToe[1][0] == TicTacToe[1][1])&&(TicTacToe[1][0]==TicTacToe[1][2])&&(TicTacToe[1][0] != 0)){
                match = 1;
            }else if ((TicTacToe[2][0] == TicTacToe[2][1])&&(TicTacToe[2][0]==TicTacToe[2][2])&&(TicTacToe[2][0] != 0)){
                match = 1;
            }


            // horizontal match check
            else if ((TicTacToe[0][0] == TicTacToe[1][0])&&(TicTacToe[0][0]==TicTacToe[2][0])&&(TicTacToe[0][0] != 0)){
                match = 1;
            }else if ((TicTacToe[0][1] == TicTacToe[1][1])&&(TicTacToe[0][1]==TicTacToe[2][1])&&(TicTacToe[0][1] != 0)){
                match = 1;
            }else if ((TicTacToe[0][2] == TicTacToe[1][2])&&(TicTacToe[0][2]==TicTacToe[2][2])&&(TicTacToe[0][2] != 0)){
                match = 1;
            }

            //diagonal match check
            else if ((TicTacToe[0][0] == TicTacToe[1][1])&&(TicTacToe[0][0]==TicTacToe[2][2])&&(TicTacToe[0][0] != 0)){
                match =1;
            }else if ((TicTacToe[2][0] == TicTacToe[1][1])&&(TicTacToe[2][0]==TicTacToe[0][2])&&(TicTacToe[2][0] != 0)){
                match =1;
            }else { // if there's no match it will have match 0 as a token
                match =0;
            }

            //draw termination condition
            if(match == 0){
                playertoken =10;
            }
        }
        //end of game array printout
        for(int i = 0; i<3; i++){
            for(int j= 0; j<3 ; j++){
                System.out.print(TicTacToe[i][j] + " ");
            }
            System.out.println();
        }
        //player win check or draw
        if (playertoken == 10){ // if there was no match it will print this separate ending screen
            System.out.println("Game Over - Draw");
        } else { // normal winner screen using the player-token variable, which oscillates each turn thanks to modulo
            System.out.println("Game Over - Player " + playertoken + " Wins");
            System.out.println(pointToken + " points"); 
        }


    }
}

