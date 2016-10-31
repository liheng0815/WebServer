package com.leeh.Utils;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

public class CloseUtil {
	public static void closeIOStream(Closeable...IO){
		for (Closeable closeable : IO) {
			try {
				if (closeable != null) {
					closeable.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeSocket(Socket client){
		try {
			if (client != null) {
				client.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
