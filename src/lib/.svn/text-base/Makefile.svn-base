all:	libbarnex.o

libbarnex.o:	libbarnex.c libbarnex-asm.s
	gcc -c libbarnex.c libbarnex-asm.s

.PHONY: clean
clean:
	rm libbarnex.o