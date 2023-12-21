import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("C:\\Users\\Sami\\IdeaProjects\\AOC\\AOCDAY14\\src\\input.txt");
        Scanner scanner = new Scanner(myFile);

        int n = 100;
        char[][] grid = new char[n][n];

        int lineNum = 0;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            grid[lineNum] = line.toCharArray();
            lineNum++;
        }


        int k = 1;
        char[][] temp = grid;
        char[][] curr = new char[n][n];

        while(k < 1000000000) {
            for(int l = 1; l <= 4; l++) {
                temp = tilt(temp.length, temp);
                temp = rotate(temp);
            }
            if(Arrays.deepEquals(temp, curr)) {
                break;
            }
            curr = Arrays.copyOf(temp, n);
            k++;
        }

        System.out.println(k);
        System.out.println(calc(temp));
    }


//        for(int i = 1; i <=10000; i++) {
//            for(int l = 1; l <= 4; l++) {
//                tilt(grid.length, grid);
//                grid = rotate(grid);
//            }
//            if(k % 2 == 1) {
//                prev = calc(grid);
//            }
//            else {
//                curr = calc(grid);
//            }
//            if(prev == curr) {
//                break;
//            }
//        }


    private static char[][] tilt(int n, char[][] grid) {
        for (int i = 0; i < n; i++) {
            int count = 0;
            int j = n - 1;
            while (j >= 0) {
                char current = grid[j][i];
                if (count == 0 && j != 0) {
                    if (current == '#' || current == '.') {
                        j--;
                        continue;
                    } else {
                        grid[j][i] = '.';
                        count++;
                    }
                    j--;
                }
                else {
                    if (current == '#') {
                        int k = j;
                        while (count > 0 && k >= 0) {
                            grid[k + 1][i] = 'O';
                            count--;
                            k++;
                        }
                    }
                    else if (current == 'O') {
                        if (j == 0) {
                            int k = j;
                            while (count > 0 && k >= 0) {
                                grid[k + 1][i] = 'O';
                                count--;
                                k++;
                            }
                        } else {
                            grid[j][i] = '.';
                            count++;
                        }
                    }
                    if (current == '.' && j == 0) {
                        int k = j;
                        while (count > 0 && k >= 0) {
                            grid[k][i] = 'O';
                            count--;
                            k++;
                        }
                    }
                    j--;
                }
            }
        }
        return grid;
    }
    public static char[][] rotate(char[][] grid) {
        int n = grid.length;
        char[][] arr = new char[grid.length][grid.length];
        for(int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = n - 1; j >= 0; j--) {
                sb.append(grid[j][i]);
            }
            arr[i] = sb.toString().toCharArray();
        }
        return arr;
    }

    public static long calc(char[][] grid) {
        int n = grid.length;
        long output = 0;
        for(int i = 0; i < n; i ++) {
            int count = 0;
            char[] arr = grid[i];
            for(int j = 0; j < n ; j++) {
                if(arr[j] == 'O') {
                    count++;
                }
            }
            output += ((long) count * (n - i));
        }
        return output;
    }
}
