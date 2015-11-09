autogit:autogit.java mainloop.class
	javac autogit.java
mainloop.class:mainloop.java DealWithGit.class
	javac mainloop.java
DealWithGit.class:DealWithGit.java Git.class
	javac DealWithGit.java
Git.class:Git.java CommandLine.class
	javac Git.java
CommandLine.class:CommandLine.java
	javac CommandLine.java
clean:
	rm *.class
run:autogit
	java autogit
