/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wxu
 */

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ABCPath {
    
    int row=0;
    int column=0;
    
    int visitPaths(String[] grid, int i, int j, char curr)
    {
        if (grid[i].charAt(j) != curr)
            return 0;
        int next = curr+1;
        Integer len[]={0,0,0,0,0,0,0,0};
        len[0] = 1+findNextLeft(grid, i, j, (char)next);
        len[1] = 1+findNextRight(grid, i, j, (char)next);
        len[2] = 1+findNextTop(grid, i, j, (char)next);
        len[3] = 1+findNextBottom(grid, i, j, (char)next);
        len[4] = 1+findNextTopLeft(grid, i, j, (char)next);
        len[5] = 1+findNextTopRight(grid, i, j, (char)next);
        len[6] = 1+findNextBottomLeft(grid, i, j, (char)next);
        len[7] = 1+findNextBottomRight(grid, i, j, (char)next);
        return Collections.max(Arrays.asList(len));    
    }
    int findNextLeft(String[] grid, int i, int j, char curr) {
        if (j--==0)
            return 0;
        return visitPaths(grid, i, j, curr);
    }
    int findNextRight(String[] grid, int i, int j, char curr) {
        if (++j==column)
            return 0;
        return visitPaths(grid, i, j, curr);
    }
    int findNextTop(String[] grid, int i, int j, char curr) {
        if (i--==0)
            return 0;
        return visitPaths(grid, i, j, curr);
    }
    int findNextBottom(String[] grid, int i, int j, char curr) {
        if (++i==row)
            return 0;
        return visitPaths(grid, i, j, curr);
    }
    int findNextTopLeft(String[] grid, int i, int j, char curr) {
        if (i--==0)
            return 0;
        if (j--==0)
            return 0;
        return visitPaths(grid, i, j, curr);
    }
    int findNextTopRight(String[] grid, int i, int j, char curr) {
        if (i--==0)
            return 0;
        if (++j==column)
            return 0;
        return visitPaths(grid, i, j, curr);
    }
    int findNextBottomLeft(String[] grid, int i, int j, char curr) {
        if (++i==row)
            return 0;
        if (j--==0)
            return 0;
        return visitPaths(grid, i, j, curr);
    }
    int findNextBottomRight(String[] grid, int i, int j, char curr) {
        if (++i==row)
            return 0;
        if (++j==column)
            return 0;
        return visitPaths(grid, i, j, curr);
    }
    int length(String[] grid)
    {
        row = grid.length;
        if ( row < 1 || row > 50 ) {
            out.println("Bad grid row "+row);
            return 0;
        }
        column = grid[0].length();
        if ( column < 1 || column > 50 ) {
            out.println("Bad grid column "+column);
            return 0;
        }
        out.println("First grid column "+column);
        List<Integer> lens = new ArrayList<>();
        for (int i=0; i<grid.length; i++){
            if ( grid[i].length() != column) {
                out.println("Bad grid column "+grid[i].length());
                return 0;
            }
            for (int j=0; j<column; j++) {
                if (grid[i].charAt(j)=='A') {
                    int len =visitPaths(grid, i, j, 'A');
                    lens.add(len);
                    //out.println("find path "+len);
                }
            }
        }
        if ( lens.size()==0)
            return 0;
        return Collections.max(lens);
    }
    public static void main(String[] args) {
        // TODO code application logic here
        out.println(new ABCPath().length(new String[]{ "ABE", "CFG", "BDH", "ABC" }));
        out.println(new ABCPath().length(new String[]{ "A" }));
        out.println(new ABCPath().length(new String[]{ "BCDEFGHIJKLMNOPQRSTUVWXYZ" }));
        out.println(new ABCPath().length(new String[]{ "C", "D", "B", "A" }));
        String str[] ={ "KCBVNRXSPVEGUEUFCODMOAXZYWEEWNYAAXRBKGACSLKYRVRKIO", "DIMCZDMFLAKUUEPMPGRKXSUUDFYETKYQGQHNFFEXFPXNYEFYEX", "DMFRPZCBOWGGHYAPRMXKZPYCSLMWVGMINAVRYUHJKBBRONQEXX", "ORGCBHXWMTIKYNLFHYBVHLZFYRPOLLAMBOPMNODWZUBLSQSDZQ", "QQXUAIPSCEXZTTINEOFTJDAOBVLXZJLYOQREADUWWSRSSJXDBV", "PEDHBZOVMFQQDUCOWVXZELSEBAMBRIKBTJSVMLCAABHAQGBWRP", "FUSMGCSCDLYQNIXTSTPJGZKDIAZGHXIOVGAZHYTMIWAIKPMHTJ", "QMUEDLXSREWNSMEWWRAUBFANSTOOJGFECBIROYCQTVEYGWPMTU", "FFATSKGRQJRIQXGAPLTSXELIHXOPUXIDWZHWNYUMXQEOJIAJDH", "LPUTCFHYQIWIYCVOEYHGQGAYRBTRZINKBOJULGYCULRMEOAOFP", "YOBMTVIKVJOSGRLKTBHEJPKVYNLJQEWNWARPRMZLDPTAVFIDTE", "OOBFZFOXIOZFWNIMLKOTFHGKQAXFCRZHPMPKGZIDFNBGMEAXIJ", "VQQFYCNJDQGJPYBVGESDIAJOBOLFPAOVXKPOVODGPFIYGEWITS", "AGVBSRLBUYOULWGFOFFYAAONJTLUWRGTYWDIXDXTMDTUYESDPK", "AAJOYGCBYTMXQSYSPTBWCSVUMNPRGPOEAVVBGMNHBXCVIQQINJ", "SPEDOAHYIDYUJXGLWGVEBGQSNKCURWYDPNXBZCDKVNRVEMRRXC", "DVESXKXPJBPSJFSZTGTWGAGCXINUXTICUCWLIBCVYDYUPBUKTS", "LPOWAPFNDRJLBUZTHYVFHVUIPOMMPUZFYTVUVDQREFKVWBPQFS", "QEASCLDOHJFTWMUODRKVCOTMUJUNNUYXZEPRHYOPUIKNGXYGBF", "XQUPBSNYOXBPTLOYUJIHFUICVQNAWFMZAQZLTXKBPIAKXGBHXX" };
        out.println(new ABCPath().length(str));
        out.println(new ABCPath().length(new String[]{ "EDCCBA", "EDCCBA" }));
        out.println(new ABCPath().length(new String[]{ "AMNOPA", "ALEFQR", "KDABGS", "AJCHUT", "AAIWVA", "AZYXAA" }));
    }
}
