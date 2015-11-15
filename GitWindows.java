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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;



public class GitWindows extends JFrame {

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
        JEditorPane git_status_pane = new JEditorPane();

        git_status_pane.setContentType("text/html");
        git_status_pane.setText("<html><b>"+repo.getPathName()+"</b><br><br><pre>"+message+"</pre></html>");
        git_status_pane.setEditable(false);

        git_status_pane.setEditable(false);
        git_status_pane.setSize(git_status_pane.getPreferredSize());

        area.add(git_status_pane);
        pane.add(area);
    }

    private void addButtonsToPanel( final Container pane )
    {
        JPanel button_panel = new JPanel();
        JButton gitignore_button = new JButton("Edit .gitignore");


            // This ActionListener is embedded because I do not know how to pass a parameter (repo).
            class gitignoreActionListener implements ActionListener
            {
                public void actionPerformed(ActionEvent e){ 
                    Runnable edit_gitignore_runnable = new edit_gitignore_launcher(repo);
                    Thread edit_gitignore = new Thread(edit_gitignore_runnable);
                    edit_gitignore.start();
                }
}

        gitignore_button.addActionListener( new gitignoreActionListener() );

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
