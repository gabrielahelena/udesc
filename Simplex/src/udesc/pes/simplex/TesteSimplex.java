package udesc.pes.simplex;


public class TesteSimplex {
	
	static double[][] matriz = new double[][]{{3, 1, 2, 1, 0, 13},     
			{0, 0, 1, 0, 1,  2},     
			{-200, -50, -150, 0, 0, 0}};
	
	public static void main(String args[]){
		Simplex simplex = new Simplex(matriz);
		simplex.run();
	}
	
}
