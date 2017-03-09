
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
// find a legal paths from (0,0) to (n-1,m-1)
// steps can be less than required, if so, the difference should be even
// complete required steps by reversing the first step and then repeat

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
public class StepsConstruct {
    
    int n=0;    // rows
    int m=0;    // columns
    int steps=0;
    String answer="";
    String []board2;
    static final int UP=1, DOWN=2, LEFT=4, RIGHT=8;
    static final int MASK = UP+DOWN+LEFT+RIGHT;
    
    
    long totalVisits=0;
    boolean visitPaths(String path, int i, int j, int dirBitMap)
    {
//        if ( !answer.isEmpty() )
//            return answer;
        //if (++totalVisits %100000000==0)
        //    out.println("visit count "+totalVisits+" path "+path.length()+":"+path);
        if ( board2[i].charAt(j)=='#')
            return false;
        if (failed[i][j])
            return false;
        //out.println(path+ " at "+i+" "+j);
        if (i==n-1 && j==m-1) {
            if (path.length()<=steps && (steps-path.length())%2==0) {
                answer = path;
                //out.println("  got it "+path);
                return true;
            }
            return false;
        }
        if (path.length()+n-1-i+m-1-j>steps)
            return false;
        if ( (LEFT & dirBitMap) >0) {
            if ( visitLeft(path, i, j) )
                return true;
        }
        if ( (RIGHT & dirBitMap) >0) {
            if (visitRight(path, i, j))
                return true;
        }
        if ( (UP & dirBitMap) >0) {
            if (visitTop(path, i, j))
                return true;
        }
        if ( (DOWN & dirBitMap) >0) {
            if (visitBottom(path, i, j))
                return true;
        }
        failed[i][j]=true;
        return false;
    }
   
    boolean visitLeft(String path, int i, int j)
    {
        if (j--==0)
            return false;
        return visitPaths(path+"L", i,j, LEFT|DOWN|UP);
    }
    boolean visitRight(String path, int i, int j)
    {
        if (++j==m)
            return false;
        return visitPaths(path+"R", i, j, RIGHT|UP|DOWN);
    }
    boolean visitTop(String path, int i, int j)
    {
        if (i--==0)
            return false;
        return visitPaths(path+"U", i, j, UP|LEFT|RIGHT);
    }
    
    boolean visitBottom(String path, int i, int j)
    {
        if (++i==n)
            return false;
        return visitPaths(path+"D", i, j, DOWN|LEFT|RIGHT);
    }
    boolean [][]failed;
    public String construct(String[] board, int k)
    {
        n = board.length;
        steps=k;
        board2=board;
        if ( n < 2 || n > 50 ) {
            out.println("Bad grid row "+n);
            return "";
        }
        m = board[0].length();
        if ( m < 2 || m > 50 ) {
            out.println("Bad grid column "+m);
            return "";
        }
        failed = new boolean[n][m];
        //out.println("Board dimension "+n+", " +m);
        if ( visitPaths("", 0, 0, DOWN|RIGHT) ) {
            int repeat = (steps-answer.length())/2;
            StringBuilder build = new StringBuilder();
            build.append(answer.charAt(0));
            for (int i=0; i< repeat; i++) { // repeat first two
                if ( answer.charAt(0)=='D' )
                    build.append("UD");
                else if ( answer.charAt(0)=='R' )
                    build.append("LR");
            }
            answer = build + answer.substring(1);
        }
        //out.println((answer.isEmpty()?"fail: ":("success " +answer))+" visits "+totalVisits);
        out.println(answer);
        return answer;
    }
    
    static void test()
    {
        String s;
        
        // DDRR
        s = new StepsConstruct().construct(new String[]{"...", ".#.", "..."}, 4);
        // DDUDUDUDUDRR  
        s = new StepsConstruct().construct(new String[]{"...", ".#.", "..."}, 12);
        // none
        s = new StepsConstruct().construct(new String[]{"..#", "#.#", "..#", ".#.", "..."}, 6);
        // DDDDRRUUUURRDDDD
        s = new StepsConstruct().construct(new String[]{".#...", ".#.#.", ".#.#.", ".#.#.", "...#."}, 16);
        // none
        s = new StepsConstruct().construct(new String[]{"#.", ".."}, 2);
        // none
        s = new StepsConstruct().construct(new String[]{"...#.", "..#..", ".#..."}, 100);
        
    }
    // don't submit main to topcoder
    public static void main(String[] args) {
        test();
    }
}
