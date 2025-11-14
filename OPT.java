import java.util.*;

public class opt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        System.out.print("Enter number of pages: ");
        int pages = sc.nextInt();

        int[] ref = new int[pages];
        System.out.println("Enter page reference string:");
        for (int i = 0; i < pages; i++) ref[i] = sc.nextInt();

        ArrayList<Integer> frame = new ArrayList<>();
        int faults = 0, hits = 0;

        System.out.println("Page\tFrames\t\tStatus");

        for (int i = 0; i < pages; i++) {
            int p = ref[i];

            if (frame.contains(p)) {      // Page Hit
                hits++;
                System.out.println(p + "\t" + frame + "\tHit");
                continue;
            }

            faults++;

            if (frame.size() < frames) {  // Free space available
                frame.add(p);
            } else {                      // Find optimal replacement
                int idx = -1, farthest = -1;

                for (int j = 0; j < frame.size(); j++) {
                    int f = frame.get(j);
                    int next = Integer.MAX_VALUE;

                    for (int k = i + 1; k < pages; k++)
                        if (ref[k] == f) { next = k; break; }

                    if (next > farthest) {
                        farthest = next;
                        idx = j;
                    }
                }
                frame.set(idx, p);
            }

            System.out.println(p + "\t" + frame + "\tFault");
        }

        System.out.println("\nTotal Page Faults = " + faults);
        System.out.println("Total Page Hits   = " + hits);

        sc.close();
    }
}
