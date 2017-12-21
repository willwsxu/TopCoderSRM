
import static java.lang.Integer.max;
import static java.lang.System.out;
import java.util.Arrays;

/*
 * SRM 444 DIV1, 500 points, dp with profile
 * Game is played on board of 1x1 squares. '1' blocks are 1x1, and '4' blocks are 2x2
 * The final score is the sum of the values in each cell. '1' blocks are worth 1 point, 
 * and '4' blocks are worth 16 points because they cover 4 cells and each cell is worth 4 points.
 * Given board with some cells covered by 1 block,Return the maximum score that can be achieved
 */

public class FourBlocks {
    
    int row, col;  // 1<=row<=10, 1<=col<=25
    String[] grid;
    int colMask[]; // pre-existing 1 block, per column
    public int maxScore(String[] grid)  // grid element contain . or 1
    {
        row = grid.length;
        col = grid[0].length();
        this.grid=grid;
        PROFILE = 1<<row;
        dp = new int[col][PROFILE];  // state per column, and profile of row of previous column
        for (int[] r: dp)
            Arrays.fill(r, -1);
        colMask=new int[col];
        for (int j=0; j<col; j++) {            
            for (int i=0; i<row; i++) {
                if (grid[i].charAt(j)=='1')
                    colMask[j] |= (1<<i);
            }
        }
        return backtracking(0, 0);
    }
    int PROFILE;    // max value of profile
    int dp[][];     // bit of profile: cells occupied by 4 block in previous column
    boolean bitSet(int p, int b)  // check if int p bit b is 1
    {
        return ((p>>b) & 1)>0;
    }
    boolean valid(int profile, int c)
    {
        for (int r=0; r<row; r++) {  // check each cell on this col
            if (!bitSet(profile, r)) 
                continue;
            if (c>=col-1)  // 4 block is not allowed on last column
                return false;
            if (r>=row-1)  // 4 block is not allowed on last row
                return false;
            if (!bitSet(profile, r+1))
                return false;   // cannot put another 4 block in next row
            if (grid[r].charAt(c)=='1' || grid[r].charAt(c+1)=='1')
                return false;  // 1 block exist
            if (grid[r+1].charAt(c)=='1' || grid[r+1].charAt(c+1)=='1')
                return false;  // 1 block exist
        }
        return true;        
    }
    int countBit(int p)
    {
        int c=0;
        while (p>0) {
            if ( (p&1)>0)
                c++;
            p >>= 1;
        }
        return c;
    }
    int nINF=-999999999;
    int backtracking(int c, int mask) // fill blocks by column
    {
        if (c==col) {
            if (mask>0)  //cannot allow 4 block in last column
                return nINF;
            return 0;
        }
        if ((mask & colMask[c])>0) // previous 4 block on existing 1 block on this column
            return nINF;
        if ( mask>=PROFILE) {
            out.println("error "+c+" "+mask);
            return nINF;
        }
        if (dp[c][mask]>=0)
            return dp[c][mask];
        int ans=0;
        for (int p=0; p<PROFILE>>1; p++) {  // only need to check row-1 as last row cannot have 4 block
            if ((p & (p<<1))>0)  // don't allow consecutive 1 bit
                continue;
            int newMask=p | (p<<1);
            if ( (mask & newMask) >0) // 4 block from previous col overlap current 4 block
                continue;
            if ( (newMask & colMask[c])>0 )  // 4 block cannot be on square with '1'
                continue;    // check next column at beginning of recursive call
            int val=countBit(p)*16;  // count 4 block, only from starting column
            val += (row-countBit(newMask|mask)); // add 1 block, exclude 4 block from prev and curr col
            ans = max(ans, val+backtracking(c+1, newMask));
        }
        return dp[c][mask]=ans;
    }
    private static void test()
    {
        out.println(new FourBlocks().maxScore(new String[]{".....1..1..", "..1.....1.."})==70);
        out.println(new FourBlocks().maxScore(new String[]{"...1.", ".....", ".1..1", ".....", "1...."})==73);
        out.println(new FourBlocks().maxScore(new String[]{"...1.", ".1...", "..1.1", "1...."})==20);
        out.println(new FourBlocks().maxScore(new String[]{".....1...", ".....1...", "111111111", ".....1...", ".....1..."})==117);
    }
    public static void main(String[]args)
    {
        test();
    }
}
