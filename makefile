autogit:autogit.java mainloop.class DealWithGit.class GitRepository.class CommandLine.class GitWindows.class edit_gitignore_action.class git_diff_action.class LogMaker.class
	javac autogit.java
mainloop.class:mainloop.java
	javac mainloop.java
DealWithGit.class:DealWithGit.java  GitWindows.class
	javac DealWithGit.java
GitRepository.class:GitRepository.java 
	javac GitRepository.java
CommandLine.class:CommandLine.java
	javac CommandLine.java
GitWindows.class:GitWindows.java  edit_gitignore_action.class git_diff_action.class
	javac GitWindows.java
edit_gitignore_action.class:edit_gitignore_action.java
	javac edit_gitignore_action.java
git_diff_action.class:git_diff_action.java
	javac git_diff_action.java
LogMaker.class:LogMaker.java
	javac LogMaker.java
clean:
	rm *.class
run:autogit
	java autogit ..
