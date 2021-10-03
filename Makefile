run: compile
	java frontEnd

compile: frontEnd.java BackEnd.java GraphADT.java CS400Graph.java
	javac frontEnd.java
	javac BackEnd.java
	javac GraphADT.java
	javac CS400Graph.java

testTTest: TTest.java  junit5.jar
	javac -cp .:junit5.jar TTest.java


test: compile testTTest
	java -jar junit5.jar -cp . --scan-classpath


clean:
	rm *.class
