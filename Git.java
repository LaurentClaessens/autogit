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

// Since I was not able to use Jgit, I implement it here.

import java.io.*;
import java.io.File;


public class Git
{
    public File repo_path;
    public Git(File pathname) throws IOException
    {

        if (!pathname.exists())
        {
            System.out.println("The path "+pathname+" does not exist");
            System.exit(1);
        }
        repo_path=pathname.getCanonicalFile();
    }
    public boolean need_commit() throws IOException
    // return 'true' if a commit is needed.
    {
        String line;
        CommandLine command=new CommandLine("git status");
        command.working_directory=repo_path;
        command.envp.add("LC_ALL=C");
        BufferedReader input=command.get_buffered_reader_output();
        System.out.println("On part pour"+repo_path);
        String searched="nothing to commit, working directory clean";
        while (  (line=input.readLine())!=null  )
        {
            System.out.println(searched.getClass().getName());
            System.out.println(line.getClass().getName());
            System.out.println("+"+searched+"+");
            System.out.println("+"+line+"+");
            if (line==searched) {
                System.out.println("ON Y EST !!");
                return false;}
        }
        return true;
    }

}
