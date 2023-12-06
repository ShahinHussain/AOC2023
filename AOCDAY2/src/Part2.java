import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {
    public static void main(String[] args) {
        try {
            File myObj = new File("./src/input.txt");
            Scanner myReader = new Scanner(myObj);

            List<Integer> minCubes = new ArrayList<>();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                int i = 8, maxRed = 0, maxGreen = 0, maxBlue = 0;
                StringBuilder currentColour = new StringBuilder();
                StringBuilder currentColourAmount = new StringBuilder();

                while (i < data.length()) {
                    while (i < data.length() && !(data.charAt(i) == ',' || data.charAt(i) == ';')) {
                        char currentChar = data.charAt(i);
                        if (Character.isDigit(currentChar) && !(currentChar == ' ')) {
                            currentColourAmount.append(currentChar);
                        }
                        else if (!(currentChar == ' ') && !(currentChar == ':')) {
                            currentColour.append(currentChar);
                        }
                        i++;
                    }

                    String stringCurrentColour = currentColour.toString();
                    int intCurrentColourAmount = Integer.parseInt(currentColourAmount.toString());

                    switch (stringCurrentColour) {
                        case "red":
                            maxRed = Math.max(maxRed, intCurrentColourAmount);
                            break;
                        case "green":
                            maxGreen = Math.max(maxGreen, intCurrentColourAmount);
                            break;
                        case "blue":
                            maxBlue = Math.max(maxBlue, intCurrentColourAmount);
                            break;
                    }

                    currentColour.setLength(0);
                    currentColourAmount.setLength(0);

                    i++;
                }

                minCubes.add(maxRed * maxGreen * maxBlue);
            }

            myReader.close();

            int minOutput = 0;

            for(int n : minCubes) {
                minOutput += n;
            }

            System.out.println(minOutput);




        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}