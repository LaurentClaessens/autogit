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

import java.awt.*;
import java.swing.*;


public class GitWindows extends JFrame{
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    private JTextArea git_status_area;

    private void addComponentsToPane( final Container pane )
    {
    }

    public GitWindows(File repo_path)
    { 
        super(repo_path.String());

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        addComponentsToPane(getContentPane());
    }
};
