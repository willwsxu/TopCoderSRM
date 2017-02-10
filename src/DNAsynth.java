
import static java.lang.System.out;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wxu
 */
public class DNAsynth {
    Map<String, Set<String>>    graph=new TreeMap<>();
    public int longest(String[] reactivity)
    {
        for (String elem : reactivity) {
            String [] pair = elem.split("[\\:]");
            graph.compute(pair[0], (k,v)->{
                if (v==null) {
                    Set<String> s = new TreeSet<>();
                    s.add(pair[1]);
                    return s;
                } else
                {
                    v.add(pair[1]);
                    return v;
                }
            });
            
            // reverse reactivity mapping
            graph.compute(new StringBuilder(pair[1]).reverse().toString(), (k,v)->{
                String val = new StringBuilder(pair[0]).reverse().toString();
                if (v==null) {
                    Set<String> s = new TreeSet<>();
                    s.add(val);
                    return s;
                } else
                {
                    v.add(val);
                    return v;
                }
            });
        }
        out.println(graph);
        return -1;
    }
    
    public static void main(String[]args)
    {
        new DNAsynth().longest(new String[]{"TTA:AGG","AGG:CCC"});
    }
}
