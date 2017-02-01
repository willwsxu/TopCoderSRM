/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andy
 */
// test if it is possible that all letter follow one single order in all words
public class AlphaBetOrder {
    
    public static final String good = "Possible";
    public static final String bad  = "Impossible";
    
    public static String isOrdered(String[] words)
    {
        List<List<Character>> alphaLists= new ArrayList<>();
        for (int i=0; i<words.length; i++) {
            List<Character> charList = new ArrayList<>();
            alphaLists.add(charList);
            String word = words[i];
            for ( int j=0; j<word.length(); j++) {
                int ind = charList.indexOf(word.charAt(j));
                if ( ind<0)
                    charList.add(word.charAt(j));
                else if (ind <charList.size()-1) {
                    System.out.println("Bad "+word);
                    return bad;
                }
            }
        }
        
        for (int i=0; i<alphaLists.size()-1; i++) {
            List<Character> charList = alphaLists.get(i);
            for (int j=i+1; j<alphaLists.size(); j++) { // compare sequence of char agaist each other
                List<Character> charList2 = alphaLists.get(j);     
                int cursor=-1;
                for (int k=0; k<charList2.size(); k++) {
                    int ind = charList.indexOf(charList2.get(k));
                    if ( ind < 0)
                        continue;
                    if (ind < cursor) {
                        System.out.println("word " +(j+1)+" mismatch "+ (i+1)+" at "+(k+1));
                        return bad;
                    }else
                        cursor = k;                    
                }
            }
        }
        return good;
    }
    
    public static void main(String[] args)
    {
        System.out.println(isOrdered(new String[]{"single","round","match"}));
        System.out.println(isOrdered(new String[]{"topcoder","topcoder"}));
        System.out.println(isOrdered(new String[]{"gimnas","y","esgrima","la","plat"}));
    }
}
