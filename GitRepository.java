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

public class GitRepository
{

    private BufferedReader status_buffered_message() throws IOException
    {
        String line;
        CommandLine command=new CommandLine("git status");
        command.working_directory=repo_path;
        command.envp.add("LC_ALL=C");

        BufferedReader input=command.get_buffered_reader_output();
        return input;
    }

    private File repo_path;
    public String getPathName() { 
        String can_path;
        try{ 
            can_path=repo_path.getCanonicalPath();
        } 
        catch (IOException e){ 
            can_path="Canonical path failed";
            LogMaker.get_logger().logger.info("Canonical path failed for "+repo_path);
        }
        return can_path;
    }
    public GitRepository(File pathname) throws IOException
    {

        if (!pathname.exists())
        {
            System.out.println("The path "+pathname+" does not exist");
            System.exit(1);
        }
        repo_path=pathname.getCanonicalFile();

    }
    public String status_message() throws IOException
    {
        String output="";
        String line;

        BufferedReader buffered_message = status_buffered_message();
        while (  (line=buffered_message.readLine())!=null  )
        {
            output=output+line+"\n";
        }
        buffered_message.close();
        return output;
    }
    public boolean need_commit() throws IOException
    // return 'true' if a commit is needed.
    {
        String line;
        String searched="nothing to commit, working directory clean";
        BufferedReader buffered_message = status_buffered_message();
        while (  (line=buffered_message.readLine())!=null  )
        {
            if (line.endsWith(searched)) {              // its strange that this works and startsWith works while '==' does not works.
                return false;}
        }
        return true;
    }

}
