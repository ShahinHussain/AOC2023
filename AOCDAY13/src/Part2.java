import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("C:\\Users\\Sami\\IdeaProjects\\AOC\\AOCDAY13\\src\\input.txt");
        Scanner scanner = new Scanner(myFile);

        List<String> lines = new ArrayList<>();
        List<String> vLines = new ArrayList<>();
        List<int[]> results = new ArrayList<>();
        long output = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (!line.isEmpty()) {
                lines.add(line);
            }

            else {
                for(int i = 0; i < lines.get(0).length(); i++) {
                    StringBuilder sb = new StringBuilder();
                    for(String s : lines) {
                        sb.append(s.charAt(i));
                    }
                    vLines.add(sb.toString());
                }

                int hori = 0;
                int vert = 0;
                hori = calc(lines);
                vert = calc(vLines);

                results.add(new int[]{100*hori, vert});
                lines.clear();
                vLines.clear();
            }
        }

        // add remaining grid
        for(int i = 0; i < lines.get(0).length(); i++) {
            StringBuilder sb = new StringBuilder();
            for(String s : lines) {
                sb.append(s.charAt(i));
            }
            vLines.add(sb.toString());
        }
        int horizontal = calc(lines);
        int vertical = calc(vLines);
        results.add(new int[]{100*horizontal, vertical});

        for(int[] arr : results) {
            output += arr[0] + arr[1];
        }
        System.out.println(output);
    }

    private static int calc(List<String> lines) {
        int n = lines.size();
        for (int i = 0; i < n - 1; i++) {
            int changes = 0;
            String lineL = lines.get(i);
            String lineR = lines.get(i + 1);
            if(diffCalc(lineL, lineR) == 0 || diffCalc(lineL, lineR) == 1 ) {
                int l = i, r = i + 1;
                while (l >= 0 && r <= n - 1 && (diffCalc(lines.get(l), lines.get(r)) == 0 || diffCalc(lines.get(l), lines.get(r)) == 1)) {
                    if(diffCalc(lines.get(l), lines.get(r)) == 1) {
                        changes++;
                    }
                    l--;
                    r++;
                }
                if ((l == -1 || r == n) && changes == 1 ) {
                    return i + 1;
                }
            }
        }
        return 0;
    }

    public static int diffCalc(String s1, String s2) {
        int count = 0;
        for(int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}

