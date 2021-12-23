package client;

import client.Client;
import exceptions.ClientException;
import fertdt.MessageReadingException;
import fertdt.RequestMessage;
import fertdt.ResponseMessage;
import graphics.GUI;
import graphics.controllers.GUIPreloadController;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

@Getter
@Setter
public class SocketClient implements Client {
    private final InetAddress host;
    private final int port;
    private Socket socket;

    public SocketClient(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void start() throws ClientException {
//        try {
//            socket = new Socket(this.host, this.port);
            this.logicStart();
//        } catch (IOException e) {
            //e.printStackTrace();
        //}
    }

    public void logicStart() {
        System.setProperty("javafx.preloader", GUIPreloadController.class.getCanonicalName());
        GUI.launch(GUI.class);
    }

    @Override
    public ResponseMessage sendMessage(RequestMessage message) throws ClientException {
        try{
            socket.getOutputStream().write(message.getData());
            socket.getOutputStream().flush();
            return ResponseMessage.readMessage(socket.getInputStream());
        }
        catch(IOException | MessageReadingException ex){
            throw new ClientException("Can't send message.", ex);
        }
    }
}