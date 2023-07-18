import java.sql.SQLOutput;
import java.util.Scanner;

public class Main
{

    static String col_Occupied = "";
    static String[] output = new String[1000];
    static int k = 0, i;

    static void setMatrixZero(int[][] arr, int num)
    {
        for(int i=0;i<num;i++)
            for(int j=0;j<num;j++)
                arr[i][j] = 0;
    }


    static int checkForPresent(int index) {
        int i, flag = 0;
        for (i = 0; i < col_Occupied.length(); i++) {
            char c = Character.forDigit(index, 10);
            if (c == col_Occupied.charAt(i)) {
                flag = 1;
                break;
            }
        }
        return flag;
    }

    static int checkForDiagonalPresent(int[][] checkboard, int row, int col, int num) {
        int i, j, flag = 0;
        //Left Backward Search
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (checkboard[i][j] == 1) {
                flag = 1;
                return flag;
            }
        }
        //Left Forward Search
        for (i = row, j = col; i < num && j < num; i++, j++) {
            if (checkboard[i][j] == 1) {
                flag = 1;
                return flag;
            }
        }
        //Right Backward Search
        for (i = row, j = col; i < num && j >= 0; i++, j--) {
            if (checkboard[i][j] == 1) {
                flag = 1;
                return flag;
            }
        }
        //Right Forward Search
        for (i = row, j = col; i >= 0 && j < num; i--, j++) {
            if (checkboard[i][j] == 1) {
                flag = 1;
                return flag;
            }
        }
        return flag;
    }

    static int getIndex(int[][] checkboard, int row, int num) {
        int i;
        for (i = 0; i < num; i++) {
            if (checkboard[row][i] == 1) {
                return i;
            }
        }
        return -1;
    }

    static int getCount(int[][] checkboard, int num) {
        int i, j, countOne = 0;
        for (i = 0; i < num; i++) {
            for (j = 0; j < num; j++) {
                if(checkboard[i][j] == 1)
                    countOne++;
            }
        }
        return countOne;
    }

    static void displayCheckboard(int[][] checkboard, int num)
    {
        int i, j;
        for(i=0;i<num;i++){
            for(j=0;j<num;j++)
                System.out.print(checkboard[i][j] + "\t");
            System.out.println();
        }
        System.out.println();
    }

    static void setCheckboard(int[][] checkboard, int row, int col, int num)
    {
        int j, flagColCheck, flagDiagonalCheck, flagCheckOne, presentCountOne;
        if(row != num)
        {
            for(j=col;j<num;j++)
            {
                flagColCheck = checkForPresent(j);
                flagDiagonalCheck = checkForDiagonalPresent(checkboard, row, j, num);
                if(flagColCheck == 0){
                    if(flagDiagonalCheck == 0){
                        checkboard[row][j] = 1;
                        col_Occupied = col_Occupied + j;
                        setCheckboard(checkboard, (row+1), 0, num);
                        checkboard[row][j] = 0;
                        col_Occupied = col_Occupied.substring(0, (col_Occupied.length()-1));
                    }
                }
            }
            flagCheckOne = getIndex(checkboard, row, num);
            if(flagCheckOne == -1){
                return;
            }
        }
        else
        {
            System.out.println("Matrix" + ++i);
            displayCheckboard(checkboard, num);
            presentCountOne = getCount(checkboard, num);
            if(presentCountOne == num){
                output[k] = "";
                for (int m = 0; m < num; m++) {
                    int tempIndex = getIndex(checkboard, m, num);
                    output[k] = output[k] + tempIndex;
                }
                k++;
            }
            return;
        }
    }

    public static void main(String[] args) {
        //1 = Queen
        //0 = empty space
        Scanner ob = new Scanner(System.in);
        int row = 0, col = 0;
        int[][] checkboard;
        int m, num;
        while(true){
            System.out.print("Enter Size of CheckBoard (Size >= 4 AND Size <=10): ");
            num = ob.nextInt();
            if(num >= 4 && num <= 10)
                break;
            else
                System.out.println("Wrong Number, enter Again");
        }
        checkboard = new int[num][num];
        setCheckboard(checkboard, row, col, num);
        if(k == 0)
            System.out.println("No Output Present!");
        else{
            //Printing
            for(m = 0; m < k; m++) {
                System.out.println("Output " + (m+1) + ":");
                for(int t=0;t<output[m].length();t++) {
                    int index = Character.getNumericValue(output[m].charAt(t));
                    System.out.println("Location of " + (t + 1) + " Queen is: " + (index + 1));
                }
                System.out.println();
            }
        }
    }
}