import java.io.*;

public class mainloop
{
    private File starting_path;
    public void DealWithFile(File pathname)
    {
        System.out.println("File : "+pathname);
    }
    public void DealWithDirectory(File pathname)
    {
        System.out.println("Entering directory "+pathname);
        if (!pathname.exists())
        {
            System.out.println("The path "+pathname+" does not exist");
            System.exit(1);
        }
        File content[]=pathname.listFiles();
        for (int i=0;i<content.length;i++)
        {
            File newpath=content[i];
            if (newpath.isDirectory()) { DealWithDirectory(newpath) ;  }
            else if (newpath.isFile()) { DealWithFile(newpath); }
            else 
            {
                System.out.println("What the hell is "+newpath+" ? ");
                System.exit(1);
            }
        }
    }
}
