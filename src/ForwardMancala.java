

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
    int findMax(int m[])
    {
        int max=0;
        for(int i=0; i<m.length; i++) {
            if (max<m[i])
                max = m[i];
        }
        //out.println("max "+max);
        return max;
    }
    int skipZero(int start[], int pos)
    {        
        for (int i=0; i<start.length; i++) {
            if ( start[pos]>0 )
                break;
            pos = (pos+1)%start.length;
        }
        return pos;
    }
    int move(int start[], int pos)
    {
        //out.println("pos "+pos);
        int stones = start[pos];
        start[pos]=0;
        int next = (pos+1)% start.length;
        for (int i=0; i<stones; i++) {
            pos = (pos+1)%start.length;
            start[pos]++;
        }
        //print(start);
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
        //out.println("max "+max);
        //out.println("total "+total);
        if (max==total)
            return new int[]{};
        
        ArrayList<Integer> moves = new ArrayList<>(100);
        // first move
        ++pos;
        while (moves.size()<2500) {
            pos = skipZero(start, pos);
            moves.add(pos);
            pos = move(start, pos);
            if (start[pos]==total)
                break;
            if (start[pos]==findMax(start) && start[pos] != 1) {
                int next = (pos+1)%start.length;
                if ( start[pos]>start[next])  // skip only if next item is not same
                    pos = next;
            }
        }
        int a[]=new int[moves.size()];
        for (int i=0; i<moves.size(); i++) {
            a[i] = moves.get(i);
        }
        out.println(moves.size()+": "+moves);
        return a;
    }
    
    static void test()
    {
        new ForwardMancala().findMoves(new int[]{6,1,0,1});
        new ForwardMancala().findMoves(new int[]{0,10,0,0,0});
        new ForwardMancala().findMoves(new int[]{0,1,0,1,0,1});
        new ForwardMancala().findMoves(new int[]{5,0,0,1,3,0,2,0});
        new ForwardMancala().findMoves(new int[]{10,10,10,10,10,10,10,10,10,10});
    }
    
    public static void main(String[] args) {
        test();
    }
}
