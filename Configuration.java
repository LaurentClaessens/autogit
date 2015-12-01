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

class Configuration
{
    private static String terminal_command;
    public static String getTerminalCommand()
    {
        return terminal_command;
    }
    public static void init()
    {
        String line;
        String terminal_key="terminal";
        try{
            InputStream fis = new FileInputStream("autogit.cfg");
            InputStreamReader isr = new InputStreamReader(  fis,"utf-8"  );
            BufferedReader br = new BufferedReader(isr);
            while (  (line=br.readLine())!=null  )
            {
                String[]  keyvalue = line.split("=");
                if (keyvalue[0].equals(terminal_key))
                {
                    terminal_command=keyvalue[1];
                    LogMaker.getLogger().info("terminal found : "+terminal_command);
                }
            }
        }
        catch (FileNotFoundException e){  System.out.println("File not found "+e);  }
        catch (UnsupportedEncodingException e){  System.out.println("Unsupported encoding "+e);  }
        catch (IOException e){  System.out.println("IO error "+e);  }
    }
};
