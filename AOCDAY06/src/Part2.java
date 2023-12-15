import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("C:\\Users\\Sami\\IdeaProjects\\AOC\\AOCDAY6\\src\\input.txt");
        Scanner scanner = new Scanner(myObj);

        List<Integer> times = new ArrayList<>();

        for(int i = 0; i < 4; i++) {
            times.add(scanner.nextInt());
        }

        List<Integer> distances = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            distances.add(scanner.nextInt());
        }

        String tempTime = "";
        for(int i : times) {
            tempTime += Integer.toString(i);
        }

        String tempDist = "";
        for(int i : distances) {
            tempDist += Integer.toString(i);
        }

        long time = Long.parseLong(tempTime);
        long dist = Long.parseLong(tempDist);

        long output = possibleWays(time, dist);

        System.out.println(output);

    }

    public static long possibleWays(long time, long distance) {
        int min = 0;
        long timeRemain = time - min;

        while(min * timeRemain < distance) {
            min++;
            timeRemain = time - min;
        }

        return time - (2 * min) + 1;
    }
}
