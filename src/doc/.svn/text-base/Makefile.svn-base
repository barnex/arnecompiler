all:	manual.pdf

manual.pdf:	*.tex
	pdflatex -halt-on-error manual.tex
	pdflatex -halt-on-error manual.tex
	makeindex manual
	pdflatex -halt-on-error manual.tex

.PHONY: clean
clean:
	rm manual.pdf