
build: codeBase/SQLServer.class

codeBase/SQLServer.class: codeBase/SQLServer.java
	javac codeBase/SQLServer.java

run: codeBase/SQLServer.class
	java -cp .:jdbc/mssql-jdbc-11.2.0.jre11.jar codeBase/SQLServer

clean:
	rm codeBase/*.class
	