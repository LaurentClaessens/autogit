import java.io.*;
import mainloop.*;

// http://www.jmdoudoux.fr/java/dej/chap-flux.htm#flux-5

class autogit {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        File starting_path=new File(args[0]);
        mainloop.DealWithDirectory(starting_path);
    }
}
