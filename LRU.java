import java.util.*;

public class LRU {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter No.of Frames : ");
        int frames = sc.nextInt();

        System.out.print("Enter No.of Pages : ");
        int pages = sc.nextInt();

        int[] pageString = new int[pages];
        System.out.print("Enter the page reference string : ");
        for (int i = 0; i < pages; i++)
            pageString[i] = sc.nextInt();

        ArrayList<Integer> frame = new ArrayList<>();
        int pageFaults = 0, pageHits = 0;

        System.out.println("\nPage reference\tFrames\t\tHit/Fault");

        for (int page : pageString) {

            if (frame.contains(page)) {            // Page Hit
                frame.remove((Integer) page);
                frame.add(page);
                pageHits++;
                System.out.println(page + "\t\t" + frame + "\t\tHit");
            } 
            else {                                 // Page Fault
                if (frame.size() == frames)
                    frame.remove(0);

                frame.add(page);
                pageFaults++;
                System.out.println(page + "\t\t" + frame + "\t\tFault");
            }
        }

        System.out.println("\nTotal Page Faults = " + pageFaults);
        System.out.println("Total Page Hits = " + pageHits);

        sc.close();
    }
}

