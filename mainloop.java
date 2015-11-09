/*
Copyright 2015 Laurent Claessens
contact : moky.math@gmail.com

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
//*/

// The class 'mainloop' implements a (recursive) loop over all the repertories included in 'starting_path'.

import java.io.*;

public class mainloop
{
    private File starting_path;

    public mainloop (File starting_path) { this.starting_path=starting_path; }
    public void DealWithDirectory(File filename) throws IOException
    {
        File pathname = filename.getCanonicalFile(); 
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
