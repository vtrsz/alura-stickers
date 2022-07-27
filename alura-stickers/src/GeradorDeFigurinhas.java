import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {
        // leitura da imagem
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int adicionarAltura = 200;
        int novaAltura = altura + adicionarAltura;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original pra novo imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        var fontSize = 64;
        var fontImpact = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/impact.ttf")).deriveFont(Font.LAYOUT_LEFT_TO_RIGHT, fontSize);
        //var fonte = new Font(fontImpact, Font.BOLD, 64);
        graphics.setFont(fontImpact);
        Color outlineColor = Color.black;
        Color fillColor = Color.yellow;

        // pegar o tamanho da fonte
        String textoImagem = "TOPZERA";
        AffineTransform affinetransform = new AffineTransform();     
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
        int textoLargura = (int)(fontImpact.getStringBounds(textoImagem, frc).getWidth());
        int textoAltura = (int)(fontImpact.getStringBounds(textoImagem, frc).getHeight());

        // escrever uma frase na nova imagem
        var CentroLargura = (largura/2) - (textoLargura/2); 
        var CentroAltura = novaAltura  - textoAltura;
        graphics.setColor(outlineColor);
        for (int i = 1; i <= 5; i++) {
            graphics.drawString(textoImagem, CentroLargura + i, CentroAltura);
            graphics.drawString(textoImagem, CentroLargura - i, CentroAltura);
            graphics.drawString(textoImagem, CentroLargura, CentroAltura + i);
            graphics.drawString(textoImagem, CentroLargura, CentroAltura - i);
        }
        graphics.setColor(fillColor);
        graphics.drawString(textoImagem, CentroLargura, CentroAltura);

        // escrever a nova imagem em um arquivo
        File directory = new File("saida/");
        if (!directory.exists()){
            directory.mkdir();
        }
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }
}