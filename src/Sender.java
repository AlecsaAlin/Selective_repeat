import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

public class Sender {
    public ArrayList<Pack> vector;

    public Sender(int noPackages) {
        vector = new ArrayList<Pack>();
        for (int i = 0; i < noPackages; i++) {
            Pack j = new Pack(i);
            vector.add(j);
        }
    }

    public Sender() {
        vector = new ArrayList<Pack>();
    }

    public void send(int windowSize) {
        Random rand = new Random();
        Receiver receiver = new Receiver();
        int windowStart = 0;

        System.out.println("Starting send process with " + vector.size() + " packages and window size " + windowSize);
        
        while (!isAllSent()) {
            System.out.println("\nCurrent window starts at index " + windowStart);

            ArrayList<Pack> currentWindow = new ArrayList<>();
            int count = 0;
            int index = windowStart;

            while (count < windowSize && index < vector.size()) {
                if (!vector.get(index).isSent) {
                    currentWindow.add(vector.get(index));
                    count++;
                }
                index++;
            }

            if (currentWindow.isEmpty()) {
                System.out.println("No more unsent packages to process");
                break;
            }

            System.out.println("Current window contains " + currentWindow.size() + " packages");


            Pack firstPack = currentWindow.get(0);
            int firstPackIndex = vector.indexOf(firstPack);
            boolean firstPackSuccess = rand.nextInt(100) < 80; // 80% success rate

            if (firstPackSuccess) {
                firstPack.isSent = true;
                receiver.vector.add(firstPack);
                System.out.println("First package (index " + firstPackIndex + ", data " + firstPack.data + ") sent successfully on first attempt");

                windowStart = firstPackIndex + 1;

                System.out.println("Window moved forward to start at index " + windowStart);

            } else {
                System.out.println("First package (index " + firstPackIndex + ", data " + firstPack.data + ") failed to send on first attempt");


                ArrayList<Pack> failed = new ArrayList<>();
                failed.add(firstPack); // Add the first failed package


                for (int i = 1; i < currentWindow.size(); i++) {
                    Pack pack = currentWindow.get(i);
                    boolean success = rand.nextInt(100) < 80; // 80% success rate

                    if (success) {
                        pack.isSent = true;
                        receiver.vector.add(pack);
                        System.out.println("Package (index " + vector.indexOf(pack) + ", data " + pack.data + ") sent successfully");
                    } else {
                        failed.add(pack);
                        System.out.println("Package (index " + vector.indexOf(pack) + ", data " + pack.data + ") failed to send");
                    }
                }

                while (!failed.isEmpty()) {
                    System.out.println("Retrying " + failed.size() + " failed packages");
                    Iterator<Pack> iterator = failed.iterator();

                    while (iterator.hasNext()) {
                        Pack pack = iterator.next();
                        boolean retrySuccess = rand.nextInt(100) < 80;

                        if (retrySuccess) {
                            pack.isSent = true;
                            receiver.vector.add(pack);
                            System.out.println("Package (index " + vector.indexOf(pack) + ", data " + pack.data + ") retransmission successful");
                            iterator.remove();
                        } else {
                            System.out.println("Package (index " + vector.indexOf(pack) + ", data " + pack.data + ") retransmission failed - will retry");
                        }
                    }
                }


                while (windowStart < vector.size() && vector.get(windowStart).isSent) {
                    windowStart++;
                }
                System.out.println("First package failed on first attempt - moving window to next unsent package at index " + windowStart);
            }

        }

        System.out.println("All packages sent successfully! Received " + receiver.vector.size() + " packages.");
        System.out.println("Receiver before sorting:");
        for(int i =0;i<receiver.vector.size();i++){
            receiver.vector.get(i).display();
        }

        receiver.vector.sort((p1, p2) -> Integer.compare(p1.data, p2.data));
        System.out.println("\n");
        System.out.println("Receiver after sorting:");
        for(int i =0;i<receiver.vector.size();i++){
            receiver.vector.get(i).display();
        }
    }

    private boolean isAllSent() {
        int sentCount = 0;
        int totalCount = vector.size();

        for (Pack pack : vector) {
            if (pack.isSent) {
                sentCount++;
            }
        }

        System.out.println("Status: " + sentCount + "/" + totalCount + " packages sent");
        return sentCount == totalCount;
    }
}