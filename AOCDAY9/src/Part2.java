import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("C:\\Users\\Sami\\IdeaProjects\\AOC\\AOCDAY9\\src\\input.txt");
        Scanner scanner = new Scanner(myObj);

        long output = 0;

        List<Integer> nums = new ArrayList<>();
        List<Integer> lastNums = new ArrayList<>();

        while (scanner.hasNext()) {
            String sequence = scanner.nextLine();
            String[] stringNumbers = sequence.split("\\s+");

            for (String s : stringNumbers) {
                nums.add(0, Integer.parseInt(s));
            }
            
            while(!(nums.get(0) == 0 && nums.get(nums.size() - 1) == 0)) {
                for(int i = 0; i < nums.size() - 1; i++) {
                    int diff = nums.get(i+1) - nums.get(i);
                    nums.set(i, diff);
                }
                int lastEl = nums.remove(nums.size() - 1);
                lastNums.add(lastEl);
            }

            long lastElSum = 0;
            for(int n : lastNums) {
                lastElSum += n;
            }

            output += lastElSum;

            nums.clear();
            lastNums.clear();
        }

        System.out.println(output);

    }
}

