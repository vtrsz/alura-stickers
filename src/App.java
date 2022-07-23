import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        API minhaAPI = API.MEME;

        var http = new ClientHttp();
        String json = http.buscaDados(minhaAPI.url());

        var geradorDeFigurinhas = new GeradorDeFigurinhas();

        //exibir e manipular os dados
        List<Conteudo> conteudos = minhaAPI.extrator().extraiConteudos(json);

        for (int i = 0; i < 3; i++) {
            Conteudo conteudo = conteudos.get(i);

            String titulo = conteudo.titulo();
            String urlImagem = conteudo.urlImagem();

            System.out.print("\u001b[1m");
            System.out.print("\u001b[48;2;42;122;228m");
            System.out.print("\u001b[38;2;255;255;255m");
            System.out.println(titulo);
            System.out.print("\u001b[m");

            System.out.println(urlImagem);

            String nomeArquivo = "saida/" + titulo.replace(":", "-")  + ".png";

            try{
                
                InputStream inputStream = new URL(urlImagem).openStream();
                System.out.println("Gerando imagem - [" + titulo + "]");
                geradorDeFigurinhas.cria(inputStream, nomeArquivo);
            }catch(FileNotFoundException err){
                System.out.println("Imagem não encontrada ou link inválido");
            }
        }
    }
}
