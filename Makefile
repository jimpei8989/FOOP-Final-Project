all:
	javac -sourcepath src/ -d class/ src/Main.java

clean:
	rm -rf class/

run:
	java -cp class/ Main ~ simple simple simple
