
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
    Set<String> allSeq = new TreeSet<>(); // contain all SEQ fragments
    
    // seq1:seq2, any DNA start with seq2 can be appended to DNA ends with seq1
    private void addToMap(String seq1, String seq2)
    {
        graph.compute(seq1, (k,v)->{
            if (v==null)
                v = new TreeSet<>();
            v.add(seq2);
            // any seq begin with seq2 can be connected to seq1
            for (String seq : allSeq)
                if (seq.startsWith(seq2))
                    v.add(seq);
            return v;
        });        
    }
    public int longest(String[] reactivity)
    {
        for (String elem : reactivity) {            
            String [] pair = elem.split("[\\:]");
            for (String e: pair) {
                allSeq.add(e);
                allSeq.add(new StringBuilder(e).reverse().toString());
            }
        }
        for (String elem : reactivity) {
            String [] pair = elem.split("[\\:]");
            addToMap(pair[0], pair[1]);            
            // reverse reactivity mapping
            addToMap(new StringBuilder(pair[1]).reverse().toString(), 
                    new StringBuilder(pair[0]).reverse().toString());           
        }
        out.println(graph);
        
        graph.forEach((k,v)->{
        for (String seq2 : v)  // start with any initial pair
            fullSearch(k+seq2);});
        out.println(longDNA+":"+longDNA.length()+(loop?":infinit":""));

        if (loop)
            return -1;
        return longDNA.length();
    }
    String longDNA="";
    boolean loop=false;
    void fullSearch(String DNA)
    {
        if (loop) {  // skip 
            longDNA="";
            return;
        }
        graph.forEach((k,v)->{
            if (DNA.endsWith(k))
            {   // if DNA ends with seq1, append seq2, unless
                for (String seq2: v) { // DNA also start with seq2, it is infinit loop
                    if (DNA.startsWith(seq2)) {
                        longDNA="";
                        loop = true;
                        return;
                    }
                    if (loop) {  // skip 
                        return;
                    }
                    fullSearch(DNA+seq2);
                }
            }else {
                if (loop) {  // skip 
                    return;
                }
                // if DNA starts with seq2, prepend seq1
                for (String seq2: v) {
                    if (DNA.startsWith(seq2)) {
                        fullSearch(k+DNA);
                    }
                }
                if (DNA.length()>longDNA.length())
                    longDNA = DNA;
            }
        });
    }
    
    public static void main(String[]args)
    {
        new DNAsynth().longest(new String[]{"TTA:AGG"});
        new DNAsynth().longest(new String[]{"TTA:AGG","AGG:CCC"});
        new DNAsynth().longest(new String[]{"CCC:AAA","AAA:CCC"});
        new DNAsynth().longest(new String[]{"AG:AC","AT:GC","GC:AG"});
        new DNAsynth().longest(new String[]{"TAG:ATC","GCA:CCCT","GAT:AC"});
        new DNAsynth().longest(new String[]{"TG:GA","CGGG:TGG","GGA:AAAC"});
        new DNAsynth().longest(new String[]{"CCCA:TGGG","TGG:GGA","GGGA:CCCA"});
    }
}
