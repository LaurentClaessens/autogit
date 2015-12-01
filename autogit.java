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
import java.util.logging.Logger;

class autogit {
    public static void main(String[] args) {

        LogMaker.init();            // Initialization of the log system in the file .autogit.log
        Configuration.init();       // Read the configuratio file "autogit.cfg"

        

        File starting_path=new File(args[0]);
        mainloop loop=new mainloop(starting_path);
        try{
            loop.DealWithDirectory(starting_path);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
