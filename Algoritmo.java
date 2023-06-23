import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Algoritmo {

    private BancoEmMemoria banco;

    public Algoritmo(BancoEmMemoria banco) {
        this.banco = banco;
    }

    public String validaProduto(Produto produto){
        
        Pattern patternGeral = Pattern.compile(banco.getRegexGeral());
        Pattern patternCapturarTelefone = Pattern.compile(banco.getRegexCapturarTelefone());
        Pattern patternTagHtml = Pattern.compile(banco.getRegexTagHtml());
        Pattern patternTipoProduto = Pattern.compile(banco.getRegexTipoProduto()); // repetir a logica do matcherProdutoSellerTitulo
        Pattern patternProdutoSeller = Pattern.compile(banco.getRegexProdutoSeller());

        Matcher bloqueioGeralTitulo = patternGeral.matcher(produto.getTitulo());
        Matcher bloqueioGeralDescricao = patternGeral.matcher(produto.getDescricao());
        Matcher capturarTelefone = patternCapturarTelefone.matcher(produto.getDescricao());
        Matcher capturarTagHtmlLink = patternTagHtml.matcher(produto.getDescricao());
        Matcher matcherTipoProdutoTitulo = patternTipoProduto.matcher(produto.getTitulo());
        Matcher matcherTipoProdutoDescricao = patternTipoProduto.matcher(produto.getDescricao());
        Matcher matcherProdutoSellerTitulo = patternProdutoSeller.matcher(produto.getTitulo());
        Matcher matcherProdutoSellerDescricao = patternProdutoSeller.matcher(produto.getDescricao());

        if(bloqueioGeralTitulo.find()){
            return "O Produto " + produto.getTitulo() + " foi Bloqueado por motivo geral pelo titulo.";
        }

        if(matcherTipoProdutoTitulo.find()){
            return "O Produto " + produto.getTitulo() + " foi Bloqueado por ser de um tipo nao permitido.";
        }

        if(matcherProdutoSellerTitulo.find()){
            String tipoProduto = matcherProdutoSellerTitulo.group();
            if(validaProdutoSeller(tipoProduto, produto.getSellerId())){
                return "O Produto " + produto.getTitulo() + " foi Bloqueado porque o seller nao pode vender este produto.";
            }
        }

        if(matcherProdutoSellerDescricao.find()){
            String tipoProduto = matcherProdutoSellerDescricao.group();
            if(validaProdutoSeller(tipoProduto, produto.getSellerId())){
                return "O Produto " + produto.getTitulo() + " foi Bloqueado porque o seller nao pode vender este produto.";
            }
        }

        if (bloqueioGeralDescricao.find()){
            return "O Produto " + produto.getTitulo() + " foi Bloqueado por motivo geral pela descricao.";
        }

        if(capturarTelefone.find()){
            return "O Produto " + produto.getTitulo() + " foi Bloqueado por conter numero telefonico na descricao.";
        }

        if(capturarTagHtmlLink.find()){
            return "O Produto " + produto.getTitulo() + " foi Bloqueado por conter tags Html/links nao permitidos descricao.";
        }

        if(matcherTipoProdutoDescricao.find()){
            return "O Produto " + produto.getTitulo() + " foi Bloqueado por ser de um tipo nao permitido.";
        }

        if(validaEan(produto.getEan())){
            return "O Produto " + produto.getTitulo() + " foi Bloqueado via Ean.";
        }

        if(validaSellerMarca(produto.getMarca(), produto.getSellerId())){
             return "Produto " + produto.getTitulo() + " foi Bloqueado pois o seller nao pode vender esta marca.";
        }

        return "produto " + produto.getTitulo() + " Esta Valido!";
    }

    /* desenvolver logica no validaProduto com este metodo */
    public String bloqueioTituloDescricao(int opcao){
         switch (opcao) {
            case 1:
                return "Bloqueio via Titulo";
            case 2:
                return "Bloqueio via Descricao";
            case 3:
                return "Bloqueio Titulo e Descricao";
            default:
                return "opcao invalida, tente novamente!";
        }
    }

    private boolean validaEan(String ean){
        return banco.verificarEanBloqueado(ean);
    }

    private boolean validaSellerMarca(String marca, String seller) {
        if (banco.validarSeMarcaExiste(marca)) {
            return banco.verificarSeSellerPodeVenderMarca(marca, seller);
        }
        return false;
    }

    private boolean validaProdutoSeller(String tipoProduto, String seller){
        return banco.verificarSeSellerEstaBloqueadoParaProduto(tipoProduto, seller);
    }
}
