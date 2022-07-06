import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterNumbers {

    private static String filterElements (String input) {
        String[] parts = input.split(" ");
        List<Integer> output = new ArrayList<Integer>();
        int n = parts.length;
        if (n % 2 == 0) {
            for (int i = 0; i < n; i++) {
                int elem = Integer.parseInt(parts[i]);
                if (elem % 2 == 0) {
                    output.add(elem);
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                int elem = Integer.parseInt(parts[i]);
                if (elem % 2 == 1) {
                    output.add(elem);
                }
            }
        }

        return output.stream().map(Object::toString).collect(Collectors.joining(" "));
    }

    private static String readStringFromSystemIn () throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    private static void writeStringToFile (String path, String text) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        bw.write(text);
        bw.close();
    }
    
    public static void main (String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Missing required argument (positive integer or path to file)");
            System.exit(1);
        } else {
            try {
                int n = Integer.parseInt(args[0]);
                if (n > 0) {
                    String input = readStringFromSystemIn();
                    String output = filterElements(input);

                    if (args.length == 1) {
                        System.out.println(output);
                    } else {
                        writeStringToFile(args[1], output);
                    }
                } else {
                    System.err.println("Expected positive integer, given: " +n);
                    System.exit(1);
                }
            } catch (NumberFormatException nfe) {
                String input = new String(Files.readAllBytes(Paths.get(args[0])));
                
                String output = filterElements(input.strip());

                if (args.length == 1) {
                    System.out.println(output);
                } else {
                    writeStringToFile(args[1], output);
                }
            }
        } 
     }
}
