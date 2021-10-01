package br.com.joaocarvalhop.cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private int minas;

	private final List<Campo> campos = new ArrayList<>();

	// publico pq vou ter que instanciar fora da classe
	// mas em projetos "de cabeça" o correto é privar o máximo possível
	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;

		gerarCampos();
		associarVizinhos();
		sortearMinas();
	}

	private void gerarCampos() {
		for (int l = 0; l < linhas; l++) {
			for (int c = 0; c < colunas; c++) {
				campos.add(new Campo(l, c));
			}
		}
	}

	private void associarVizinhos() {
		for (Campo c1 : campos) {
			for (Campo c2 : campos) {
				c1.adicionarVizinho(c2);
			}
		}
	}

	private void sortearMinas() {
		long minasArmadas = 0;
		Predicate<Campo> minado = c -> c.isMinado();
		
		do {
			// count retorna um long
			minasArmadas = campos.stream().filter(minado).count();
			int aleatorio = (int) (Math.random() * campos.size());
			campos.get(aleatorio).minar();
		} while(minasArmadas < minas);
	}
	
	public boolean objetivoAlcancado() {
		Predicate<Campo> objtAlcancado = c -> c.objetivoAlcancado();
		return campos.stream().allMatch(objtAlcancado);
	}
	
	public void reiniciar() {
		campos.stream().forEach(c -> c.reiniciar());
		sortearMinas();
	}
	
	public String toString() {
		return "";
	}
}
