autogit:autogit.java mainloop.java mainloop.class DealWithGit.class
	javac autogit.java
mainloop.class:
	javac mainloop.java
DealWithGit.class:
	javac DealWithGit.java
clean:
	rm *.class
run:
	java autogit
