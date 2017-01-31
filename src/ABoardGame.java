/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author WXU
 */
public class ABoardGame
{
    // return 0 draw, negative A lose, positive A wins
    int whoWins(String[] board, int ring)
    {
        int countA=0, countB=0;
        int startInd = board.length/2-1;
        int endInd = board.length/2;
        if (ring>startInd)
            return 0;
        startInd -= ring;
        endInd += ring;
        // count first row and last row of the ring
        for (int col=startInd; col<=endInd; col++) {
            if ( board[startInd].length()>col) {
                if (board[startInd].charAt(col)=='A')
                    countA++;
                else if (board[startInd].charAt(col)=='B')
                    countB++;
            }
            if ( board[endInd].length()>col) {
                if (board[endInd].charAt(col)=='A')
                    countA++;
                else if (board[endInd].charAt(col)=='B')
                    countB++;
            }
        }
        if (ring >0)  {
            for (int row=startInd+1; row<endInd; row++) {
                if (board[row].charAt(startInd)=='A')
                    countA++;
                else if (board[row].charAt(startInd)=='B')
                    countB++;
                if (board[row].charAt(endInd)=='A')
                    countA++;
                else if (board[row].charAt(endInd)=='B')
                    countB++;
            }
        }
        return countA-countB;
    }
    
    public String whoWins(String[] board) {
        int size = board.length;
        if (size %2 != 0)
            return "ODD";
        for (int ring=0; ring<size/2; ring++) {
            int win= whoWins(board, ring);
            if ( win>0 )
                return "Alice";
            else if (win<0)
                return "Bob";
        }
        return "Draw";
    }
}
