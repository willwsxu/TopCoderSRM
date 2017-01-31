
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
 */
public class StepsConstruct {
    int n=0;    // rows
    int m=0;    // columns
    int steps=0;
    String answer="";
    String visitPaths(String[] board, String path, int i, int j)
    {
        if ( board[i].charAt(j)=='#')
            return "";
        //out.println(path+ " at "+i+" "+j);
        if (path.length()>steps)
            return "";
        if (i==n-1 && j==m-1) {
            if (path.length()==steps) {
                answer = path;
                return path;
            }
        }
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
        if ( board[i].charAt(j)=='#')
            return;
        visitPaths(board, path+"L", i,j);
    }
    void visitRight(String[] board, String path, int i, int j)
    {
        if (++j==m)
            return;
        if ( board[i].charAt(j)=='#')
            return;
        visitPaths(board, path+"R", i, j);
    }
    void visitTop(String[] board, String path, int i, int j)
    {
        if (i--==0)
            return;
        if ( board[i].charAt(j)=='#')
            return;
        visitPaths(board, path+"U", i, j);
    }
    
    void visitBottom(String[] board, String path, int i, int j)
    {
        if (++i==n)
            return;
        if ( board[i].charAt(j)=='#')
            return;
        visitPaths(board, path+"D", i, j);
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
        out.println("First grid column "+m);
        visitPaths(board, "", 0, 0);
        return answer;
    }
    
    public static void main(String[] args) {
        String s;
        
        s = new StepsConstruct().construct(new String[]{"...", ".#.", "..."}, 4);
        out.println("success " +s);
        s = new StepsConstruct().construct(new String[]{"...", ".#.", "..."}, 12);
        out.println("success " +s);
        s = new StepsConstruct().construct(new String[]{"..#", "#.#", "..#", ".#.", "..."}, 6);
        out.println("success " +s);
        s = new StepsConstruct().construct(new String[]{".#...", ".#.#.", ".#.#.", ".#.#.", "...#."}, 16);
        out.println("success " +s);
        s = new StepsConstruct().construct(new String[]{"#.", ".."}, 2);
        out.println("success " +s);   

        s = new StepsConstruct().construct(new String[]{"...#.", "..#..", ".#..."}, 100);
        out.println("success " +s);    

    }
}
