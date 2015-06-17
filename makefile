# Caminho dos arquivos
JPATH = src

# Especifica o compilador
JC = javac

# Especifica as opções do compilador, habilita aviso sobre erros
JFLAGS = -g

# Class path option
CP = -cp

# Comando de execucao
JR = java

# Comando terminal para limpar sem confirmação
RM = rm -f

# Classe com metodo main
MAIN = "src/" Main

# Dependencias externas
#CP = jars/core.jar:jars/controlP5.jar
JARS = jars/*

# Compilação do programa e passos das ligações de dependências
$(MAIN):
	@echo ""
	@echo "--- COMPILANDO PROGRAMA ---"
	$(JC) $(JFLAGS) $(CP) "$(JARS)" $(JPATH)/*.java
	@echo ""

clean:
	$(RM) $(JPATH)/*.class
	clear

run: $(MAIN)
	$(JR) $(CP) ".:$(JARS)" $(MAIN)
