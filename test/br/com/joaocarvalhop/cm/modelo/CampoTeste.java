package br.com.joaocarvalhop.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import br.com.joaocarvalhop.cm.excecao.ExplosaoException;

public class CampoTeste {

	private Campo campo;

	@BeforeEach
	void CampoInicio() {
		campo = new Campo(3, 3);
	}

	@Test
	void testeVizinhoDistancia1() {
		// horizontal e vertical
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void testeVizinhoDistancia2() {
		// diagonal
		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void testeNaoVizinho() {
		// testar caso clique fora do campo
		Campo vizinho = new Campo(1, 1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		// vai ver se é falso
		assertFalse(resultado);
	}

	@Test
	void testeValorPadraoAtrubuidoMarcado() {
		assertFalse(campo.isMarcado());
	}

	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}

	@Test
	void testeAlternarMarcacaoDuasChamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}

	@Test
	void testeNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}

	@Test
	void testeNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}

	@Test
	void testeMinadoMarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}

	@Test
	void testeMinadoNaoMarcado() {
		// resulta na explosao
		campo.minar();
		// assertThrows -> eu espero que seja lancada a excecao de explosao
		// ref de arquivo .class
		// expr lambda chamando o método abrir
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
	}

	@Test
	void abrirComVizinhos1() {
		// teste de abertura de vizihos
		Campo campo22 = new Campo(2, 2);
		Campo campo11 = new Campo(1, 1);
		campo22.adicionarVizinho(campo11);

		campo.adicionarVizinho(campo22);
		campo.abrir();

		assertTrue(campo22.isAberto() && campo11.isAberto());
	}

	@Test
	void abrirComVizinhos2() {
		// teste de abertura de vizihos
		Campo campo22 = new Campo(2, 2);
		Campo campo12 = new Campo(1, 2);
		campo12.minar();

		Campo campo11 = new Campo(1, 1);
		campo22.adicionarVizinho(campo12);
		campo22.adicionarVizinho(campo11);

		campo.adicionarVizinho(campo22);
		campo.abrir();

		// posso usar assim !campo11.isAberto()
		assertTrue(campo22.isAberto() && campo11.isFechado());
	}
	
	@Test
	 void testeObjetivoAlcancadoDesvendado(){
		Campo c7 = new Campo(2, 2);
		c7.abrir();
		c7.minar();
		assertFalse(c7.objetivoAlcancado());
	}
	
	@Test
	 void testeObjetivoAlcancadoProtegido(){
		Campo c8 = new Campo(2, 2);
		c8.abrir();
		c8.isMarcado();
		assertTrue(c8.objetivoAlcancado());
	}

	@Test
	 void testeToString() {

		// caso marcado = return x
		Campo c1 = new Campo(2, 2);
		c1.alternarMarcacao();

		System.out.println(c1.toString());

		// caso aberto && minado = return *
		Campo c2 = new Campo(2, 2);
		c2.abrir();
		c2.minar();

		System.out.println(c2.toString());

		// caso aberto && minasNaVizinhanca() > 0 = return
		// Long.toString(minasNaVizinhanca())
		Campo c3 = new Campo(2, 2);
		c3.abrir();
		c3.minasNaVizinhanca();

		System.out.println(c3.toString());

		// caso aberto && minado = return " "
		Campo c4 = new Campo(2, 2);
		c4.abrir();

		System.out.println(c4.toString());

		// else = return ?
		Campo c5 = new Campo(2, 4);

		System.out.println(c5.toString());
	}
}