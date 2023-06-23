import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BancoEmMemoria {

    private String regexGeral = "(?i)\\b(?:Validade próxima|Inativo|Acordo comercial)\\b";
    private String regexCapturarTelefone = "\\(?(?:[14689][1-9]|2[12478]|3[1234578]|5[1345]|7[134579])?\\)? ?(?:[2-8]|9[1-9])[0-9]{3}\\-?[0-9]{4}";
    private String regexTagHtml = "(?i)</font>|</a>|www|http";
    private String regexTipoProduto = "(?i)\\b(?:Celular|TV|Geladeira)\\b";
    private String regexProdutoSeller = "(?i)\\b(?:Perfume|Calcinha)\\b";

    private Set<String> eansData = new HashSet<String>() {{
            add("5696684");
        }};

    private Map<String, Set<String>> marcaSellerData = new HashMap<>();

    private Map<String, Set<String>> tipoProdutoSellerData = new HashMap<>();

    public boolean verificarEanBloqueado(String ean){
        if (eansData.size() > 0 && eansData.contains(ean)){
            return true;
        }
        return false;
    }

    public void adicionarMarca(String brand) {
        marcaSellerData.put(brand, new HashSet<>());
    }

    public void permitirSellerParaMarca(String brand, String seller) {
        if (marcaSellerData.containsKey(brand)) {
            Set<String> allowedSellers = marcaSellerData.get(brand);
            allowedSellers.add(seller);
        }
    }
    
    public boolean verificarSeSellerPodeVenderMarca(String brand, String seller){
        Set<String> allowedSellers = marcaSellerData.get(brand);
        if(allowedSellers.contains(seller)){
            return true;
        }
        return false;
    }

    public boolean validarSeMarcaExiste(String marca) {
        return marcaSellerData.containsKey(marca);
    }

    public void adicionarProduto(String produto) {
        tipoProdutoSellerData.put(produto, new HashSet<>());
    }

    public void bloquearSellerParaProduto(String produto, String seller) {
        if (tipoProdutoSellerData.containsKey(produto)) {
            Set<String> blockedSellers = tipoProdutoSellerData.get(produto);
            blockedSellers.add(seller);
        }
    } 
    
    public boolean verificarSeSellerEstaBloqueadoParaProduto(String tipoProduto, String seller){
            Set<String> sellerBloqueados = tipoProdutoSellerData.get(tipoProduto);
            if(sellerBloqueados != null && sellerBloqueados.contains(seller)){
                return true;
            }
            return false;
    }

    public String getRegexGeral() {
        return regexGeral;
    }

    public String getRegexCapturarTelefone() {
        return regexCapturarTelefone;
    }

    public String getRegexTagHtml() {
        return regexTagHtml;
    }

    public String getRegexTipoProduto() {
        return regexTipoProduto;
    }

    public String getRegexProdutoSeller() {
        return regexProdutoSeller;
    }
}
