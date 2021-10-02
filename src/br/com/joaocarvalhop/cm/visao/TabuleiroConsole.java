package br.com.joaocarvalhop.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.joaocarvalhop.cm.excecao.ExplosaoException;
import br.com.joaocarvalhop.cm.excecao.SairException;
import br.com.joaocarvalhop.cm.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	Scanner entrada = new Scanner(System.in);

	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;

		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;

			// enquanto continuar for true 
			while (continuar) {
				cicloDoJogo();
				
				System.out.println("Deseja jogar novamente? (S/n) ...");
				String resposta = entrada.next();

				if ("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else {
					tabuleiro.reiniciar();
				}
			}
		} catch (SairException e) {
			System.out.println("Tchau ;)");
		} finally {
			// independentemente do user sair do jogo ele vai passar aqui no finally
			entrada.close();
		}
	}

	private void cicloDoJogo() {
		try {
			while (!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				
				String digitado = capturarValorDigitado("Digite (x, y) >>> ");
				
				// transformando o valor digitado String em int
				// Arrays.toString(digitado.split(","));
				// System.out.println(Arrays.toString(digitado.split(",")));
				// Arrays.stream(digitado.split(",")).map(e -> Integer.parseInt(e));
				Iterator<Integer> xy = Arrays.stream(digitado.split(",")).map(e -> Integer.parseInt(e.trim())).iterator();

				digitado = capturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar: ");
				
				if ("1".equals(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				} else if ("2".equals(digitado)) {
					tabuleiro.alterarMarcacao(xy.next(), xy.next());
				}
			}
			
			System.out.println("Você ganhou :D");
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Você perdeu :(");
		}
	}
	
	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();
		
		if ("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		return digitado;
	}
}
