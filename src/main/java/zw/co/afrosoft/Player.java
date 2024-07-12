package zw.co.afrosoft;

public class Player {

    public Player() {
    }

    public char[][] playerDisplayBoard= {
            {' ' , 'A' , 'B' , 'C'},
            {'1' , '-' , '-' , '-'},
            {'2' , '-' , '-' , '-'},
            {'3' , '-' , '-' , '-'},
            {'4' , '-' , '-' , '-'}
    };

    public char[][] playerHiddenShipBoard= {
            {' ' , 'A' , 'B' , 'C'},
            {'1' , '-' , '-' , '-'},
            {'2' , '-' , '-' , '-'},
            {'3' , '-' , '-' , '-'},
            {'4' , '-' , '-' , '-'}
    };

    public char[][] getPlayerDisplayBoard() {
        return playerDisplayBoard;
    }

    public void setPlayerDisplayBoard(char[][] playerDisplayBoard) {
        this.playerDisplayBoard = playerDisplayBoard;
    }

    public char[][] getPlayerHiddenShipBoard() {
        return playerHiddenShipBoard;
    }

    public void setPlayerHiddenShipBoard(char[][] playerHiddenShipBoard) {
        this.playerHiddenShipBoard = playerHiddenShipBoard;
    }
}
