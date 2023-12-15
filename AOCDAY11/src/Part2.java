import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("C:\\Users\\Sami\\IdeaProjects\\AOC\\AOCDAY11\\src\\input.txt");
        Scanner scanner = new Scanner(myFile);
        Scanner newScanner = new Scanner(myFile);

        String temp = scanner.nextLine();
        char[][] grid = new char[temp.length()][temp.length()];
        scanner.close();

        List<int[]> gals = new ArrayList<>();
        int lineNum = 0;
        while (newScanner.hasNext()) {
            String line = newScanner.nextLine();
            grid[lineNum] = line.toCharArray();
            for(int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '#') {
                    gals.add(new int[] {lineNum, i});
                }
            }
            lineNum++;
        }

        Map<String, List<Integer>> rcmap = new HashMap<>();
        rcmap.put("rows", new ArrayList<>());
        rcmap.put("cols", new ArrayList<>());
        for (int[] gal : gals) {
            rcmap.get("rows").add(gal[0]);
            rcmap.get("cols").add(gal[1]);
        }

        BigInteger result = BigInteger.ZERO;

        for (int i = 0; i < gals.size() - 1; i++) {
            int[] gal1 = gals.get(i);
            for (int j = i + 1; j < gals.size(); j++) {
                int[] gal2 = gals.get(j);
                result = result.add(calcDist(gal1, gal2, rcmap));
            }
        }

        System.out.println(result);

    }

    private static BigInteger calcDist(int[] gal1, int[] gal2, Map<String, List<Integer>> rcmap) {
        int a = gal1[0]; // x1
        int b = gal1[1]; // x2
        int c = gal2[0]; // y1
        int d = gal2[1]; // y2
        BigInteger dist = BigInteger.ZERO;
        BigInteger temp = BigInteger.valueOf(999999);


        while (b != d) {
            if(!rcmap.get("cols").contains(b)) {
                dist = dist.add(temp);
            }
            b += (d - b)/Math.abs(d - b);
            dist = dist.add(BigInteger.ONE);
        }
        while (a != c) {
            if(!rcmap.get("rows").contains(a)) {
                dist = dist.add(temp);
            }
            a += (c - a)/Math.abs(c - a);
            dist = dist.add(BigInteger.ONE);
        }
        return dist;
    }
}
