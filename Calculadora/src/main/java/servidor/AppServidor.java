package servidor;

import comum.Conexao;
import comum.Requisicao;
import comum.Resposta;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppServidor {
    
    static Socket client_socket;
    static ServerSocket server_socket;
    
    public AppServidor(){
        try {
            server_socket = new ServerSocket(9600);
            System.out.println("Servidor no ar!!!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Requisicao requisicao = new Requisicao();
        Resposta resposta = new Resposta();
        
        new AppServidor();
        if(connect()) {
            requisicao = (Requisicao)Conexao.receive(client_socket);
            
            switch(requisicao.getOperacao()) {
                case '+':
                    resposta.setResultado(requisicao.getNum1() + requisicao.getNum2());
                    resposta.setStatus(0);
                    System.out.println("Vai fazer " + requisicao.getNum1() + " + " + requisicao.getNum2());
                    break;
            }
            Conexao.send(client_socket, resposta);
            
            try {
                server_socket.close();
                client_socket.close();
            } catch (IOException ex) {
                Logger.getLogger(AppServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static boolean connect(){
        try {
            client_socket = server_socket.accept();
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }
}
