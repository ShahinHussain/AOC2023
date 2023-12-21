import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("C:\\Users\\Sami\\IdeaProjects\\AOC\\AOCDAY15\\src\\input.txt");
        Scanner sc = new Scanner(myFile);
        String[] input = sc.nextLine().split(",");

        long result = 0;
        for(int i = 0; i < input.length; i++) {
            result += calc(input[i]);
        }
        System.out.println(result   );
    }

    public static int calc(String str) {
        int n = str.length();
        int i = 0;
        int out = 0;

        StringBuilder sb = new StringBuilder();
        sb.append(str, 0, i);
        while( i < n ) {
            sb.append(str.charAt(i));
            out = ((out + (int)str.charAt(i)) * 17) % 256;
            i++;
            if(i == n) {
                break;
            }
            else {
                sb.append(str.charAt(i));
            }
        }
        return out;
    }
}
