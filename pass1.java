

import java.util.*;

public class pass1 {
    public static void main(String[] args) {

        String[] code = {
            "START 1000",
            "A MOV =5",
            "ADD B",
            "B SUB =3",
            "LTORG",
            "LOOP ADD A",
            "END"
        };

        HashMap<String, Integer> SYMTAB = new HashMap<>();
        ArrayList<String> LIT = new ArrayList<>();
        ArrayList<Integer> LADDR = new ArrayList<>();
        ArrayList<Integer> POOL = new ArrayList<>(List.of(0));
        ArrayList<String[]> INTER = new ArrayList<>();
        int LC = 0;

        for (String line : code) {

            String[] p = line.trim().split("\\s+");
            String label = p.length == 3 ? p[0] : "";
            String op = p.length == 3 ? p[1] : p[0];
            String opd = p.length >= 2 ? p[p.length - 1] : "-";

            if (!label.isEmpty()) SYMTAB.put(label, LC);
            if (opd.startsWith("=")) { LIT.add(opd); LADDR.add(-1); }

            if (op.equals("START")) {
                LC = Integer.parseInt(opd);
                INTER.add(new String[]{LC + "", op, opd});
                continue;
            }

            if (op.equals("LTORG") || op.equals("END")) {
                for (int i = POOL.get(POOL.size() - 1); i < LIT.size(); i++)
                    LADDR.set(i, LC++);
                POOL.add(LIT.size());
                INTER.add(new String[]{LC + "", op, "-"});
                if (op.equals("END")) break;
                continue;
            }

            INTER.add(new String[]{LC + "", op, opd});
            LC++;
        }

        // -------- PRINT OUTPUT ----------
        System.out.println("\nINTERMEDIATE CODE");
        System.out.println("LC\tOP\tOPERAND");
        for (var r : INTER)
            System.out.println(r[0] + "\t" + r[1] + "\t" + r[2]);

        System.out.println("\nSYMBOL TABLE");
        System.out.println("SYM\tADDR");
        for (var s : SYMTAB.keySet())
            System.out.println(s + "\t" + SYMTAB.get(s));

        System.out.println("\nLITERAL TABLE");
        System.out.println("LIT\tADDR");
        for (int i = 0; i < LIT.size(); i++)
            System.out.println(LIT.get(i) + "\t" + LADDR.get(i));

        System.out.println("\nPOOL TABLE");
        System.out.println("POOL\tSTART");
        for (int i = 0; i < POOL.size(); i++)
            System.out.println(i + "\t" + POOL.get(i));
    }
}
