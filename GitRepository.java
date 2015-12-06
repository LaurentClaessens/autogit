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

import java.io.*;
import java.lang.String;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GitRepository
{

    private BufferedReader status_buffered_message() 
    {
        String line;
        CommandLine command=new CommandLine( new String[] { "git","status"});
        command.setWorkingDirectory(repo_path);
        command.addEnvironmentVariable("LC_ALL","C");

        BufferedReader input=command.get_buffered_reader_output();
        return input;
    }

    private Path repo_path;         // This one is an absolute path.
    public Path getPath(){ return repo_path; }

    public String getPathName() { return repo_path.toString(); }

    public ArrayList<Path> untracked_files ()
    // return the "list" of untracked files.
    {
        BufferedReader status=status_buffered_message();
        Boolean yet=false;
        String line;
        ArrayList<Path> file_list = new ArrayList<Path>();
        try
        {
            while (  (line=status.readLine())!=null  )
            {
                if (yet==true)
                {
                    if (line.startsWith("\t"))
                    {
                        String filename=line.split("\t")[1];
                        Path filepath=getPath().resolve(filename);
                        file_list.add(filepath);
                    }
                }
                if (line.equals("Untracked files:")) 
                { 
                    yet=true; 
                    LogMaker.getLogger().info("'Untracked files' line found");   
                }
            }
            status.close();
        }
        catch(IOException e){ LogMaker.getLogger().info("readLine probably failed in 'untracked_files'");  }
        return file_list;
    }

    public GitRepository(Path path_to_repo) throws IOException
    {

        if (!path_to_repo.toFile().exists())
        {
            System.out.println("The path "+path_to_repo+" does not exist");
            System.exit(1);
        }
        repo_path=path_to_repo;
    }
    public GitRepository(String pathname) throws IOException { this(Paths.get(pathname));  }
    public GitRepository(File pathname) throws IOException { this(pathname.toPath());  }

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
            if (line.equals(searched)) { return false;}
        }
        return true;
    }
    // Launch the command line in a terminal inside the 'this' path.
    private Process terminal_action(String[] cl)  throws IOException
    {
        CommandLine command=new CommandLine(cl);
        command.setWorkingDirectory(getPath());
        command.setInTerminal(true);
        Process p = command.run();
        return p;
    }

    class terminal_command_launcher implements Runnable
    {
        private String[] command_line;
        public terminal_command_launcher(String[] cl)
        {
            command_line=cl;
        }
        public void run()
        {
            System.out.println("Click for "+command_line+" in "+getPathName());
            try
            {
                Process p=terminal_action(command_line);
            }
            catch (IOException e) { 
                LogMaker.getLogger().info("Operation failed : "+command_line+" in "+getPathName());
                System.out.println("Operation failed");
            }
        }
    };

    class shortCommitRunnable implements Runnable
    {
        private String text;
        public shortCommitRunnable(String t)
        {
            text=t;
        }
        public void run()
        {
            System.out.println("Click for a short commit in "+getPathName()+" avec message : "+text);

            CommandLine command = new CommandLine( new String[] {"git","commit","-a","--message="+text}  );
            command.setWorkingDirectory(getPath());
            command.addEnvironmentVariable("LC_ALL","C");

            try
            {
                Process p = command.run();
            }
            catch (IOException e) { 
                LogMaker.getLogger().info("Cannot make 'git commit' with message : "+text+" in "+getPathName());
                System.out.println("Operation failed");
            }
        }
    }

    // This function add the untracked files in '.gitignore '.
    // - Boolean top (default=false) if 'top' is true, add the untracked files at the top of .gitignore instead of the bottom.
    //     if 'top' is true, a blank line is added _under_ the list and if 'top' is false, a blank line is added _over_ the list.
    // - String comment (defaut="# Automatically added")
    // after having used this function, you should open the file for manual editing.
    public void add_untracked_gitignore(Boolean top,String comment)
    {
        ArrayList<String> to_be_added=new ArrayList<String>();
        if (!top){to_be_added.add("");}

        to_be_added.add(comment);
        for (Path file:untracked_files())
        {
            String string_path=file.toString();
            if (file.toFile().isDirectory()) { string_path=string_path+"/*"; }
            to_be_added.add(string_path);
        }
        if (top){to_be_added.add("");}
        System.out.println(to_be_added);
        String s=combine.join("\n",to_be_added);
        System.out.println(s);
    }
    public void add_untracked_gitignore() { add_untracked_gitignore(false,"# Automatically added"); }

    public void shortCommit(String text)
    {
        Runnable short_commit_runnable = this.new shortCommitRunnable( text );
        Thread short_commit_thread = new Thread(short_commit_runnable);
        short_commit_thread.start();
    }
}
