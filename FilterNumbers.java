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
    
    public static void main (String[] args) {
        String input = new String();
        if (args.length == 0) {
            System.err.println("Missing required argument (positive integer or path to file)");
            System.exit(1);
        } else {
            try {
                int n = Integer.parseInt(args[0]);
                if (n > 0) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    try {
                        input = br.readLine();
                    } catch (IOException ioe) {
                        System.err.println(ioe);
                        System.exit(1);
                    }

                    String output = filterElements(input);

                    if (args.length == 1) {
                        System.out.println(output);
                    } else {
                        try {
                            BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
                            bw.write(output);
                            bw.close();
                        } catch (IOException ioe) {
                            System.err.println(ioe);
                            System.exit(1);
                        }
                    }
                } else {
                    System.err.println("Expected positive integer, given: " +n);
                    System.exit(1);
                }
            } catch (NumberFormatException nfe) {
                try {
                    input = new String(Files.readAllBytes(Paths.get(args[0])));
                } catch (IOException ioe) {
                    System.err.println(ioe);
                    System.exit(1);
                }
                
                String output = filterElements(input.strip());

                if (args.length == 1) {
                    System.out.println(output);
                } else {
                    try {
                        BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
                        bw.write(output);
                        bw.close();
                    } catch (IOException ioe) {
                        System.err.println(ioe);
                        System.exit(1);
                    }
                }
            }
        } 
     }
}
