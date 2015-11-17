autogit:autogit.java mainloop.class
	javac autogit.java
mainloop.class:mainloop.java DealWithGit.class
	javac mainloop.java
DealWithGit.class:DealWithGit.java GitRepository.class GitWindows.class
	javac DealWithGit.java
GitRepository.class:GitRepository.java CommandLine.class LogMaker.class
	javac GitRepository.java
CommandLine.class:CommandLine.java
	javac CommandLine.java
GitWindows.class:GitWindows.java open_gitignore.class
	javac GitWindows.java
open_gitignore.class:open_gitignore.java
	javac open_gitignore.java
LogMaker.class:LogMaker.java
	javac LogMaker.java
clean:
	rm *.class
run:autogit
	java autogit ..
