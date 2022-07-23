import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // realizar conexão HTTP e buscar os tops 250 filmes
        String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        
        // extrair dados que nos interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        var geradorDeFigurinhas = new GeradorDeFigurinhas();
        //exibir e manipular os dados
        for (Map<String, String> filme : listaDeFilmes) {

            String nomeFilme = filme.get("title");
            String urlImagem = filme.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");
            String ratingImdb = filme.get("imDbRating");
            double numRatingImdb = Double.parseDouble(ratingImdb);

            System.out.print("\u001b[1m");
            System.out.print("\u001b[48;2;42;122;228m");
            System.out.print("\u001b[38;2;255;255;255m");
            System.out.println(nomeFilme);
            System.out.print("\u001b[m");

            System.out.println(urlImagem);
            System.out.println("\u2B50 " + ratingImdb);
            System.out.println();

            String textoRating = null;
            if (numRatingImdb < 5){
                textoRating = "Ehhh, meio fraquinho.";
            } else if (numRatingImdb > 5 & numRatingImdb <= 7){
                textoRating = "Um filme aceitavel.";
            } else if (numRatingImdb > 7 & numRatingImdb <= 9){
                textoRating = "Cara, é bom.";
            } else if (numRatingImdb > 9) {
                textoRating = "TOPZERA!";
            }

            String nomeArquivo = "saida/" + nomeFilme.replace(":", "-")  + ".png";
            
            try{
                
                InputStream inputStream = new URL(urlImagem).openStream();
                System.out.println("Gerando imagem - [" + nomeFilme + "]");
                geradorDeFigurinhas.cria(inputStream, nomeArquivo, textoRating);
            }catch(java.io.FileNotFoundException err){
                System.out.println("Imagem não encontrada ou link inválido");
            }
        }
    }
}

