autogit:autogit.java GitWindows mainloop
	javac autogit.java
mainloop:mainloop.java
	javac mainloop.java
DealWithGit:DealWithGit.java
	javac DealWithGit.java
GitRepository:GitRepository.java 
	javac GitRepository.java
CommandLine:CommandLine.java
	javac CommandLine.java
GitWindows:GitWindows.java GitRepository
	javac GitWindows.java
edit_gitignore_action:edit_gitignore_action.java 
	javac edit_gitignore_action.java
git_diff_action:git_diff_action.java 
	javac git_diff_action.java
LogMaker:LogMaker.java
	javac LogMaker.java
Configuration:Configuration.java
	javac Configuration.java

clean:
	rm *.class
run:autogit
	java autogit ..

