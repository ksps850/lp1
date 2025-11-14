import java.util.*;

class SJF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int bt[] = new int[n];   // burst times
        int wt[] = new int[n];   // waiting times
        int tat[] = new int[n];  // turnaround times

        for(int i = 0; i < n; i++) {
            System.out.print("Enter burst time of P" + (i+1) + ": ");
            bt[i] = sc.nextInt();
        }

        // Sort burst times (SJF)
        int p[] = new int[n];
        for(int i = 0; i < n; i++) p[i] = i;
        for(int i = 0; i < n-1; i++)
            for(int j = i+1; j < n; j++)
                if(bt[p[i]] > bt[p[j]]) {
                    int temp = p[i]; p[i] = p[j]; p[j] = temp;
                }

        // Calculate waiting time
        wt[p[0]] = 0;
        for(int i = 1; i < n; i++)
            wt[p[i]] = wt[p[i-1]] + bt[p[i-1]];

        // Calculate turnaround time
        for(int i = 0; i < n; i++)
            tat[i] = wt[i] + bt[i];

        // Display result
        System.out.println("\nProcess\tBT\tWT\tTAT");
        for(int i : p)
            System.out.println("P"+(i+1)+"\t"+bt[i]+"\t"+wt[i]+"\t"+tat[i]);
    }
