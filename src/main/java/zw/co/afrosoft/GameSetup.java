package zw.co.afrosoft;
import java.util.Scanner;
public class GameSetup {

    char[][] displayBoard= {
            {' ' , 'A' , 'B' , 'C'},
            {'1' , '-' , '-' , '-'},
            {'2' , '-' , '-' , '-'},
            {'3' , '-' , '-' , '-'},
            {'4' , '-' , '-' , '-'}
    };

    int columnA=1;
    int columnB=2;
    int columnC=3;
    
    public void setPlayerShips(Player player1, Player player2) {

        Player[] players=new Player[]{player1,player2};

            System.out.println("Welcome to the game of Battleships\n Here is what the board looks like:");
            System.out.println(); //Skipping a line


            //Printing the DisplayBoard
            for (int i=0;i<5;i++)
            {
                for (int j=0;j<4;j++)
                {
                    System.out.print(displayBoard[i][j] + " ");
                }

                System.out.println();
            }


            System.out.println(); //Skipping a line
            System.out.println("When you shoot a spot with no ship, you will be shown a blank space.\n" +
                               "When you shoot a spot with a ship, you will be shown an X. ");
            System.out.println();//Skipping a line


            //Setting ships for each player
            int playerCounter=1; //Will just be used for displaying which Player is setting ships
            for (Player currentPlayer: players)
            {
                char[][] currentPlayerHiddenBoard=currentPlayer.playerHiddenShipBoard;

                for(int i=1;i<=3;i++)
                {
                    Scanner scanner=new Scanner(System.in);

                    //Obtaining column letter
                    System.out.println(); //Skipping a line
                    System.out.println("Player " + playerCounter + ", Please enter the coordinates of ship " + i + ":");
                    System.out.println("Column: ");
                    String columnInput=scanner.nextLine();
                    columnInput= columnInput.toUpperCase(); //To make sure the input is always Uppercase to be used in switch statement

                    //Error check to make sure the set column letters are picked
                    while(!columnInput.equals("A") && !columnInput.equals("B") && !columnInput.equals("C"))
                    {
                        System.out.println("That column doesn't exist!\nPlease re-input a valid column letter");
                        System.out.println("Column: ");
                        columnInput=scanner.nextLine();
                        columnInput= columnInput.toUpperCase();
                    }

                    //Changing column input into integer
                    int shipColumn = switch (columnInput) {
                        case "A" -> columnA;
                        case "B" -> columnB;
                        case "C" -> columnC;
                        default -> 0;
                    };

                    //Obtaining row number
                    System.out.println("Row: ");
                    int shipRow=scanner.nextInt();
                    scanner.nextLine();  //For clearing scanner

                    //Error check to make sure the set row numbers are picked. It's not working though
                    while(shipRow<1 || shipRow>4)
                    {
                        System.out.println("That row doesn't exist!\nPlease re-input a valid row number");
                        System.out.println("Row: ");
                        shipRow=scanner.nextInt();
                        scanner.nextLine();  //For clearing scanner
                    }


                    //Placing the ship on the board
                    if(currentPlayerHiddenBoard[shipRow][shipColumn]=='X')
                    {
                        System.out.println("There is a ship there already!");
                        i--;
                    }

                    else{
                        currentPlayerHiddenBoard[shipRow][shipColumn]='X';
                    }

                }

                //Setting board in object
                currentPlayer.setPlayerHiddenShipBoard(currentPlayerHiddenBoard);
                playerCounter++;
            }


            System.out.println();//Skipping a line
            System.out.println("The Game Is Now Beginning!");
            System.out.println();//Skipping a line

        }

    public String winCondition(char[][] player1Board, char[][] player2Board) {

        //These are local variables which will be used to check of any player has destroyed all the opponent's ships
        int player1ShipsDestroyed=0;
        int player2ShipsDestroyed=0;

        //Code to search through the player1 board
        for (int i=0;i<5;i++)
        {
            for (int j=0;j<4;j++)
            {
                if (player1Board[i][j]=='X')
                {
                    player1ShipsDestroyed++;
                }
            }

        }

        //Code to check if player2 won
        if(player1ShipsDestroyed == 3)
        {
            return "Player 2";
        }


        //Code to search through the player2 board
        for (int i=0;i<5;i++)
        {
            for (int j=0;j<4;j++)
            {
                if (player2Board[i][j]=='X')
                {
                    player2ShipsDestroyed++;
                }
            }

        }

        //Code to check if player 2 won
        if(player2ShipsDestroyed == 3)
        {
            return "Player 1";
        }

        return "None";
    }

    public String gameLoop(Player player1, Player player2) {

        char[][] targetPlayerHiddenShips=new char[5][4];
        char[][] targetPlayerDisplayBoard=new char[5][4];
        int playerTurn=1;
        Player playerReceivingChanges=new Player();
        String playerShooting="";
        String finalWinner="None";
        Scanner scanner = new Scanner(System.in);
        String inputColumn;
        int shipColumn;
        int shipRow;

            //Loop for the game
            while(finalWinner.equals("None"))
            {
                //Checking if anyone won
                finalWinner=winCondition(player1.getPlayerDisplayBoard(),player2.getPlayerDisplayBoard());
                if(finalWinner.equals("Player 1"))
                {
                    return "Player 1";
                }
                else if (finalWinner.equals("Player 2"))
                {
                    return "Player 2";
                }


                /* Checking who has their turn to shoot the other player's ships */
                if (playerTurn==1){
                    targetPlayerDisplayBoard = player2.getPlayerDisplayBoard();
                    targetPlayerHiddenShips = player2.getPlayerHiddenShipBoard();
                    playerShooting = "Player 1";
                    playerReceivingChanges=player2;
                } else if (playerTurn==2){
                    targetPlayerDisplayBoard = player1.getPlayerDisplayBoard();
                    targetPlayerHiddenShips = player1.getPlayerHiddenShipBoard();
                    playerShooting = "Player 2";
                    playerReceivingChanges=player1;
                }

                //Printing board of target player (player who is having their board shot)
                for (int i = 0; i < 5; i++){
                    for (int j = 0; j < 4; j++){
                        System.out.print(targetPlayerDisplayBoard[i][j] + " ");
                    }
                    System.out.println();
                }

                //Getting player input
                System.out.println(playerShooting + ", please pick a coordinate to shoot: ");
                System.out.println("Column: ");
                inputColumn = scanner.nextLine().toUpperCase();

                while(!inputColumn.equals("A") && !inputColumn.equals("B") && !inputColumn.equals("C")){
                    System.out.println("That column doesn't exist!\nPlease re-input a valid column letter");
                    System.out.println("Column: ");
                    inputColumn = scanner.nextLine().toUpperCase();
                }

                 shipColumn = switch (inputColumn) {
                    case "A" -> columnA;
                    case "B" -> columnB;
                    case "C" -> columnC;
                    default -> 0;
                };

                System.out.println("Row: ");
                shipRow = scanner.nextInt();
                scanner.nextLine(); //Clearing the scanner buffer

                while(shipRow < 1 || shipRow > 4){
                    System.out.println("That row doesn't exist!\nPlease re-input a valid row number");
                    System.out.println("Row: ");
                    shipRow = scanner.nextInt();
                    scanner.nextLine();
                }

                if(targetPlayerHiddenShips[shipRow][shipColumn] == 'X'){
                    targetPlayerDisplayBoard[shipRow][shipColumn] = 'X';
                    System.out.println("It's a direct hit!!!!\n");
                    playerReceivingChanges.setPlayerDisplayBoard(targetPlayerDisplayBoard);
                } else {
                    targetPlayerDisplayBoard[shipRow][shipColumn] = ' ';
                    System.out.println("It's a miss\n");
                    playerReceivingChanges.setPlayerDisplayBoard(targetPlayerDisplayBoard);

                    //Changing turns
                    if (playerTurn==1){
                        playerTurn=2;
                    } else if (playerTurn==2){
                        playerTurn=1;
                    }
                }

            }


                return finalWinner;

        }
}
