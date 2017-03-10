

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author WXU
 */

import static java.lang.System.out;
import java.util.ArrayList;
public class ForwardMancala {
    
    // 
    void print(int m[])
    {
        for(int i=0; i<m.length; i++) {
            out.print(m[i]);
            out.print(" ");
        }
        out.println();
    }
    int move(int start[], int pos)
    {
        out.println(pos);
        int stones = start[pos];
        start[pos]=0;
        int next = (pos+1)% start.length;
        for (int i=0; i<stones; i++) {
            pos = (pos+1)%start.length;
            start[pos]++;
        }
        print(start);
        return next;        
    }
    // start length 2 to 10
    // values in each start element 1 to 10
    public int[] findMoves(int[] start)
    {
        int total=0;
        int max=0;
        int pos=-1;
        for (int i=0; i<start.length; i++) {
            total += start[i];
            if (max < start[i] ) {
                max = start[i];
                pos=i;
            }
        }
        out.println(max);
        out.println(total);
        out.println(pos);
        if (max==total)
            return new int[]{};
        
        ArrayList<Integer> moves = new ArrayList<>(100);
        // find first move
        for (int i=0; i<start.length; i++) {
            pos = (pos+1)%start.length;
            if ( start[pos]>0 )
                break;
        }
        while (moves.size()<2500) {
            moves.add(pos);
            pos = move(start, pos);
            if (start[pos]==total)
                break;
        }
        int a[]=new int[moves.size()];
        for (int i=0; i<moves.size(); i++) {
            a[i] = moves.get(i);
        }
        out.println(moves);
        return a;
    }
    
    static void test()
    {
        //new ForwardMancala().findMoves(new int[]{6,1,0,1});
        //new ForwardMancala().findMoves(new int[]{0,10,0,0,0});
        new ForwardMancala().findMoves(new int[]{5,0,0,1,3,0,2,0});
    }
    
    public static void main(String[] args) {
        test();
    }
}
