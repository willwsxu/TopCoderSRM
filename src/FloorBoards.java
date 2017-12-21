
import static java.lang.Integer.min;
import static java.lang.System.out;
import java.util.Arrays;

/*
 * SRM 383 DIV1, 500 points, dp with profile
 * Room of dimension nxm, 1<=n,m<=10, each square is either empty (.) or a pillar (#)
 * Fill room with boards with width 1, length of any positive number
 * Find the minimal boards needed
 */

public class FloorBoards {
    static final int PILLAR='#';
    int row, col;
    String[] room;
    public int layout(String[] room)
    {
        row = room.length;
        col = room[0].length();
        PROFILE = 1<<col; // profile of columns on previous row
        dp=new int[row][PROFILE];
        for (int[] r: dp)
            Arrays.fill(r, -1);
        this.room=room;
        return fillByRow(0,0);
    }
    int PROFILE;  // max value of profile
    int dp[][];     // profile bit 1 means to use vertical board at the position
    boolean bitSet(int p, int b)  // check if int p bit b is 1
    {
        return ((p>>b) & 1)>0;
    }
    boolean isValid(int profile, String r)  // check if any vertical board is on cell with #
    {
        for (int i=0; i<col; i++) {  // check each cell on this row
            if (bitSet(profile, i) && r.charAt(i)==PILLAR)  // vertical board on # is not allowed
                return false;
        }
        return true;
    }
    int INF=1000000;
    int fillByRow(int r, int mask)
    {
        if (r==row)
            return 0;
        if (dp[r][mask]>=0)
            return dp[r][mask];
        int ans=INF;
        for (int p=0; p<PROFILE; p++)  // try all profile of current row
        {
            if (!isValid(p, room[r]))  // invalid profile for current row due to pillar
                continue;
            // inspect profile on each column, 1 means vertical, 0 is horizontal board
            int addBoard=0;
            boolean horizontal=false;
            for (int j=0; j<col; j++) {
                if (bitSet(p, j))  {      // use vertical board
                    horizontal=false;
                    if (bitSet(mask, j))  // if mask of previous row is vertical
                        continue;         // use same vertical board
                    addBoard++;
                } else if (room[r].charAt(j)==PILLAR) {
                    horizontal=false;
                } else {
                    if (!horizontal)
                        addBoard++;       // add horizontal board if none exist before
                    horizontal=true;      // horizonal board in place, until next 
                }
            }
            ans = min(ans, addBoard+fillByRow(r+1, p));
        }
        return dp[r][mask]=ans;
    }
    
    private static void test()
    {
        String[]room=new String[]{".....",".....",".....",".....","....."};
        out.println(new FloorBoards().layout(room)==5);
        out.println(new FloorBoards().layout(new String[]{"......." ,".#..##." ,".#....." ,".#####." ,".##..#." ,"....###"})==7);
        out.println(new FloorBoards().layout(new String[]{"####" ,"####" ,"####" ,"####"})==0);
        out.println(new FloorBoards().layout(new String[]{"...#.." ,"##...." ,"#.#..." ,".#...." ,"..#..." ,"..#..#"})==9);
        out.println(new FloorBoards().layout(new String[]{".#...." ,"..#..." ,".....#" ,"..##.." ,"......" ,".#..#."})==9);
    }
    public static void main(String[]args)
    {
        test();
    }
}