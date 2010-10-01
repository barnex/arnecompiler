all:	bootstrap.jar

bootstrap.jar:	*.java MANIFEST.MF
	rm *.class
	javac *.java
	jar c0fm bootstrap.jar MANIFEST.MF *.class

.PHONY: clean
clean:
	rm bootstrap.jar