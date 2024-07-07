/*
Author:Javier Wahn
ID: B00935618
Date: 27/11/2022
Function: Recreate a simpler version of the game Battleship while using mostly method calls
*/
import java.util.Scanner;
public class Problem4 {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        //static variables
        int[][] gameBoard = new int[8][8];
        String GRID = ""; //for printing the gameboard later
        int counter = 0;
        String nextAttack = "";
        boolean win = false;
        String stats;

        //user input
        int count = in.nextInt(); // number of ships to place in the gameboard
        in.nextLine(); // buffer

        //variable amount of ship placement coordinates >>> array solves it
        //storage with 1D array with for loop using count variable

        String[] shipCoordinate = new String[count];
        for(int i = 0; i< count;i++){
            shipCoordinate[i] = in.nextLine();
        }
        //fill gameboard with 0 which will work alongside getGrid function
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8;j++){
                gameBoard[i][j] = 0;
            }
        }

        //since each array element is in format XN then we can chartAt 0 = char col charAt 1 = char row
        for(int i = 0; i<count; i++){
            setTarget(gameBoard, shipCoordinate[i].charAt(0), shipCoordinate[i].charAt(1)); //fills gameboard squares with ships
        }

        // in here we put the attack phase
        // so the way the attack phase works player 2 will attack N amount of times
        //we can program a while look that calls ATTACKGRID and install an X until a -1 is inputed

        while(!nextAttack.equals("-1")){
            nextAttack = in.nextLine();
            //will put an X in that spot in nextattack coordinate
            if(!nextAttack.equals("-1")){
                attackGrid(gameBoard,nextAttack.charAt(0),nextAttack.charAt(1));
            }

        }

        //getting the string version of the grid should still be LAST!!!!!!!!!!

        //prints the GRID string with the getGRID method
        GRID = getGrid(gameBoard);
        String[] SPLIT = GRID.split(" ");

        //trying to print out the 64 word long array into a 2d array image but failing tbh TO DO AND ITS DONE
        for(int i = 0; i<64;i++){
            System.out.print(SPLIT[i]);
            counter++;
            if(counter == 8){
                System.out.println("");
                counter = 0;
            }
        }
        //end game section
        win = checkAttackerWin(gameBoard); // checks if there is any ships still alive (array element == 1)
        if (win == false){
            System.out.println("Defending Player Wins");
        } else if (win == true){
            System.out.println("Attacking Player Wins");
        }

        //prints the stats taken from reading the final state of the gameBoard
        stats = getStats(gameBoard);
        System.out.println(stats);

    }


    /*
    Function setTarget: uses the String inputs to set the ship positions in the gameboard array
    parameter: 2D array gameboard,char column coordinate and char row coordinate for 1 ship
    */
    public static void setTarget(int gameBoard[][], char col, char row ){

        //turning the row coordinate into an array coordinate
        String temp = String.valueOf(row); //moving char to string
        int ROWnumber = Integer.parseInt(temp)-1;  // string to int

        //turning the collumn coordinate into an array coordinate
        temp = String.valueOf(col);
        int COLnumber = temp.charAt(0);
        COLnumber = COLnumber -65; // ASCCI number for the current column coordinate - the ASCCI value of A which would be the smallest value

        //set target statement
        gameBoard[ROWnumber][COLnumber] = 1; //we'll fill it with 1 for now because they want a T somehow when you turn into a String
    }

    /*
    function attackGrid: uses the String inputed in each loop interation in the attack phase while loop to "attack" a grid coordinate
    paremeter: 2d array gameboard that already has a ship in it. char column coordinate and char row coordinate for 1 grid attack
    */
    public static void attackGrid(int gameBoard[][],char col, char row){
        //turning the row coordinate into an array coordinate
        String temp = String.valueOf(row); //moving char to string
        int ROWnumber = Integer.parseInt(temp)-1;  // string to int - 1 since the row coordinate starts at 0 in the array

        //turning the collumn coordinate into an array coordinate
        temp = String.valueOf(col);
        int COLnumber = temp.charAt(0);
        COLnumber = COLnumber -65; // ASCCI number for the current column coordinate - the ASCCI value of A which would be the smallest value
        //set target statement
        if (gameBoard[ROWnumber][COLnumber] == 1){
            gameBoard[ROWnumber][COLnumber] = 2; //we'll fill it with 2 so it turns into an X
        }else if (gameBoard[ROWnumber][COLnumber] ==0){
            gameBoard[ROWnumber][COLnumber] = 3; //we'll fill it with 3 so it turns into an 0 because they missed
        }
    }

    /*
    Function getStats: Uses the final version of the int gameBoard array which has either 1 2 or 3 stored in the grid which will gives us the final stats
    paremeter: 2d array gameboard
    */
    public static String getStats(int[][] gameBoard){
        //int values which will determine how many of these values are in the current final gameBoard
        int shipHitCounter = 0;
        int shipMissCounter = 0;
        int shipLiveCounter = 0;
        //int to string storage values
        String hit = "";
        String miss = "";
        String live = "";
        //return statement
        String gameStats = "";
        //nested for loop which checks in gameBoard[i][j] if it has either a destroyed ship, a missed shot or a living ship in that order
        for(int i=0; i<8; i++){
            for(int j = 0; j<8; j++){
                if(gameBoard[i][j] ==2){
                    shipHitCounter++;
                }else if(gameBoard[i][j] == 3){
                    shipMissCounter++;
                } else if(gameBoard[i][j]==1){
                    shipLiveCounter++;
                }
            }
        }
        //string to int
        hit = Integer.toString(shipHitCounter);
        miss = Integer.toString(shipMissCounter);
        live = Integer.toString(shipLiveCounter);
        //all strings are merged into gameStats with integrated line breaks
        gameStats = "Hits: " + hit + System.lineSeparator() + "Misses: " + miss + System.lineSeparator() + "Remaining Targets: " + live;
        //return
        return gameStats;
    }

    /*
    function getGrid: uses the gameboard after inputting the ship positions and turning it into a single String
    parameter: 2D array
    */
    public static String getGrid(int gameBoard[][]){
        String water = "_";
        String attack = "X";
        String ship = "T";
        String GRID = "";
        String miss = "O";

        //uses the fact that in java string + string = "string string" by adding together all array elements
        for(int i = 0; i<8;i++ ){
            for(int j = 0; j<8; j++){
                if(gameBoard[i][j] == 0){ //since theres only two possible scnearios inside the array we can create the String with this if else
                    GRID = GRID + water + " "; //the space is necessary for the SPLIT function to work later
                }else if(gameBoard[i][j] == 1){
                    GRID = GRID + water + " ";
                }else if(gameBoard[i][j] == 2){
                    GRID = GRID + attack + " ";
                }else if(gameBoard[i][j] == 3){
                    GRID = GRID + miss + " ";
                }
            }
        }
        return GRID;
    }
    /*
    function checkAttackerWin: uses the int gameBoard to check if a single element is equal to 1 which means the attacking player missed a ship
    parameter: 2D array gameBoard
    */
    public static boolean checkAttackerWin(int[][] gameBoard){
        boolean win = true;
        for(int i=0; i<8; i++){
            for(int j = 0; j<8; j++){
                if(gameBoard[i][j] ==1){
                    win = false; //flag for the win screen
                }
            }
        }
        return win;
    }

}