package br.com.joaocarvalhop.cm;

import br.com.joaocarvalhop.cm.modelo.Tabuleiro;
import br.com.joaocarvalhop.cm.visao.TabuleiroConsole;

public class Aplicacao {

	public static void main(String[] args) {

		Tabuleiro tab = new Tabuleiro(6, 6, 3);
		new TabuleiroConsole(tab);
	}
}
