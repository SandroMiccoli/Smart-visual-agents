FILE = tp1

all: pdflatex clear

pdflatex:
	pdflatex ${FILE}.tex
	bibtex ${FILE}
	pdflatex ${FILE}.tex
	pdflatex ${FILE}.tex
	open ${FILE}.pdf
clear:
	rm -f *.log *.aux *.bbl *.blg *.dvi *.lot *.toc *.lof *.*~
