import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part1 {
    public static void main(String[] args)  throws FileNotFoundException {
        File myFile = new File("C:\\Users\\Sami\\IdeaProjects\\AOC\\AOCDAY10\\src\\input.txt");
        Scanner scanner = new Scanner(myFile);
        Scanner newScanner = new Scanner(myFile);

        String temp = scanner.nextLine();
        int n = temp.length();
        scanner.close();

        char[][] field = new char[n][n];
        field[0] = temp.toCharArray();

        int lineNum = 0;
        int[] whereS = new int [2];
        while(newScanner.hasNext()) {
            String line = newScanner.nextLine();
            if(line.contains(String.valueOf('S'))) {
                whereS[0] = lineNum;
                whereS[1] = line.indexOf('S');
            }
            field[lineNum] = line.toCharArray();
            lineNum++;
        }

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

        if(directions.get("right").contains(r)) {
            location[1]++;
            lastMove = 'r';
        }
        else if(directions.get("left").contains(l)) {
            location[1]--;
            lastMove = 'l';
        }
        else if(directions.get("up").contains(u)) {
            location[0]--;
            lastMove = 'u';
        }
        else {
            location[0]++;
            lastMove = 'd';
        }

        int steps = 1;
        while(location[0] != whereS[0] || location[1] != whereS[1]) {
            char currentChar = field[location[0]][location[1]];

            if(currentChar == '7') {
                // bigGrid[2*loc[0]][2*loc[1]]
                if(lastMove == 'r') {
                    location[0]++;
                    lastMove = 'd';
                }
                else {
                    location[1]--;
                    lastMove = 'l';
                }
            }
            else if(currentChar == 'F') {
                if(lastMove == 'l') {
                    location[0]++;
                    lastMove = 'd';
                }
                else {
                    location[1]++;
                    lastMove = 'r';
                }
            }
            else if(currentChar == 'J') {
                if(lastMove == 'd') {
                    location[1]--;
                    lastMove = 'l';
                }
                else {
                    location[0]--;
                    lastMove = 'u';
                }
            }
            else if (currentChar == '|') {
                if(lastMove == 'd') {
                    location[0]++;
                    lastMove = 'd';
                }
                else {
                    location[0]--;
                    lastMove = 'u';
                }
            }
            else if(currentChar == '-') {
                if(lastMove == 'r') {
                    location[1]++;
                    lastMove = 'r';
                }
                else {
                    location[1]--;
                    lastMove = 'l';
                }
            }
            else {
                if(lastMove == 'd') {
                    location[1]++;
                    lastMove = 'r';
                }
                else {
                    location[0]--;
                    lastMove = 'u';
                }
            }
            steps++;
        }

        if(steps %2 == 1) {
            System.out.println((steps - 1)/2);
        }
        else {
            System.out.println(steps/2);
        }









//        while(location[0] != whereS[0] || location[1] != whereS[1] || steps == 0) {
//
//            char r = field[location[0]][location[1] + 1];
//            char l = field[location[0]][location[1] - 1];
//            char u = field[location[0] - 1][location[1]];
//            char d = field[location[0] + 1][location[1]];
//
//            if(directions.get("right").contains(r)) {
//                if (r == '7') {
//                    if(lastMove == 'R') {
//
//                    }
//                }
//                else if(r == '-') {location[1]++;}
//                else {location[0]++; location[1]--;}
//                lastMove = 'r';
//            }
//            else if(directions.get("left").contains(l) && lastMove != 'r') {
//                if(l == 'L') {location[0]--; location[1]--;}
//                else if(l == '-') location[1]--;
//                else {location[0]++; location[1]--;}
//                lastMove = 'l';
//            }
//            else if(directions.get("up").contains(u) && lastMove != 'd') {
//                if(l == '|') location[0]--;
//                else if (l == '7') {location[0]--; location[1]--;}
//                else {location[0]--; location[1]++;}
//                lastMove = 'u';
//            }
//            else {
//                if(lastMove != 'u') {
//                    if(l == '|') location[0]++;
//                    else if(l == 'L') {location[0]++; location[1]++;}
//                    else {location[0]++; location[1]--;}
//                    lastMove = 'd';
//                }
//            }
//            steps+=2;
//        }


    }
}
