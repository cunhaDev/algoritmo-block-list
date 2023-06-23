import java.util.ArrayList;
import java.util.List;

public class PrepararCenarios {
        public void start() throws InterruptedException{
            BancoEmMemoria banco = new BancoEmMemoria();
            Algoritmo algoritmo = new Algoritmo(banco);
            List<Produto> produtos = new ArrayList<>();
            
            banco.adicionarMarca("Converse");
            banco.permitirSellerParaMarca("Converse", "2801");

            banco.adicionarProduto("Perfume");
            banco.bloquearSellerParaProduto("Perfume", "8974");

            produtos.add(0, new Produto("Sandalia Rosa","A melhor sandalia que existe","Flor","0000614","1001"));
            produtos.add(1, new Produto("Estampa Rosa Florida","A melhor Estampa Rosa Florida que existe, validade próxima do vencimento!","Flor","5738935","2801"));
            produtos.add(2, new Produto("Camiseta Adidas","A melhor camisa da adidas, entre em contato: 92984808322","Adidas","5696888","1001"));
            produtos.add(3, new Produto("Camiseta Verde","Camisa da <a>adidas Verde</a>","Adidas","5696888","1001"));
            produtos.add(4, new Produto("Camiseta Rosa pink","Camiseta pink","Pink","5696684","2801"));
            produtos.add(5, new Produto("Geladeira Britania","A Melhor geladeira que existe!","Britania","6868654","8974"));
            produtos.add(6, new Produto("Camisa Verde Converse","Camiseta converse, para todos os dias!","Converse","6868654","2801"));
            produtos.add(7, new Produto("Camisa Azul Converse","Camiseta converse, para todos os dias!","Converse","6868654","1001"));
            produtos.add(8, new Produto("Perfume Amo Beleza","Camiseta converse, para todos os dias!","Bruna","6868654","8974"));

            for (Produto produto : produtos){
                StringBuilder resultado = prepararResposta(produto, algoritmo.validaProduto(produto));
                System.out.println(resultado.toString());
                Thread.sleep(2000);
            }
        }

        private StringBuilder prepararResposta(Produto produto, String resultado){
            StringBuilder mensagem = new StringBuilder();
            mensagem.append("Produto " + produto.getTitulo() + "\n");
            mensagem.append("Resultado: " + resultado);
            mensagem.append("\n");
            return mensagem;
        }
}
