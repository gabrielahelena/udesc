package udesc.rec.udp.server;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class UDPServer {

	static byte[] receiveData = new byte[64]; 
	
	public static void main(String args[]) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(9876);
		DatagramPacket receivePacket;
		
		while(true){
			receivePacket = receberPacote(receiveData, serverSocket);
			enviarResposta(serverSocket, receivePacket); 
		}
	}

	private static void enviarResposta(DatagramSocket serverSocket,	DatagramPacket receivePacket) throws IOException {
		byte[] sendData;
		String sentence = new String(receivePacket.getData());
		InetAddress IPAddress = receivePacket.getAddress();
		int port = receivePacket.getPort(); 
		String capitalizedSentence = sentence.toUpperCase();
		sendData = capitalizedSentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
		serverSocket.send(sendPacket);
	}

	private static DatagramPacket receberPacote(byte[] receiveData, DatagramSocket serverSocket) throws Exception {
		DatagramPacket receivePacket = 	new DatagramPacket(receiveData, receiveData.length);
		serverSocket.receive(receivePacket);
		System.out.println("Pacote Recebido com sucesso!");
		return receivePacket;
	}
}
