
import java.util.*;
import java.io.*;

public class pass2 {
    public static void main(String[] args) throws IOException {

        // Step 1: OPCODE TABLE
        Map<String, String> opcode = new HashMap<>();
        opcode.put("MOV", "01");
        opcode.put("ADD", "02");
        opcode.put("SUB", "03");
        opcode.put("JMP", "04");
        opcode.put("STOP", "00");

        // Step 2: READ SYMBOL TABLE
        Map<String, Integer> symtab = new HashMap<>();
        BufferedReader br1 = new BufferedReader(new FileReader("symbol_table.txt"));

        String line;
        while ((line = br1.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            if (parts.length >= 2) {
                symtab.put(parts[0], Integer.parseInt(parts[1]));
            }
        }
        br1.close();

        // Step 3: READ INTERMEDIATE CODE
        BufferedReader br2 = new BufferedReader(new FileReader("intermediate.txt"));
        System.out.println("\n--- MACHINE CODE ---");

        while ((line = br2.readLine()) != null) {

            line = line.trim();
            if (line.isEmpty()) continue;  // skip blank lines

            String[] parts = line.split("\\s+");
            if (parts.length < 2) continue;  // avoid errors

            // Skip START/END
            if (parts[1].equalsIgnoreCase("START") || parts[1].equalsIgnoreCase("END"))
                continue;

            String loc   = parts[0];
            String op    = parts[1].toUpperCase();
            String code  = opcode.getOrDefault(op, "??");

            // Operand may or may not exist
            String operand = (parts.length > 2) ? parts[2] : "";
            String address = "000";

            if (symtab.containsKey(operand)) {
                address = String.valueOf(symtab.get(operand));
            }

            // PRINT MACHINE CODE
            System.out.println(loc + "\t" + code + "\t" + address);
        }

        br2.close();
    }
}

//Intermediate code
//100 START 0
//100 MOV A
//101 ADD B
//102 SUB A
//103 JMP LOOP
//104 STOP
//200 END

//symbol table
//A 210
//B 215
//LOOP 300
