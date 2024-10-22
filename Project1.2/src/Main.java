import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> L_List = new LinkedList<>();
        List<Integer> A_List = new ArrayList<>();

        long startTime = System.nanoTime();
        for (int i = 0; i < 5; i++) {
            L_List.add(i);
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Czas dodawania elementów do LinkedList: " + elapsedTime + " nanoseconds");

        long startTime1 = System.nanoTime();
        for (int i = 0; i < 5; i++) {
            A_List.add(i);
        }
        long endTime1 = System.nanoTime();
        long elapsedTime1 = endTime1 - startTime1;
        System.out.println("Czas dodawania elementów do ArrayList: " + elapsedTime1 + " nanoseconds");

        long startTime5 = System.nanoTime();
        for (int i = 0; i < 5; i++) {
            L_List.set(i, 1);
        }
        long endTime5 = System.nanoTime();
        long elapsedTime5 = endTime5 - startTime5;
        System.out.println("Czas modyfikacje elementów do LinkedList: " + elapsedTime5 + " nanoseconds");


        long startTime4 = System.nanoTime();
        for (int i = 0; i < 5; i++) {
            A_List.set(i, 1);
        }
        long endTime4 = System.nanoTime();
        long elapsedTime4 = endTime4 - startTime4;
        System.out.println("Czas modyfikacje elementów do ArrayList: " + elapsedTime4 + " nanoseconds");



        long startTime2 = System.nanoTime();
        for (int i = 4; i >= 0; i--) {
            L_List.remove(i);
        }
        long endTime2 = System.nanoTime();
        long elapsedTime2 = endTime2 - startTime2;
        System.out.println("Czas usuwania elementów do LinkedList: " + elapsedTime2 + " nanoseconds");

        long startTime3 = System.nanoTime();
        for (int i = 4; i >= 0; i--) {
            A_List.remove(i);
        }
        long endTime3 = System.nanoTime();
        long elapsedTime3 = endTime3 - startTime3;
        System.out.println("Czas usuwania elementów do ArrayList: " + elapsedTime3 + " nanoseconds");

    }
}