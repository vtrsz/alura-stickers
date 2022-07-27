import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDeLinguagens implements ExtratorDeConteudo {
    public List<Conteudo> extraiConteudos(String json) {

        // extrair dados que nos interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        // popular a lista de conteudos
        for (Map<String, String> atributos : listaDeAtributos) {
            String titulo = atributos.get("title");
            String urlImagem = atributos.get("image");

            Conteudo conteudo = new Conteudo(titulo, urlImagem);
            conteudos.add(conteudo);
        }

        return conteudos;
    }
}
