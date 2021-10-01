package br.com.joaocarvalhop.cm;

import br.com.joaocarvalhop.cm.modelo.Tabuleiro;

public class Aplicacao {

	public static void main(String[] args) {

		Tabuleiro tab = new Tabuleiro(6, 6, 6);

		tab.alterarMarcacao(4, 4);
		tab.alterarMarcacao(4, 5);
		tab.abrir(3, 3);
		
		System.out.println(tab);
	}
}
