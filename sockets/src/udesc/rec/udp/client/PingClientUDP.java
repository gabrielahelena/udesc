package udesc.rec.udp.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class PingClientUDP
{

	public static void main(String[] args) throws Exception
	{
		DatagramSocket socket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("10.222.223.86");

		long SendTime = System.currentTimeMillis();
		String Message = "Ping " + SendTime + "\n";

		DatagramPacket request = new DatagramPacket(Message.getBytes(), Message.length(), IPAddress, 9876);
		socket.send(request);
		DatagramPacket reply = new DatagramPacket(new byte[1024], 1024);

		socket.receive(reply);
		long finalTime = System.currentTimeMillis();

		long diference = finalTime - SendTime;
		System.out.println("Diferença: " + finalTime + " " + diference + " milisegundos");

	}


	// private static void printData(DatagramPacket request) throws Exception
	// {
	// byte[] buf = request.getData();
	//
	// ByteArrayInputStream bais = new ByteArrayInputStream(buf);
	//
	// InputStreamReader isr = new InputStreamReader(bais);
	//
	// BufferedReader br = new BufferedReader(isr);
	//
	// String line = br.readLine();
	//
	// System.out.println("Received from " + request.getAddress().getHostAddress() + ": " + new String(line));
	// }
}
