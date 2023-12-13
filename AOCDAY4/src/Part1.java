import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("C:\\Users\\Sami\\IdeaProjects\\AOC\\AOCDAY4\\src\\input.txt");
        Scanner scanner = new Scanner(myObj);

        int output = 0;

        while(scanner.hasNext()) {
            String data = scanner.nextLine();
            int n = data.length();

            List<Integer> winningNums = new ArrayList<>();
            Set<Integer> myNums = new HashSet<>();

            String winningNumsString = data.substring(10, 39).trim();
            String[] numsArrayString = winningNumsString.split("\\s+");
            for(String s : numsArrayString) {
                winningNums.add(Integer.parseInt(s));
            }

            String myNumsString = data.substring(42, n).trim();
            String[] myNumsArrayString = myNumsString.split("\\s+");
            for(String s : myNumsArrayString) {
                myNums.add(Integer.parseInt(s));
            }

            int result = 0;
            for(int i : winningNums) {
                if(myNums.contains(i)){
                    if(result == 0) {
                        result = 1;
                    }
                    else {
                        result *= 2;
                    }
                }
            }
            output += result;
        }
        System.out.println(output);
    }
}
