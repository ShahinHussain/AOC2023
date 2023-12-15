import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("input.txt");
        Scanner scanner = new Scanner(myFile);

        long result = 0;

        char[][] input = new char[140][140];

        for (int i = 0; i < 140; i++) {
            String currentLine = scanner.nextLine();
            for (int j = 0; j < 140; j++) {
                input[i][j] = currentLine.charAt(j);
            }
        }

        int[] coord = new int[2];

        for (int i = 0; i < 140; i++) {
            for (int j = 0; j < 140; j++) {
                if (input[i][j] == '*') {
                    coord[0] = i;
                    coord[1] = j;

                    int prodProxNums = nearbyNums(coord, input);
                    if(prodProxNums != 0) {
                        result += prodProxNums;
                    }
                }
            }
        }

        System.out.println(result);
    }

    public static int nearbyNums(int[] firstDigitCoord, char[][] input) {
        int size = input[0].length;
        int[][] proxCells = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        int i = firstDigitCoord[0];
        int j = firstDigitCoord[1];

        for (int[] coord : proxCells) {
            int m = i + coord[0], n = j + coord[1];
            if ((m >= 0 && m < size) && (n >= 0 && n < size)) {
                char seenChar = input[m][n];
                if (Character.isDigit(seenChar)) {
                    sb.append(seenChar);
                    int l = 1, r = 1;
                    // check left
                    while ((n - l) >= 0 && Character.isDigit(input[m][n - l])) {
                        sb.insert(0, input[m][n - l]);
                        l++;
                    }
                    // check right
                    while ((n + r) < size && Character.isDigit(input[m][n + r])) {
                        sb.append(input[m][n + r]);
                        r++;
                    }

                    int lastDig = n + r - 1;

                    List<Integer> rowNumLastDigitCol = new ArrayList<>();
                    rowNumLastDigitCol.add(m);
                    rowNumLastDigitCol.add(lastDig);


                    int num = Integer.parseInt(sb.toString());

                    if(!map.containsKey(num)) {
                        List<List<Integer>> val = new ArrayList<>();
                        val.add(rowNumLastDigitCol);
                        map.put(num, val);
                    }
                    else {
                        boolean bool = true;
                        for(List<Integer> list : map.get(num)) {
                            if(list.get(0) == m && list.get(1) == lastDig) {
                                bool = false;
                            }
                        }
                        if(bool) {
                            List<List<Integer>> numVal = map.get(num);
                            numVal.add(rowNumLastDigitCol);
                            map.replace(num, numVal);
                        }
                    }

                }
            }
            sb.setLength(0);
        }

        int count = 0;
        int result = 1;

        for(int n : map.keySet()) {
            for(List<Integer> list : map.get(n)) {
                count++;
                result *= n;
            }
        }

        if(count == 2) {
            return result;
        }
        else return 0;
    }
}

