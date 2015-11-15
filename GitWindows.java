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

    private GitRepository repo;

    private void addGitCommitToPane( final Container pane )
    {
        JPanel area = new JPanel();
        JTextArea git_status_area;
        String message;
        try
        {
            message=repo.status_message();
        }
        catch (IOException e) { message="status failed"; }
        git_status_area= new JTextArea(message);

        git_status_area.setEditable(false);
        git_status_area.setSize(git_status_area.getPreferredSize());

        area.add(git_status_area);
        pane.add(area);
    }

    private void addButtonsToPanel( final Container pane )
    {
        JPanel button_panel = new JPanel();
        JButton gitignore_button = new JButton("add to .gitignore");
        button_panel.add(gitignore_button);
        pane.add( button_panel,BorderLayout.SOUTH );
    }

    private void addTitleToPanel( final Container pane )
    {
        JPanel title_panel = new JPanel();

        JEditorPane editor = new JEditorPane();
        editor.setContentType("text/html");
        editor.setText("<html><b>"+repo.getPathName()+"</b></html>");
        editor.setEditable(false);

        title_panel.add(editor);
        pane.add( title_panel,BorderLayout.NORTH);
    }

    public GitWindows(GitRepository gitRepo)
    { 
        super("bla");
        repo=gitRepo;

        addGitCommitToPane(getContentPane());
        addButtonsToPanel(getContentPane());
        addTitleToPanel(getContentPane());
        setSize(getContentPane().getPreferredSize());
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
