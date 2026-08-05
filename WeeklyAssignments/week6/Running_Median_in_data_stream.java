import java.util.Collections;
import java.util.PriorityQueue;

public class Running_Median_in_data_stream {
    public static void main(String[] args) {

    }

    class Median {
        PriorityQueue<Integer> max; // Left half (Max Heap)
        PriorityQueue<Integer> min; // Right half (Min Heap)

        public Median() {
            max = new PriorityQueue<>(Collections.reverseOrder());
            min = new PriorityQueue<>();
        }

        void insert(int num) {
            if (max.isEmpty() || num <= max.peek()) {
                max.add(num);
            } else {
                min.add(num);
            }
            // Balance the heaps
            if (max.size() - min.size() > 1) {
                min.add(max.poll());
            } else if (min.size() > max.size()) {
                max.add(min.poll());
            }
        }

        double getMedian() {
            if (max.size() > min.size()) {
                return max.peek();
            }
            return (max.peek() + min.peek()) / 2.0;
        }
    }
}
