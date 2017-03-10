
import static java.lang.System.out;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wxu
 */
public class Combinatoric {
    boolean permute[]=new boolean[4]; // toggle between true and false
    
    int test=0;
    void doStuff()
    {
        for (int i=0; i<permute.length; i++)
            out.print(permute[i]?'1':'0');
        out.println();
    }
    void perm(int i, boolean val)
    { 
        if ( i==permute.length)
            return;
        permute[i]=val;
        doStuff();
        perm(i+1, true);
        perm(i+1, false);
    }
    void perm2()
    {
        for (int i=0; i<permute.length; i++) {
            
        }
    }
    
    void binaryCombo(int i, int size)
    {
        if ( i==size)
            return;
        binaryCombo(i+1, size);
        doStuff();
    }
    
}
