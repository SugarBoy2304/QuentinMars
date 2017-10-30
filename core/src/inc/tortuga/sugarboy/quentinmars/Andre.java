package inc.tortuga.sugarboy.quentinmars;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by swift on 30.10.2017.
 */

public class Andre {

    /*
    Этот код для моего одноклассника, просто мне лень было создавать отдельные проект и я вставил его сюда, в этот класс.
    */

    public static void main(String[] args) {

        System.out.println("Введите число a");
        Scanner scn = new Scanner(System.in);
        int a = scn.nextInt();
        System.out.println("Введите число b");
        int b = scn.nextInt();
        System.out.println("Введите число с");
        int c = scn.nextInt();
        int[] n = new int[25];
        double t;
        double w;
        int i = 0;
        for (int q = a; q <= c ;q += b) {
            n[i] = q;
            i++;
        }
        t = 1;
        w = 0;

        System.out.println(Arrays.toString(n));

        for (int q = 0; q < n.length; q += 2) {
            if (n[q] != 0) t *= n[q];
            if (q + 1 < n.length && n[q + 1] != 0) w += n[q + 1];
            System.out.println(t + " " + w);
        }

        System.out.println("Вывод: " + t/w);

        /*Scanner scan = new Scanner(System.in);

        System.out.println("Введите A:");
        int a = scan.nextInt();

        System.out.println("Введите B:");
        int b = scan.nextInt();

        System.out.println("Введите C:");
        int c = scan.nextInt();

        int numbers[] = new int[100];
        int index = 0;
        for (int q = a; q <= c; q += b) {
            numbers[index] = q;
            index++;
        }

        System.out.println(Arrays.toString(numbers));

        double mult = 1;
        double sum = 0;
        for (int q = 0; q < numbers.length; q++) {
            if (numbers[q] == 0) continue;
            if (q % 2 == 0) {
                mult *= numbers[q];
                System.out.println(q + ". Mult = " + mult);
            } else {
                sum += numbers[q];
                System.out.println(q + ". Sum = " + mult);
            }
        }

        System.out.println("Вывод: " + mult / sum);*/

    }

}
