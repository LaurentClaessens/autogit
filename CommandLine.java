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
import java.util.ArrayList;
import java.util.Arrays;

class CommandLine
{
    private ArrayList<String> envp=new ArrayList<String>();

    private boolean inTerminal=false;
    private ArrayList<String> command_line_al;
    private File working_directory;

     // In every case, the given command line is stored in a ArrayList<String>
    public CommandLine(String cm) { 
        command_line_al=new ArrayList<String>();
        command_line_al.add(cm);
    }  
    public CommandLine(String[] cm){ command_line_al=new ArrayList<String>(Arrays.asList(cm)); }
    public CommandLine(ArrayList<String> cm){ command_line_al=cm ;}

    public void addEnvironmentVariable(String ev) { envp.add(ev); }
    public void setInTerminal(boolean it) { inTerminal=it; }
    public void setWorkingDirectory(File wd){working_directory=wd;}
    public void addArgument(String s){ command_line_al.add(s); }

    public Process run() throws IOException
    {
        Runtime rt = Runtime.getRuntime();
        String[] a_envp=new String[envp.size()];
        envp.toArray(a_envp);

        if (inTerminal)
        {
            // For launching "konsole -e git diff" we need a 3 item String[] with "konsole", "-e" and "git diff".
            String[] tmp = Configuration.getTerminalCommand().split(" ");
            ArrayList<String> a_cl = new ArrayList<String>(Arrays.asList(tmp));
            a_cl.addAll(command_line_al);
            String[] cl = new String[a_cl.size()];      // What is sent to to exec is a Array
            a_cl.toArray(cl);

            LogMaker.getLogger().info("Launching "+cl);

            Process p = rt.exec(cl,null,working_directory);             // for some reason, one cannot give environment variable.
            return p;
        }
        else
        {
            LogMaker.getLogger().info("Have to transform : "+command_line_al);
            String[] cl = new String[command_line_al.size()];      // What is sent to to exec is a Array
            command_line_al.toArray(cl);

            LogMaker.getLogger().info("Launching "+cl);
            if (command_line_al.size()==1)
            {
                Process p = rt.exec( cl[0] ,a_envp,working_directory );
                return p;
            }
            else
            {
                Process p = rt.exec( cl ,a_envp,working_directory );
                return p;
            }
        }
    }
    public BufferedReader get_buffered_reader_output() throws IOException
        // return the output as a BufferedReader object that is ready to be read line by line.
        // from http://www.codecodex.com/wiki/Execute_an_external_program_and_capture_the_output
    {
        Process p = run();
        BufferedReader input = new BufferedReader( new InputStreamReader( p.getInputStream()));
        return input;
    }
    public String get_string_output() throws IOException
        // return the output as a string.
    {
        String line;
        String output="";

        BufferedReader input = get_buffered_reader_output();
        while (  (line=input.readLine())!=null  )
        {
            output=output+line+"\n";
        }
        input.close();
        return output;
    }
}
