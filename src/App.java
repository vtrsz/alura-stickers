import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // realizar conexão HTTP e buscar os tops 250 filmes
        String url = "https://api.mocki.io/v2/549a5d8b";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        
        // extrair dados que nos interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        

        //exibir e manipular os dados
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.print("\u001b[1m");
            System.out.print("\u001b[48;2;42;122;228m");
            System.out.print("\u001b[38;2;255;255;255m");
            System.out.println(filme.get("title"));
            System.out.print("\u001b[m");

            System.out.println(filme.get("image"));
            System.out.println("\u2B50 " + filme.get("imDbRating"));
            System.out.println();

        }
    }
}