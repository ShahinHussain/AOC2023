import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part1 {
    public static void main(String[] args) {
        try {
            File myObj = new File("./src/input.txt");
            Scanner myReader = new Scanner(myObj);
            Map<String, Integer> map = new HashMap<>();
            map.put("red",12);
            map.put("green",13);
            map.put("blue",14);

            List<Integer> validIDs = new ArrayList<>();

            int gameID = 1;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                int i = 8;
                boolean isValid = true;
                StringBuilder currentColour = new StringBuilder();
                StringBuilder currentColourAmount = new StringBuilder();

                while (i < data.length()) {

                    while (i < data.length() && !(data.charAt(i) == ',' || data.charAt(i) == ';')) {
                        char currentChar = data.charAt(i);
                        if (Character.isDigit(currentChar) && !(currentChar == ' ')) {
                            currentColourAmount.append(currentChar);
                        }
                        else if (!(currentChar == ' ')) {
                            currentColour.append(currentChar);
                        }
                        i++;
                    }

                    String stringCurrentColour = currentColour.toString();
                    int intCurrentColourAmount = Integer.parseInt(currentColourAmount.toString());

                    if(intCurrentColourAmount > 14) {
                        isValid = false;
                    }
                    else if(intCurrentColourAmount >= 12) {
                        isValid = intCurrentColourAmount <= map.get(stringCurrentColour);
                    }

                    if (!isValid) {
                        break;
                    }

                    currentColour.setLength(0);
                    currentColourAmount.setLength(0);

                    i++;
                }

                if (isValid) {
                    validIDs.add(gameID);
                }
                gameID++;
            }
            myReader.close();

            int output = 0;

            for(int k : validIDs) {
                output += k;
            }

            System.out.println(output);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}