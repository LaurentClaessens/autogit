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
    private JTextField short_commit_text;

    private void addGitStatusToPane( final Container pane )
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

    private JButton createButton(JPanel button_panel, String text, final Runnable to_do )
    {

        // This ActionListener is embedded because I do not know how to pass a parameter (repo).
        class gitGeneralistActionListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e){ 
                Runnable git_generalist_runnable = to_do;
                Thread generalist_thread = new Thread(git_generalist_runnable);
                generalist_thread.start();
            }
        }

        JButton generalist_button = new JButton(text);
        generalist_button.addActionListener( new gitGeneralistActionListener() );
        button_panel.add(generalist_button);

        return generalist_button;
    }

    private void addButtonsToPanel( final Container pane )
    {
        JPanel button_panel = new JPanel();
        button_panel.setLayout( new BoxLayout(button_panel,BoxLayout.Y_AXIS) );

        createButton(  button_panel,"Edit .gitignore", repo.new terminal_command_launcher("vim .gitignore"));
        createButton(  button_panel,"git diff", repo.new terminal_command_launcher("git diff")  );
        createButton(  button_panel,"git commit -a", repo.new terminal_command_launcher("git commit -a"));

        pane.add( button_panel,BorderLayout.WEST );
    }

    private JTextField addShortCommitLine(final Container pane)
    {

        class shortGitCommitListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                repo.shortCommit(short_commit_text.getText());
            }
        }

        JPanel line = new JPanel( new BorderLayout() );
        JTextField short_text_field = new JTextField(20);
        JLabel short_commit_label = new JLabel ("git commit -a with short comment : ");
        JButton ok_commit_button = new JButton("ok, commit that!");
        ok_commit_button.addActionListener( new shortGitCommitListener() );

        line.add(short_commit_label,BorderLayout.WEST);
        line.add(short_text_field,BorderLayout.CENTER);
        line.add(ok_commit_button,BorderLayout.EAST);

        pane.add(line,BorderLayout.SOUTH);
        return short_text_field;
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

        addGitStatusToPane(getContentPane());
        addButtonsToPanel(getContentPane());
        addTitleToPanel(getContentPane());
        short_commit_text = addShortCommitLine(getContentPane());        // We have to keep a reference to the text field because we will have to read the text inside later.

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
