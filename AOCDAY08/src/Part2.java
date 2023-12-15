import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("C:\\Users\\Sami\\IdeaProjects\\AOC\\AOCDAY8\\src\\input.txt");
        Scanner scanner = new Scanner (myObj);

        File myObj2 = new File("C:\\Users\\Sami\\IdeaProjects\\AOC\\AOCDAY8\\src\\directions.txt");
        Scanner scanner2 = new Scanner (myObj2);
        String directions = scanner2.nextLine();

        Graph graph = new Graph();
        List<String> startingPoints = new ArrayList<>();

        while(scanner.hasNext()) {
            String line = scanner.nextLine();

            String[] parts = line.split("\\s*=\\s*|\\(|\\)|,\\s*");

            String source = parts[0];
            String left = parts[2];
            String right = parts[3];

            if(source.charAt(2) == 'A') startingPoints.add(source);

            graph.addEdge(source, left);
            graph.addEdge(source, right);
        }


        List<Long> pathLengths = new ArrayList<>();

        for(int j = 0; j < startingPoints.size(); j++) {
            int i = 0;
            long steps = 0;
            String location = startingPoints.get(j);
            while(location.charAt(2) != 'Z') {
                if(i == directions.length()) {
                    i = 0;
                }
                char c = directions.charAt(i);
                LinkedList<String> vals = graph.map.get(location);
                if(c == 'L') {
                    location = vals.getFirst();
                }
                else {
                    location = vals.getLast();
                }
                i++;
                steps++;
            }
            pathLengths.add(steps);
        }


        System.out.println(lcm(pathLengths));
    }

    static long lcm(List<Long> numbers)
    {
        return numbers.stream().reduce(
                1L, (x, y) -> (x * y) / gcd(x, y));
    }

    static long gcd(long a, long b)
    {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}


