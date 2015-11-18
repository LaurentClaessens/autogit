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
import java.util.logging.FileHandler;

class LogMaker
{
    private static Logger logger = Logger.getGlobal();

    public static Logger get_logger()
    {
        return logger;
    }

    public static void main()
    {
        try{
            FileHandler fh= new FileHandler(".autogit.log");
            logger.addHandler(fh);
            logger.setUseParentHandlers(false);
        }
        catch (IOException e)
        { 
            System.out.println("I cannot create the log file");
            System.exit(1); 
        }
    }
};
