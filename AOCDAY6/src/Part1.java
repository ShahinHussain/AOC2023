import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part1 {
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

        List<Integer[]> races = new ArrayList<>();

        for(int i = 0; i < 4; i++) {
            Integer[] race = {times.get(i), distances.get(i)};
            races.add(race);
        }


        int output = 1;

        for(Integer[] race : races) {
            int time = race[0];
            int distance = race[1];
            output *= possibleWays(time, distance);
        }

        System.out.println(output);

    }

    public static int possibleWays(int time, int distance) {
        int min = 0;
        int timeRemain = time - min;

        while(min * timeRemain < distance) {
            min++;
            timeRemain = time - min;
        }

        return time - (2 * min) + 1;
    }
}
