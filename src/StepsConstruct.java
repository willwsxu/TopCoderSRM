
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author WXU
 * Find path on n x m board, from top left to bottom right corner in k steps
 * valid moves, up, down, Left, Right
 * board can have values . or #, you cannot move to square with #
 */
// find valid paths, prune invalid path
class StepsConstruct2
{
    int n=0;    // rows
    int m=0;    // columns
    int steps=0;
    String answer="";
    char [][]board2;
    static final int UP=1, DOWN=2, LEFT=4, RIGHT=8;
    static final int MASK = UP+DOWN+LEFT+RIGHT;
    
    // check if there is a way to reach bottom right square    
    boolean visitLeft2(char [][]board2, int i, int j)
    {
        if (j--==0)
            return true;
        return blockSquare(board2, i, j, RIGHT);
    }
    boolean visitUp2(char [][]board2, int i, int j)
    {
        if (i--==0)
            return true;
        return blockSquare(board2, i, j, DOWN);
    }
    boolean visitRight2(char [][]board2, int i, int j)
    {
        if (++j==m)
            return true;
        return blockSquare(board2, i, j, LEFT);
    }
    boolean visitDown2(char [][]board2, int i, int j)
    {
        if (++i==n)
            return true;
        return blockSquare(board2, i, j, UP);
    }
    boolean isRightBlocked(char [][]board2, int i, int j)
    {
        if (++j==m)
            return true;
        if (board2[i][j]=='#')
            return true;
        return false;
    }
    boolean isDownBlocked(char [][]board2, int i, int j)
    {
        if (++i==n)
            return true;
        if (board2[i][j]=='#')
            return true;
        return false;
    }
    boolean blockSquare(char [][]board2, int i, int j, int back)
    {
        if (board2[i][j]=='#')
            return true;
        if ( i==n-1 && j==m-1)
            return false;
        if ( isRightBlocked(board2, i, j) && isDownBlocked(board2, i, j)) {
            board2[i][j]='#'; // mark it as blocked if it cannot go down or right
            visitUp2(board2, i, j);
            visitLeft2(board2, i, j);
            return true;
        }
        visitDown2(board2, i, j);
        visitRight2(board2, i, j);
        return false;
    }
    boolean isPathViable(String[] board)
    {
        n = board.length;
        m = board[0].length();
        if ( board[0].charAt(0)=='#')
            return false;
        if ( board[n-1].charAt(m-1)=='#')
            return false;
        board2 = new char[n][m];
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                board2[i][j]=board[i].charAt(j);
            }
        }
        blockSquare(board2, 0, 0, 0);
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                out.print(board2[i][j]);
            }
            out.println();
        }
        boolean blocked = isRightBlocked(board2,0,0) && isDownBlocked(board2,0,0);
        out.println("path "+(blocked?"BAD":"OK"));
        return !blocked;
    }    
    
    
    long totalVisits=0;
    String visitPaths(String path, int i, int j)
    {
        if ( !answer.isEmpty() )
            return answer;
        if (++totalVisits %100000000==0)
            out.println("visit count "+totalVisits+" path "+path.length()+":"+path);
        if ( board2[i][j]=='#')
            return "";
        //out.println(path+ " at "+i+" "+j);
        if (i==n-1 && j==m-1) {
            if (path.length()==steps) {
                answer = path;
                out.println("got it "+path);
                return path;
            }
            return "";
        }
        if (path.length()+n-1-i+m-1-j>steps)
            return "";
        visitLeft(path, i, j);
        visitRight(path, i, j);
        visitTop(path, i, j);
        visitBottom(path, i, j);
        return "";
    }
   
    void visitLeft(String path, int i, int j)
    {
        if (j--==0)
            return;
        visitPaths(path+"L", i,j);
    }
    void visitRight(String path, int i, int j)
    {
        if (++j==m)
            return;
        visitPaths(path+"R", i, j);
    }
    void visitTop(String path, int i, int j)
    {
        if (i--==0)
            return;
        visitPaths(path+"U", i, j);
    }
    
    void visitBottom(String path, int i, int j)
    {
        if (++i==n)
            return;
        visitPaths(path+"D", i, j);
    }
    String construct(String[] board, int k)
    {
        n = board.length;
        steps=k;
        if ( n < 2 || n > 50 ) {
            out.println("Bad grid row "+n);
            return "";
        }
        m = board[0].length();
        if ( m < 2 || m > 50 ) {
            out.println("Bad grid column "+m);
            return "";
        }
        out.println("Board dimension "+n+", " +m);
        if (!isPathViable(board))
            return "";
        visitPaths("", 0, 0);
        out.println((answer.isEmpty()?"fail: ":("success " +answer))+" visits "+totalVisits);
        return answer;
    }
}
// recursive full search
public class StepsConstruct {
    int n=0;    // rows
    int m=0;    // columns
    int steps=0;
    String answer="";
    long totalVisits=0;
    String visitPaths(String[] board, String path, int i, int j)
    {
        if ( !answer.isEmpty() )
            return answer;
        if (++totalVisits %100000000==0)
            out.println("visit count "+totalVisits+" path "+path.length()+":"+path);
        if ( board[i].charAt(j)=='#')
            return "";
        //out.println(path+ " at "+i+" "+j);
        if (i==n-1 && j==m-1) {
            if (path.length()==steps) {
                answer = path;
                out.println("got it "+path);
                return path;
            }
            return "";
        }
        if (path.length()+n-1-i+m-1-j>steps)
            return "";
        visitLeft(board, path, i, j);
        visitRight(board, path, i, j);
        visitTop(board, path, i, j);
        visitBottom(board, path, i, j);
        return "";
    }
   
    void visitLeft(String[] board, String path, int i, int j)
    {
        if (j--==0)
            return;
        visitPaths(board, path+"L", i,j);
    }
    void visitRight(String[] board, String path, int i, int j)
    {
        if (++j==m)
            return;
        visitPaths(board, path+"R", i, j);
    }
    void visitTop(String[] board, String path, int i, int j)
    {
        if (i--==0)
            return;
        visitPaths(board, path+"U", i, j);
    }
    
    void visitBottom(String[] board, String path, int i, int j)
    {
        if (++i==n)
            return;
        visitPaths(board, path+"D", i, j);
    }
    
    // check if there is a way to reach bottom right square    
    boolean visitLeft2(char [][]board2, int i, int j)
    {
        if (j--==0)
            return true;
        return blockSquare(board2, i, j);
    }
    boolean visitUp2(char [][]board2, int i, int j)
    {
        if (i--==0)
            return true;
        return blockSquare(board2, i, j);
    }
    boolean visitRight2(char [][]board2, int i, int j)
    {
        if (++j==m)
            return true;
        return blockSquare(board2, i, j);
    }
    boolean visitDown2(char [][]board2, int i, int j)
    {
        if (++i==n)
            return true;
        return blockSquare(board2, i, j);
    }
    boolean isRightBlocked(char [][]board2, int i, int j)
    {
        if (++j==m)
            return true;
        if (board2[i][j]=='#')
            return true;
        return false;
    }
    boolean isDownBlocked(char [][]board2, int i, int j)
    {
        if (++i==n)
            return true;
        if (board2[i][j]=='#')
            return true;
        return false;
    }
    boolean blockSquare(char [][]board2, int i, int j)
    {
        if (board2[i][j]=='#')
            return true;
        if ( i==n-1 && j==m-1)
            return false;
        if ( isRightBlocked(board2, i, j) && isDownBlocked(board2, i, j)) {
            board2[i][j]='#'; // mark it as blocked if it cannot go down or right
            return true;
        }
        visitDown2(board2, i, j);
        visitRight2(board2, i, j);
        visitUp2(board2, i, j);
        visitLeft2(board2, i, j);
        return false;
    }
    boolean isPathViable(String[] board)
    {
        n = board.length;
        m = board[0].length();
        if ( board[0].charAt(0)=='#')
            return false;
        if ( board[n-1].charAt(m-1)=='#')
            return false;
        char [][]board2 = new char[n][m];
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                board2[i][j]=board[i].charAt(j);
            }
        }
        blockSquare(board2, 0, 0);
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                out.print(board2[i][j]);
            }
            out.println();
        }
        boolean blocked = isRightBlocked(board2,0,0) && isRightBlocked(board2,0,0);
        out.println("path "+(blocked?"BAD":"OK"));
        return blocked;
    }
    String construct(String[] board, int k)
    {
        n = board.length;
        steps=k;
        if ( n < 2 || n > 50 ) {
            out.println("Bad grid row "+n);
            return "";
        }
        m = board[0].length();
        if ( m < 2 || m > 50 ) {
            out.println("Bad grid column "+m);
            return "";
        }
        out.println("Board dimasion "+n+", " +m);
        visitPaths(board, "", 0, 0);
        out.println((answer.isEmpty()?"fail: ":("success " +answer))+" visits "+totalVisits);
        return answer;
    }
    
    public static void main(String[] args) {
        String s;
        
        // DDRR
        s = new StepsConstruct2().construct(new String[]{"...", ".#.", "..."}, 4);
        // DDUDUDUDUDRR  
        s = new StepsConstruct2().construct(new String[]{"...", ".#.", "..."}, 12);
        // none
        s = new StepsConstruct2().construct(new String[]{"..#", "#.#", "..#", ".#.", "..."}, 6);
        // DDDDRRUUUURRDDDD
        s = new StepsConstruct2().construct(new String[]{".#...", ".#.#.", ".#.#.", ".#.#.", "...#."}, 16);
        // none
        s = new StepsConstruct2().construct(new String[]{"#.", ".."}, 2);
        
        //new StepsConstruct2().isPathViable(new String[]{"...#.", "..#..", ".#..."});
        s = new StepsConstruct2().construct(new String[]{"...#.", "..#..", ".#..."}, 100);
    }
}
