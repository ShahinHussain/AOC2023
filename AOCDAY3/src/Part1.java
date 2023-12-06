import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("input.txt");
        Scanner scanner = new Scanner(myFile);

        int result = 0;

        char[][] input = new char[140][140];

        for (int i = 0; i < 140; i++) {
            String currentLine = scanner.nextLine();
            for (int j = 0; j < 140; j++) {
                input[i][j] = currentLine.charAt(j);
            }
        }

        StringBuilder numSB = new StringBuilder();
        int numLength = 0;
        int[] firstDigitCoord = new int[2];

        for (int i = 0; i < 140; i++) {
            for (int j = 0; j < 140; j++) {
                if (Character.isDigit(input[i][j])) {
                    firstDigitCoord[0] = i;
                    firstDigitCoord[1] = j;
                    while (Character.isDigit(input[i][j]) && j < 140) {
                        numSB.append(input[i][j]);
                        numLength++;
                        if(j == 139) break;
                        else j++;
                    }
                    if(symbolNearby(firstDigitCoord, numLength, input)) {
                        result += Integer.parseInt(numSB.toString());
                    }
                    numSB.setLength(0);
                    numLength = 0;
                }
            }
        }

        System.out.println(result);
    }

    public static boolean symbolNearby(int[] firstDigitCoord, int length, char[][] input) {
        int[][] lengthOneProx = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        int[][] lengthTwoProx = {{-1, -1}, {-1, 0}, {-1, 1}, {-1, 2}, {0, -1}, {0, 2}, {1, -1}, {1,0}, {1, 1}, {1, 2}};
        int[][] lengthThreeProx = {{-1, -1}, {-1, 0}, {-1, 1}, {-1, 2}, {-1, 3}, {0, -1}, {0, 3}, {1, -1},{1,0}, {1, 1}, {1, 2}, {1, 3}};


        int i = firstDigitCoord[0];
        int j = firstDigitCoord[1];

        if (length == 1) {
            for (int[] coord : lengthOneProx) {
                if((i + coord[0] >= 0 && i + coord[0] <= 139) && (j + coord[1] >= 0 && j + coord[1] <= 139)) {
                    if(isSymbol(input[i + coord[0]][j + coord[1]])) {
                        return true;
                    }
                }
            }
        }
        else if (length == 2) {
            for (int[] coord : lengthTwoProx) {
                if((i + coord[0] >= 0 && i + coord[0] <= 139) && (j + coord[1] >= 0 && j + coord[1] <= 139)) {
                    if(isSymbol(input[i + coord[0]][j + coord[1]])) {
                        return true;
                    }
                }
            }
        }
        else {
            for (int[] coord : lengthThreeProx) {
                if((i + coord[0] >= 0 && i + coord[0] <= 139) && (j + coord[1] >= 0 && j + coord[1] <= 139)) {
                    if(isSymbol(input[i + coord[0]][j + coord[1]])) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static boolean isSymbol(char c) {
        if (c == '.') {
            return false;
        }
        else
            return !Character.isLetterOrDigit(c);
    }
}
