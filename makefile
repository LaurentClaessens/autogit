autogit:autogit.java mainloop.class
	javac autogit.java
mainloop.class:mainloop.java DealWithGit.class
	javac mainloop.java
DealWithGit.class:DealWithGit.java GitRepository.class GitWindows.class
	javac DealWithGit.java
GitRepository.class:GitRepository.java CommandLine.class
	javac GitRepository.java
CommandLine.class:CommandLine.java
	javac CommandLine.java
GitWindows.class:GitWindows.java
	javac GitWindows.java
clean:
	rm *.class
run:autogit
	java autogit
