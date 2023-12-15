import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("C:\\Users\\Sami\\IdeaProjects\\AOC\\AOCDAY8\\src\\input.txt");
        Scanner scanner = new Scanner (myObj);

        File myObj2 = new File("C:\\Users\\Sami\\IdeaProjects\\AOC\\AOCDAY8\\src\\directions.txt");
        Scanner scanner2 = new Scanner (myObj2);
        String directions = scanner2.nextLine();

        Graph graph = new Graph();

        while(scanner.hasNext()) {
            String line = scanner.nextLine();

            String[] parts = line.split("\\s*=\\s*|\\(|\\)|,\\s*");

            String source = parts[0];
            String left = parts[2];
            String right = parts[3];

            graph.addEdge(source, left);
            graph.addEdge(source, right);
        }


        String location = "AAA";
        int steps = 0;
        int i = 0;

        while(!location.equals("ZZZ")) {
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

        System.out.println(steps);
    }
}

class Graph {
    HashMap<String, LinkedList<String>> map = new HashMap<>();

    //add an edge from source to destination
    void addEdge(String src, String dest) {
        if (!map.containsKey(src)) {
            LinkedList<String> l = new LinkedList<>();
            l.addLast(dest);
            map.put(src, l);
        } else {
            LinkedList<String> l = map.get(src);
            l.addLast(dest);
            map.put(src, l);
        }
    }

}
