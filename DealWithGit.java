import java.io.*;

class DealWithGit extends Thread{
    File directory_path;
    DealWithGit(File directory_path)
    {
        this.directory_path=directory_path;
        System.out.println("Bon. On va un peu travailler avec le git de "+this.directory_path);
    }
};
