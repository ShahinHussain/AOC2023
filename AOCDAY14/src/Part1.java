import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {
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

        tilt(n, grid);

        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < n; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }

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

        System.out.println(output);
    }

    private static void tilt(int n, char[][] grid) {
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
    }
}
