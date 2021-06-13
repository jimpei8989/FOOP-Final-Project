all:
	javac -sourcepath src/ -d class/ src/Main.java

clean:
	rm -rf class/

run:
	cd class && java Main
