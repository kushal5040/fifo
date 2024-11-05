import java.util.*;

class Fifo {
    static int pageFaults(int pages[], int n, int capacity) {
        // HashSet to store current pages in memory
        HashSet<Integer> s = new HashSet<>(capacity);
        
        // Queue to store the order of pages
        Queue<Integer> indexes = new LinkedList<>();
        
        int page_faults = 0;
        int hit = 0;

        for (int i = 0; i < n; i++) {
            // If memory has space available
            if (s.size() < capacity) {
                // If page is not present, add it
                if (!s.contains(pages[i])) {
                    s.add(pages[i]);
                    page_faults++;
                    indexes.add(pages[i]);
                } else {
                    hit++;
                }
            }
            // If memory is full
            else {
                // If page is not present
                if (!s.contains(pages[i])) {
                    // Remove the first page from queue and hashset
                    int val = indexes.peek();
                    indexes.poll();
                    s.remove(val);
                    
                    // Add new page
                    s.add(pages[i]);
                    indexes.add(pages[i]);
                    page_faults++;
                } else {
                    hit++;
                }
            }
        }
        
        System.out.println("Number of page faults: " + page_faults);
        System.out.println("Number of page hits: " + hit);
        System.out.println("Hit ratio: " + (float)hit/n);
        System.out.println("Fault ratio: " + (float)page_faults/n);
        
        return page_faults;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        
        // Get the number of pages
        System.out.print("Enter the number of pages: ");
        int n = scanner.nextInt();
        
        // Create array to store page reference string
        int pages[] = new int[n];
        
        // Get the page reference string
        System.out.println("Enter the page reference string:");
        for(int i = 0; i < n; i++) {
            pages[i] = scanner.nextInt();
        }
        
        // Get the number of frames
        System.out.print("Enter the number of frames: ");
        int capacity = scanner.nextInt();
        
        // Calculate and display results
        pageFaults(pages, n, capacity);
        
        scanner.close();
    }
}