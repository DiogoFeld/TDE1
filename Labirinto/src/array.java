import java.util.Arrays;

public class array {
    private int[][] data;
    int top =0;

    public void push(int[] element) {
        if(top >= getCapacity() - 1) {
            int newCapacity = getCapacity() + 1;
            var newArray = Arrays.copyOf(data, newCapacity);
            data = newArray;
        }
        top = top + 1;
        data[top] = element;
    }

    public int getCapacity() {
        return data.length;
    }




}
