/* mbor1 created on 11.11.2020 
inside the package - pl.seleniumbot */

package pl.seleniumbot;

public class Utils {
    public static int parseToSeconds(String duration) {
        String[] tab = duration.split(":");
        int h = Integer.valueOf(tab[0]);
        int m = Integer.valueOf(tab[1]);
        int s = Integer.valueOf(tab[2]);
        m = h*60 + m;
        s = m*60 + s;
        return s;
    }

    public static void sleep(int seconds) {
        while (seconds > 0) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new IllegalArgumentException();
            }
            System.out.println("pozostało " + seconds + "sekund!");
            seconds -= 10;
        }
    }
}
