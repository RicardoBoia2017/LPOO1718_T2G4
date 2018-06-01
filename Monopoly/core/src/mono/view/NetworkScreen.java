package mono.view;

import mono.model.Game;
import mono.view.AbstractScreen;
import mono.view.swapper.ScreenEnum;
import mono.view.swapper.ScreenManager;
import mono.view.swapper.UIFactory;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class NetworkScreen extends AbstractScreen {
    private Skin skin;
    private Stage stage;
    private Label labelDetails;
    private Label labelMessage;
    private Label textLabel1;
    private Label textLabel2;
    private TextButton sendMessage;
    private TextArea textIPAddress;
    private TextArea textMessage;
    private TextButton goBack;
    private ServerSocket serverSocket;

	@Override
	public void buildStage() {
        
        skin = new Skin(Gdx.files.internal("plain-james/skin/plain-james-ui.json"));

        List<String> addresses = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            for(NetworkInterface ni : Collections.list(interfaces)){
                for(InetAddress address : Collections.list(ni.getInetAddresses()))
                {
                    if(address instanceof Inet4Address){
                        addresses.add(address.getHostAddress());
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        
        String ipAddress = new String("");
        for(String str:addresses)
        {
            ipAddress = ipAddress + str + "\n";
        }
        
        labelDetails = new Label(ipAddress,skin);
        labelDetails.setPosition(120, 800);
        
        createAndAddSceneElements();
        
        new Thread(new Runnable(){

            @Override
            public void run() {
                ServerSocketHints serverSocketHint = new ServerSocketHints();
                serverSocketHint.acceptTimeout = 0;
                
                serverSocket = Gdx.net.newServerSocket(Protocol.TCP, Game.getInstance().getCurrentSocketUsed(), serverSocketHint);
                
                while(true){
                    Socket socket = serverSocket.accept(null);
                    
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
                    
                    try {
                        labelMessage.setText(buffer.readLine());    
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start(); 
	}
	
	private void createAndAddSceneElements() {
		createLabels();
        
        createButtonForSendingMessage();
        createButtonForGoingBack();
        
        addActor(labelDetails);
        addActor(labelMessage);
        addActor(textIPAddress);
        addActor(textMessage);
        addActor(textLabel1);
        addActor(textLabel2);
        addActor(sendMessage);
        addActor(goBack);
	}
	
	private void createButtonForSendingMessage() {
        sendMessage = new TextButton("Send message",skin);
		sendMessage.setPosition(120, 75);
		sendMessage.setWidth(170);
		
        sendMessage.addListener(new ClickListener(){
            @Override 
            public void clicked(InputEvent event, float x, float y){
                
                String textToSend = new String();
                if(textMessage.getText().length() == 0)
                    textToSend = "You sent nothing!\n";
                else
                    textToSend = textMessage.getText() + ("\n"); 
                
                SocketHints socketHints = new SocketHints();
                socketHints.connectTimeout = 4000;
                Socket socket = Gdx.net.newClientSocket(Protocol.TCP, textIPAddress.getText(), 0, socketHints);
                try {
                    socket.getOutputStream().write(textToSend.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
	}
	
	private void createLabels() {
        textLabel1 = new Label("<Enter ip adress>",skin);
        textLabel1.setPosition(120, 400);
		
        textIPAddress = new TextArea("",skin);
        textIPAddress.setPosition(120, 350);
        
        textLabel2 = new Label("<Enter message>",skin);
        textLabel2.setPosition(120, 250);
        
        textMessage = new TextArea("",skin);
        textMessage.setPosition(120, 200);
        
        labelMessage = new Label("<Awaits Messages>",skin);
        labelMessage.setPosition(120, 600);
	}
	
	private void createButtonForGoingBack() {
        goBack = new TextButton("Go back",skin);
        goBack.setPosition(500, 500);
        goBack.setWidth(170);
        
        goBack.addListener(new ClickListener(){
            @Override 
            public void clicked(InputEvent event, float x, float y){
            	Game.getInstance().incrementCurrentSocket();
            	ScreenManager.getInstance().showScreen(ScreenEnum.GAME, Game.getInstance().getCurrentPlayer().getBoardPiece().getType());
            }
        });
	}
	
}
