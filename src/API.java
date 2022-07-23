public enum API {
    NASA("https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json", new ExtratorDeConteudoDaNasa()),
    IMDB("https://raw.githubusercontent.com/alura-cursos/imersao-java/api/IMDB-top-250.json", new ExtratorDeConteudoDoIMDB()),
    MEME("https://api.mocki.io/v2/549a5d8b/Memes", new ExtratorDeConteudoDeMeme());

    private String url;
    private ExtratorDeConteudo extrator;
    API(String url, ExtratorDeConteudo extrator) {
        this.url = url;
        this.extrator = extrator;
    }
    public String url() {
        return url;
    }
    public ExtratorDeConteudo extrator() {
        return extrator;
    }
}