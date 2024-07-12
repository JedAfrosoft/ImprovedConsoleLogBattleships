package zw.co.afrosoft;

public class Main {
    public static void main(String[] args) {

        //Creating instances
        Player player1 = new Player();
        Player player2 = new Player();
        GameSetup gameSetup = new GameSetup();

        gameSetup.setPlayerShips(player1,player2);
        String winner=gameSetup.gameLoop(player1,player2);

        System.out.println("The winner of the game is: " + winner);
        System.out.println("Congratulations!!!!!!!");


    }
}