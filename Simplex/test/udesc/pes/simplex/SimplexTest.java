package udesc.pes.simplex;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import udesc.pes.simplex.Pivo;
import udesc.pes.simplex.Simplex;



public class SimplexTest {
	
	private Simplex simplex;
	
	private double[][] matrizNaoOtima = new double[][]{{3, 2, 1, 0, 10},
													   {1, 1, 0, 1,  6}, 
													   {4, 0, 0, 1,  8}, 
													   {-9, -4, 0, 0, 0}};
	
	private double[][] matrizOtima =  new double[][]{{3, 1, 1, 0, 13},
													 {0, 0, 0, 1,  2}, 
													 {4, 5, 0, 0, 0}};
	
	@Before
	public void run(){
		simplex = new Simplex(matrizNaoOtima);
	}
	
	@Test
	public void testeIsOtima(){
		assertFalse(simplex.isOtima());  
	}
	
	@Test
	public void testeIsOtima2(){
		simplex.setMatriz(matrizOtima);
		assertTrue(simplex.isOtima());  
	}
	
	@Test
	public void testeEscolhePivo(){
		simplex.escolhePivo();
		Pivo pivo = simplex.getPivo();
		
		assertTrue(pivo.getColuna() == 0);
		assertTrue(pivo.getLinha() == 2);
	}

	
	@Test
	public void multiplicaLinhaTest(){
		simplex.setMatriz(matrizNaoOtima);
		simplex.escolhePivo();
		simplex.redefineLinhaPivo();
		Pivo pivo = simplex.getPivo();
		
		double base = matrizNaoOtima[0][pivo.getColuna()];
		
		double [] resultado = simplex.multiplicaLinha(base, pivo.getLinha());
		
		double [] expected = new double[]{3.0, 0.0, 0.0, 0.75, 6.0};
		for(int coluna = 0; coluna < resultado.length; coluna++)
			assertTrue(resultado[coluna] == expected[coluna]);
	}
	
	@Test
	public void verificaPivoTest(){
		simplex.setMatriz(matrizNaoOtima);
		simplex.escolhePivo();
		simplex.redefineLinhaPivo();
		Pivo pivo = simplex.getPivo();
		assertTrue(pivo.getValor() == 1);
	}

}
