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
//


// The user cases that I have to support :
// konsole -e git diff   inside a directory  (this one has to open a new terminal)
// git commit -a --message='my message'   inside a directory  (has to be silencious)
// git status   inside a directory and read the output.

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.ProcessBuilder;
import java.util.Map;
import java.util.HashMap;

class CommandLine
{
    private ArrayList<String> envp=new ArrayList<String>();

    private boolean inTerminal=false;
    private ArrayList<String> command_line_al;
    private File working_directory;
    private Map<String,String> environment = new HashMap<String,String>();

     // In every case, the given command line is stored in a ArrayList<String>
    public CommandLine(String cm) { 
        command_line_al=new ArrayList<String>();
        command_line_al.add(cm);
    }  
    public CommandLine(String[] cm){ command_line_al=new ArrayList<String>(Arrays.asList(cm)); }
    public CommandLine(ArrayList<String> cm){ command_line_al=cm ;}

    public void addEnvironmentVariable(String env,String value) { environment.put(env,value); }
    public void setInTerminal(boolean it) { inTerminal=it; }
    public void setWorkingDirectory(File wd){working_directory=wd;}
    public void addArgument(String s){ command_line_al.add(s); }

    public Process run() throws IOException
    {
        String[] effective_command_line;     // the effective command line has to be a String[]

        if (inTerminal)
        {
            String[] tmp = Configuration.getTerminalCommand().split(" ");
            ArrayList<String> tmp_al  = new ArrayList<String>(Arrays.asList(tmp));
            tmp_al.addAll(command_line_al);
            String[] cl = new String[tmp_al.size()];      // What is sent to to exec is a Array
            tmp_al.toArray(cl);
            effective_command_line=cl;
        }
        else
        {
            String[] cl = new String[command_line_al.size()];
            command_line_al.toArray(cl);
            effective_command_line=cl;
        }
        LogMaker.getLogger().info("Launching "+effective_command_line);
        ProcessBuilder b=new ProcessBuilder(effective_command_line);
        b.directory(working_directory);
        b.environment().putAll(environment);
        return b.start();
    }
    public BufferedReader get_buffered_reader_output() 
        // return the output as a BufferedReader object that is ready to be read line by line.
        // from http://www.codecodex.com/wiki/Execute_an_external_program_and_capture_the_output
    {
        try
        {
            Process p = run();
            BufferedReader input = new BufferedReader( new InputStreamReader( p.getInputStream()));
            return input;
        }
        catch (IOException e){ LogMaker.getLogger().info("Unable to create the buffered reader.") ; }
        return null;
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
