import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("C:\\Users\\Sami\\IdeaProjects\\AOC\\AOCDAY10\\src\\input.txt");
        Scanner scanner = new Scanner(myFile);
        Scanner newScanner = new Scanner(myFile);

        String temp = scanner.nextLine();
        int n = temp.length();
        scanner.close();

        // scaled up grid
        char[][] bigField = new char[2 * n][2 * n];


        // fill grid with .'s
        char[][] field = new char[n][n];
        for (int i = 0; i < bigField.length; i++) {
            for (int j = 0; j < bigField[0].length; j++) {
                bigField[i][j] = '.';
            }
        }

        field[0] = temp.toCharArray();

        int lineNum = 0;
        int[] whereS = new int[2];
        while (newScanner.hasNext()) {
            String line = newScanner.nextLine();
            if (line.contains(String.valueOf('S'))) {
                whereS[0] = lineNum;
                whereS[1] = line.indexOf('S');
            }
            field[lineNum] = line.toCharArray();
            lineNum++;
        }

        bigField[whereS[0]][whereS[1]] = 'S';

        Map<String, List<Character>> directions = new HashMap<>();
        Character[] down = {'|', 'L', 'J'};
        Character[] up = {'|', '7', 'F'};
        Character[] right = {'7', '-', 'J'};
        Character[] left = {'L', '-', 'F'};
        directions.put("down", Arrays.asList(down));
        directions.put("up", Arrays.asList(up));
        directions.put("left", Arrays.asList(left));
        directions.put("right", Arrays.asList(right));


        int[] location = {whereS[0], whereS[1]};

        char lastMove = ' ';

        char r = field[location[0]][location[1] + 1];
        char l = field[location[0]][location[1] - 1];
        char u = field[location[0] - 1][location[1]];
        char d = field[location[0] + 1][location[1]];

        if (directions.get("right").contains(r)) {
            location[1]++;
            lastMove = 'r';
        } else if (directions.get("left").contains(l)) {
            location[1]--;
            lastMove = 'l';
        } else if (directions.get("up").contains(u)) {
            location[0]--;
            lastMove = 'u';
        } else {
            location[0]++;
            lastMove = 'd';
        }

        int steps = 1;
        while (location[0] != whereS[0] || location[1] != whereS[1]) {
            char currentChar = field[location[0]][location[1]];

            if (currentChar == '7') {
                bigField[2 * location[0]][2 * location[1]] = '7';
                bigField[2 * location[0] + 1][2 * location[1]] = '|';
                bigField[2 * location[0]][2 * location[1] - 1] = '-';
                if (lastMove == 'r') {
                    location[0]++;
                    lastMove = 'd';
                } else {
                    location[1]--;
                    lastMove = 'l';
                }
            } else if (currentChar == 'F') {
                bigField[2 * location[0]][2 * location[1]] = 'F';
                bigField[2 * location[0]][2 * location[1] + 1] = '-';
                bigField[2 * location[0] + 1][2 * location[1]] = '|';

                if (lastMove == 'l') {
                    location[0]++;
                    lastMove = 'd';
                } else {
                    location[1]++;
                    lastMove = 'r';
                }
            } else if (currentChar == 'J') {
                bigField[2 * location[0]][2 * location[1]] = 'J';
                bigField[2 * location[0]][2 * location[1] - 1] = '-';
                bigField[2 * location[0] - 1][2 * location[1]] = '|';
                if (lastMove == 'd') {
                    location[1]--;
                    lastMove = 'l';
                } else {
                    location[0]--;
                    lastMove = 'u';
                }
            } else if (currentChar == '|') {
                bigField[2 * location[0]][2 * location[1]] = '|';
                bigField[2 * location[0] - 1][2 * location[1]] = '|';
                bigField[2 * location[0] + 1][2 * location[1]] = '|';
                if (lastMove == 'd') {
                    location[0]++;
                    lastMove = 'd';
                } else {
                    location[0]--;
                    lastMove = 'u';
                }
            } else if (currentChar == '-') {
                bigField[2 * location[0]][2 * location[1]] = '-';
                bigField[2 * location[0]][2 * location[1] - 1] = '-';
                bigField[2 * location[0]][2 * location[1] + 1] = '-';
                if (lastMove == 'r') {
                    location[1]++;
                    lastMove = 'r';
                } else {
                    location[1]--;
                    lastMove = 'l';
                }
            } else {
                bigField[2 * location[0]][2 * location[1]] = 'L';
                bigField[2 * location[0]][2 * location[1] + 1] = '-';
                bigField[2 * location[0] - 1][2 * location[1]] = '|';
                if (lastMove == 'd') {
                    location[1]++;
                    lastMove = 'r';
                } else {
                    location[0]--;
                    lastMove = 'u';
                }
            }
            steps++;
        }

        floodFill(bigField, 0, 0, 'O');


        char[][] newField = new char[field.length][field.length];

        for (int i = 0; i < newField.length; i++) {
            for (int j = 0; j < newField.length; j++) {
                newField[i][j] = bigField[2 * i][2 * j];
            }
        }

        int oS = 0;
        for (int i = 0; i < newField.length; i++) {
            for (int j = 0; j < newField[0].length; j++) {
                if (newField[i][j] == 'O') {
                    oS++;
                }
            }
        }

        int result = (newField.length * newField.length) - steps - oS;
        System.out.println(result);

    }

        public static void floodFill(char[][] grid, int i, int j, char newChar) {
            int n = grid.length;
            char oldChar = grid[i][j];
            if (oldChar != '.') {
                return;
            }
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{i, j});
            while (!queue.isEmpty()) {
                int[] arr = queue.poll();
                i = arr[0];
                j = arr[1];
                if (i < 0 || i >= n || j < 0 || j >= n || grid[i][j] != '.') {
                    continue;
                } else {
                    grid[i][j] = newChar;
                    queue.add(new int[]{i + 1, j});
                    queue.add(new int[]{i - 1, j});
                    queue.add(new int[]{i, j + 1});
                    queue.add(new int[]{i, j - 1});
                }
            }
        }
}

