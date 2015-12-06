autogit:autogit.java GitWindows mainloop 
	javac autogit.java
mainloop:mainloop.java
	javac mainloop.java
DealWithGit:DealWithGit.java
	javac DealWithGit.java
GitRepository:GitRepository.java CommandLine combine
	javac GitRepository.java
CommandLine:CommandLine.java
	javac CommandLine.java
GitWindows:GitWindows.java GitRepository
	javac GitWindows.java
LogMaker:LogMaker.java
	javac LogMaker.java
Configuration:Configuration.java
	javac Configuration.java
combine:combine.java
	javac combine.java

clean:
	rm *.class
run:autogit
	java autogit ..

