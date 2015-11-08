// The class 'mainloop' implements a (recursive) loop over all the repertories included in 'starting_path'.

import java.io.*;

public class mainloop
{
    private File starting_path;

    public mainloop (File starting_path) { this.starting_path=starting_path; }
    public void DealWithDirectory(File filename) throws IOException
    {
        File pathname = filename.getCanonicalFile(); 
        //System.out.println("Entering directory "+pathname);
        if (!pathname.exists())
        {
            System.out.println("The path "+pathname+" does not exist");
            System.exit(1);
        }
        File content[]=pathname.listFiles();
        for (int i=0;i<content.length;i++)
        {
            File newpath=content[i];
            if (newpath.isDirectory()) 
            { 
                if (newpath.getAbsolutePath().endsWith(".git"))
                {
                    DealWithGit git_task= new DealWithGit(pathname);
                    git_task.start();
                }
                DealWithDirectory(newpath) ;  
            }
        }
    }
}
