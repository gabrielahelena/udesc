package udesc.pes.simplex;

import java.util.logging.Logger;


public class Simplex{
	
	private final Logger log = Logger.getAnonymousLogger();
	private Pivo pivo;
	private double[][] matriz = null;
	private int COLUNAS;
	private int LINHAS;
	
	public Simplex(double[][] matriz){
		this.matriz = matriz;
		COLUNAS = matriz[0].length;
		LINHAS = matriz.length;
		pivo = new Pivo();
	}
	
	public void run(){
		log.info("Resolvendo sistema linear...");
		 while(!isOtima()) {
			 escolhePivo();
			 escalona();
		 }
	}
	
	public void escalona() {
		log.info("Escalonando...");
		redefineLinhaPivo();
		
		for(int linha = 0; linha < LINHAS; linha++){
			if(linha != pivo.linha){
				double base = matriz[linha][pivo.coluna];
				
				if(pivo.valor < base){
					double[] linhaBase = multiplicaLinha(base, pivo.linha);
					somaLinhas(linha, linhaBase);
				}
			}
				
		}
		
	}

	public void somaLinhas(int linha, double[] linhaBase) {
		log.info("Somando linha alvo com a linha do pivo...");
		for(int coluna = 0; coluna < linhaBase.length; coluna++){
			matriz[linha][coluna] = matriz[linha][coluna] - linhaBase[coluna]; 
		}
	}
	
	/**
	 * @param taxa pelo qual a linha sera multiplicada
	 * @return linha multiplicada
	*/
	public double[] multiplicaLinha(double taxa, int indice) {
		log.info("Multiplicando linha do pivo...");
		
		double linha [] = matriz[indice];
		for(int coluna = 0; coluna < linha.length; coluna++)
			linha[coluna] *= taxa; 

		return linha;
	}

	/**
	 * Quando pivo diferente de 1, divide a linha inteira pelo valor do pivo para que o mesmo seja 1.
	 * 
	 */
	public void redefineLinhaPivo(){
		log.info("Redefinindo linha do pivo...");
		
		double valorPivo = matriz[pivo.linha][pivo.coluna];
		if(valorPivo != 1) {
			for(int  c = 0 ; c < matriz[pivo.linha].length; c++){
				matriz[pivo.linha][c] /=  valorPivo;
			}
		}
		pivo.setValor(matriz);
	}
	
	//Na funcao objetiva pegar a coluna com menor valor.
	//Escolha a linha que resulte no menor valor(nao negativo) da constante pelo
	//coeficiente. Pivo tem que ser 1. Zero nao pode ser pivo.
	public Pivo escolhePivo() {
		log.info("Escolhendo pivo...");
		pivo.setColuna(escolheColuna());
		pivo.setLinha(escolheLinha());
		pivo.setValor(matriz);
		return pivo;
	}

	private int escolheLinha() {
		log.info("Escolhendo linha do pivo...");
		double menorValorLinha =  matriz[0][COLUNAS-1] / matriz[0][pivo.coluna];
		for(int linha = 0; linha < LINHAS - 1; linha++){
			if(matriz[linha][COLUNAS-1] / matriz[linha][pivo.coluna] < menorValorLinha){
				menorValorLinha =  matriz[linha][COLUNAS-1] / matriz[linha][pivo.coluna];
				pivo.linha = linha;
			}
		}
		return pivo.linha;
	}

	private int escolheColuna() {
		log.info("Escolhendo coluna do pivo...");
		double menor = 0;
		for(int linha = 0; linha < LINHAS; linha++){
			if(matriz[LINHAS-1][linha] < menor){
				menor = matriz[LINHAS-1][linha];
				pivo.coluna = linha;
			}
		}
		return pivo.coluna;
	}

	public void imprimeSolucao() {
		// TODO Auto-generated method stub
	}

	public void imprimeTabela() {
		// TODO Auto-generated method stub
	}

	public boolean equals(double a, double b) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isOtima() {
		for(int coluna = 0; coluna < LINHAS; coluna++)
			if(matriz[LINHAS-1][coluna] < 0){
				log.info("Matriz não é ótima...");
				return false;
			}
		
		log.info("Matriz ótima...");
		return true;
	}

	public void setMatriz(double[][] matriz) {
		this.matriz = matriz;
		COLUNAS = matriz[0].length;
		LINHAS = matriz.length;
	}

	public Pivo getPivo() {
		return pivo;
	}

	public void setPivo(Pivo pivo) {
		this.pivo = pivo;
	}

}
