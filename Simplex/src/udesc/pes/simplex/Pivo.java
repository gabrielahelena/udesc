package udesc.pes.simplex;

public class Pivo {
	protected int linha;
	protected int coluna;
	protected double valor;
	
	
	
	public double getValor() {
		return valor;
	}

	public void setValor(double matriz[][]) {
		this.valor = matriz[this.linha][this.coluna];
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}

	public Pivo() {
		this.linha = 0;
		this.coluna = 0;
	}

	public Pivo(int linha, int coluna, double valor){
		this.linha = linha;
		this.coluna = coluna;
		this.valor = valor;
	}
	
	public void setLinha(int linha) {
		this.linha = linha;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
}