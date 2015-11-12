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

import javax.swing.*;
import java.awt.*;

import java.io.*;

public class GitWindows extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    private JTextArea git_status_area;
    private GitRepository repo;

    private void addGitCommitToPane( final Container pane )
    {
        JPanel area = new JPanel();
        String message;
        try
        {
            message=repo.status_message();
        }
        catch (IOException e) { message="status failed"; }
        git_status_area= new JTextArea(message) ;
        area.add(git_status_area);
        pane.add(area);
    }

    public GitWindows(GitRepository gitRepo)
    { 
        super("bla");
        repo=gitRepo;

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        addGitCommitToPane(getContentPane());
    }
};

class GitWindowsLauncher implements Runnable
{
    private GitRepository repo;
    public GitWindowsLauncher(Object Orepo)
    {
        repo=(GitRepository)Orepo;
    }
    public void run()
    {
        GitWindows gw = new GitWindows(repo);
        gw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gw.setVisible(true);
    }
};
