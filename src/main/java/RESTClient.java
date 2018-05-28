import rsirest2.demo.Message;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class RESTClient {

    public static void main(String[] args) {
        get1();
        get2();
        get3();
        post4();
    }

    private static void get1(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/messages/1");
        String message = target.request(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println("response: " + message);
    }

    private static void get2(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/messages/1");
        Response response = target.request().get();
        System.out.println("response: " + response);
        Message message = response.readEntity(Message.class);
        System.out.println("message: " + message.toString());
    }

    private static void get3(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/messages");
        List<Message> messages = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Message>>(){});
        System.out.println("response: " + messages);
    }

    private static void post4(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/messages");
        Message message = new Message(9L,"RSI", "ja");
        Response response = target.request().post(Entity.json(message));
        System.out.println("response: " + response);
    }
}
