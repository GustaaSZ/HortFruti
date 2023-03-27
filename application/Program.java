package exerciciosProprios.usandoArray.hortifrut.application;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exerciciosProprios.usandoArray.hortifrut.entities.Hortifrut;

public class Program {
    
    // 1 - Programa que leia os dados de um produto, nome, id, preco, estoque
    // pedir para o usuário que informe se há um estoque inicial. Só é permitido  mexer no estoque por meio de método de adicionar e retirar do estoque. Mostrar a média de preços dos produtos cadastrados.
    
    // - Mostrar produto com maior preço e menor preço
    // - Mostrar produto que tem maior quantidade no estoque e menor quantidade
    // - Não permitir a repetição de id e nem de nome do produto
    // - Programa que possibilite a remoção de produto
    // - Programa que possibilite a atualização de certo produto
    // - Listagem de produtos

    // - Fazer com listas (ArrayList): utilizar laço for each e funções lambida(Predicado)

    public static void main(String[] args){
        try(Scanner ler = new Scanner(System.in)){

            List<Hortifrut> lista = new ArrayList<>();

            // Variaveis
            int op;
            Hortifrut hort;
            int position;
            double maiorPreco;
            double menorPreco;
            Integer maiorQuantity; 
            Integer menorQuantity; 
            String resp;
            
            do{

                System.out.println("\n1 - Cadastrar Produto:");
                System.out.println("2 - Remover Produto:");
                System.out.println("3 - Atualizar Produto:");
                System.out.println("4 - Adicionar Quantidade no estoque de um certo produto:");
                System.out.println("5 - Retirar Quantidade do estoque de um certo produto:");
                System.out.println("6 - Mostrar Produto com maior Preço:");
                System.out.println("7 - Mostrar Produto com menor Preço:");
                System.out.println("8 - Mostrar Produto com maior quantidade em estoque:");
                System.out.println("9 - Mostrar Produto com menor quantidade em estoque:");
                System.out.println("10 - Mostrar o valor total de um certo produto com base no estoque:");
                System.out.println("11 - Listar Produtos:");
                System.out.println("12 - Sair\n");

                System.out.println("Digite a opcao desejada:");
                op = ler.nextInt();

                switch(op){
                    
                    case 1: // Cadastrar produto

                        System.out.println("Quantos Produtos deseja cadastrar:");
                        int n = ler.nextInt();
                        
                        // Leitura dos dados:
                        for(int i = 0; i < n; i++){

                            System.out.println("Digite o nome do Produto:");
                            ler.nextLine();
                            String nameProduct = ler.nextLine();

                            // // Verificação se o nome digitado já foi cadastrado em outro produto
                            for(i = 0; i < lista.size(); i++){
                                if(lista.get(i).getNomeProduto().equalsIgnoreCase(nameProduct)){
                                    System.out.println("Este nome de produto ja foi cadastrado e pertence ao produto de codigo "+lista.get(i).getCodigo()+"! Por favor, insira outro nome:");
                                    nameProduct = ler.nextLine();
                                }
                            }

                            System.out.println("Digite o código do produto:");
                            Integer codigo = ler.nextInt();

                            // Variavel auxiliar que recebe o nome digitado pelo usuário
                            int codAux = codigo;

                            // Verificação se o codigo digitado já foi cadastrado em outro produto
                            hort = lista.stream().filter(x -> x.getCodigo() == codAux).findFirst().orElse(null);

                            while(hasCod(lista, codigo)){

                                System.out.println("Este codigo "+codigo+" de produto ja foi cadastrado e pertence ao produto "+hort.getNomeProduto()+"! Por favor, insira outro codigo:");
                                codigo = ler.nextInt();
                            }

                            System.out.println("Digite o valor do produto:");
                            Double valor = ler.nextDouble();

                            System.out.println("Deseja informar inicialmente um estoque deste produto? [sim/nao]");
                            resp = ler.next();

                            if(resp.equalsIgnoreCase("sim")){

                                System.out.println("Digite a quantidade deste produto no seu estoque:");
                                int quantidadeInStock = ler.nextInt();
                                lista.add(new Hortifrut(nameProduct, codigo, quantidadeInStock, valor));

                            } else {

                                lista.add(new Hortifrut(nameProduct, codigo, valor));

                            }
                            System.out.println();
                        }

                    break;

                    case 2: // Remover Produto

                        System.out.println("Deseja remover o produto informando o nome ou codigo?");
                        resp = ler.next();

                        if(resp.equalsIgnoreCase("Codigo")){

                            System.out.println("Digite o codigo do produto que deseja remover:");
                            int codProduto = ler.nextInt();
                            hort = lista.stream().filter(x -> x.getCodigo() == codProduto).findFirst().orElse(null);

                            if(hort != null){
                                lista.remove(hort);
                                System.out.println(hort.getNomeProduto()+" removido da sua lista de produtos!\n");
                            } else{
                                System.out.println("\nEsse codigo nao Existe!\n");
                            }

                        } else  if(resp.equalsIgnoreCase("nome")){

                            System.out.println("Digite o nome do produto que deseja remover:");
                            ler.nextLine();
                            String nomeProductAux = ler.nextLine();
                            
                            hort = lista.stream().filter(x -> x.getNomeProduto().equalsIgnoreCase(nomeProductAux)).findFirst().orElse(null);

                            if(hort != null){
                                lista.remove(hort);
                                System.out.println(hort.getNomeProduto()+" removido da sua lista de produtos!\n");
                            } else{
                                System.out.println("\nEsse nome de produto nao Existe!\n");
                            }

                        }

                    break;

                    case 3: // Atuaizar produto
                    System.out.println("\nDeseja atualizar o produto informando o nome ou codigo?");
                    resp = ler.next();

                    if(resp.equalsIgnoreCase("codigo")){

                        System.out.println("Digite o codigo do produto que deseja atualizar:");
                        int codProduto = ler.nextInt();

                        hort = lista.stream().filter(x -> x.getCodigo() == codProduto).findFirst().orElse(null);

                        for(int i = 0; i < lista.size(); i ++){

                            if(hort != null){

                                // Removendo o produto que desejo atualizar
                                System.out.println("\nProduto "+hort.getNomeProduto()+" encontrado!\n");
                                lista.remove(hort);

                                // Obtendo os novos dados do produto que estou add
                                System.out.println("Digite o novo nome do Produto:");
                                ler.nextLine();
                                String nameProduct = ler.nextLine();

                                // // Verificação se o nome digitado já foi cadastrado em outro produto
                                for(i = 0; i < lista.size(); i++){
                                    if(lista.get(i).getNomeProduto().equalsIgnoreCase(nameProduct)){
                                        System.out.println("Este nome de produto ja foi cadastrado e pertence ao produto de codigo "+lista.get(i).getCodigo()+"! Por favor, insira outro nome:");
                                        nameProduct = ler.nextLine();
                                    }
                                }

                                System.out.println("Digite o novo código do produto:");
                                int codigo = ler.nextInt();

                                // Verificação se o codigo digitado já foi cadastrado em outro produto
                                while(hasCod(lista, codigo)){

                                    System.out.println("Este codigo "+codigo+" de produto ja foi cadastrado e pertence ao produto "+lista.get(i).getNomeProduto()+"! Por favor, insira outro codigo:");
                                    codigo = ler.nextInt();
                                }

                                System.out.println("Digite o novo valor do produto:");
                                Double valor = ler.nextDouble();

                                // Adicionando o novo produto a lista de produtos
                                lista.add((codigo - 1), new Hortifrut(nameProduct, codigo, valor));
                                System.out.println("\nProduto atualizado com sucesso!\n");

                            } else {
                                System.out.println("\nCodigo "+codProduto+" nao encontrado em sua lista. Por favor, cadastre um produto com este codigo antes!\n");
                                break;
                            }
                        }
                        
                    } else if(resp.equalsIgnoreCase("nome")){


                        System.out.println("Digite o nome do produto que deseja atualizar:");
                        ler.nextLine();
                        String nome = ler.nextLine();

                        // Verificando na lista
                        hort = lista.stream().filter(x -> x.getNomeProduto().equalsIgnoreCase(nome)).findFirst().orElse(null);

                        for(int i = 0; i < lista.size(); i ++){

                            if(hort != null){

                                // Removendo o produto que desejo atualizar
                                System.out.println("\nProduto "+hort.getNomeProduto()+" encontrado!\n");
                                lista.remove(hort);

                                // Obtendo os novos dados do novo produto
                                System.out.println("Digite o novo nome do Produto:");
                                String newNameProduct = ler.nextLine();


                                // Verificação se o nome digitado já foi cadastrado em outro produto
                                for(i = 0; i < lista.size(); i++){
                                    if(lista.get(i).getNomeProduto().equalsIgnoreCase(newNameProduct)){
                                        System.out.println("Este nome de produto ja foi cadastrado e pertence ao produto de codigo "+lista.get(i).getCodigo()+"! Por favor, insira outro nome:");
                                        newNameProduct = ler.nextLine();
                                    }
                                }

                                System.out.println("Digite o novo código do produto:");
                                int codigo = ler.nextInt();


                                // // Verificação se o codigo digitado já foi cadastrado em outro produto
                                while(hasCod(lista, codigo)){
                                    System.out.println("Este codigo "+codigo+" de produto ja foi cadastrado e pertence ao produto "+hort.getNomeProduto()+"! Por favor, insira outro codigo:");
                                    codigo = ler.nextInt();
                                }

                                System.out.println("Digite o novo valor do produto:");
                                Double valor = ler.nextDouble();

                                lista.add((codigo - 1), new Hortifrut(newNameProduct, codigo, valor));
                                System.out.println("\nProduto atualizado com sucesso!\n");

                            } else {
                                System.out.println("\n"+nome+" nao encontrado no seu estoque. Por favor, cadastre um produto com este nome antes!\n");
                            }
                        }

                    }
                    break;


                    case 4: // Adicionar quantidade adicional in stock

                        System.out.println("\nDigite o codigo do produto que deseja adicionar a quantidade no estoque:");
                        Integer cod = ler.nextInt();

                        hort = lista.stream().filter(x -> x.getCodigo() == cod).findFirst().orElse(null);

                        if(hort != null){
                            System.out.println("\nDigite a quantidade que deseja adicionar no produto "+hort.getNomeProduto()+":");
                            int quant = ler.nextInt();
                            hort.adicionarInStock(quant);

                            System.out.println();
                            System.out.print("Quantidade adicionada do estoque!\n");
                        } else {
                            System.out.println("\nEsse codigo nao Existe!\n");
                        }

                    break;

                    case 5: // retirar quantidade do stock

                        System.out.println("\nDigite o codigo do produto que deseja retirar uma certa quantidade no estoque:");
                        cod = ler.nextInt();

                        hort = lista.stream().filter(x -> x.getCodigo() == cod).findFirst().orElse(null);

                        if(hort != null){
                            System.out.println("\nDigite a quantidade que deseja retirar no produto "+hort.getNomeProduto()+":");
                            int quant = ler.nextInt();
                            hort.retirarDoStock(quant);

                            System.out.println();
                            System.out.print("Quantidade retirada do estoque!\n");
                        } else {
                            System.out.println("\nEsse codigo nao Existe!\n");
                        }
                    break;

                    case 6: // Mostrar produto com maior preço

                        if(!lista.isEmpty()){
                            maiorPreco = lista.get(0).getValor();
                            position = 0;
                            for(int i = 0; i < lista.size(); i++){
                                // maiorPreco = lista.get(i).getValor();
                                if(lista.get(i).getValor() > maiorPreco){
                                    maiorPreco = lista.get(i).getValor();
                                    position = i;
                                }
                            }
                            System.out.println("\nProduto com Maior Preço ["+lista.get(position).getNomeProduto()+": R$"+String.format("%.2f", maiorPreco)+"]");
                            System.out.println();
                        } else {
                            System.out.println("\nSua lista de Produtos esta vazia!");
                        }
                        
                    break;
                        
                    case 7: // Mostrar produto com maior preço

                        if(!lista.isEmpty()){
                            menorPreco = lista.get(0).getValor();
                            position = 0;
                            for(int i = 0; i < lista.size(); i++){

                                if(lista.get(i).getValor() < menorPreco){
                                    menorPreco = lista.get(i).getValor();
                                    position = i;
                                }
                            }
                            System.out.println("\nProduto com Menor Preço ["+lista.get(position).getNomeProduto()+": R$"+String.format("%.2f", menorPreco)+"]");
                            System.out.println();
                        } else {
                            System.out.println("\nSua lista de Produtos esta vazia!");
                        }
                        
                    break;

                    case 8: // Mostrar produto com maior quantidade em estoque

                        // Se a lista hortifrut n estiver vazia
                        if(!lista.isEmpty()){

                            maiorQuantity = lista.get(0).getQuantityInStock();
                            position = 0;

                            for(int i = 0; i < lista.size(); i++){

                                if(lista.get(i).getQuantityInStock() > maiorQuantity){
                                    maiorQuantity = lista.get(i).getQuantityInStock();
                                    position = i;
                                }
                            }
                            System.out.println("\nPRODUTO COM MAIOR ESTOQUE:");
                            System.out.print(lista.get(position).getNomeProduto()+" ");
                            System.out.println(lista.get(position).getQuantityInStock()+" Kg");

                        } else {
                            System.out.println("\nSua lista de Produtos esta vazia!");
                        }
                        
                    break;

                    case 9: // Mostrar produto com menor quantidade no estoque

                        // Se a lista hortifrut n estiver vazia
                        if(!lista.isEmpty()){

                            menorQuantity = lista.get(0).getQuantityInStock();
                            position = 0;

                            for(int i = 0; i < lista.size(); i++){

                                if(lista.get(i).getQuantityInStock() < menorQuantity){
                                    menorQuantity = lista.get(i).getQuantityInStock();
                                    position = i;
                                }
                            }
                            System.out.println("\nPRODUTO COM MENOR ESTOQUE:");
                            System.out.print(lista.get(position).getNomeProduto()+" ");
                            System.out.println(lista.get(position).getQuantityInStock()+" Kg");

                        } else {
                            System.out.println("\nSua lista de Produtos esta vazia!");
                        }
                        
                    break;

                    case 10: // Listar o valor total de um certo produto com base no estoque

                        System.out.println("\nDigite o codigo do produto que deseja verificar o valor total:");
                        cod = ler.nextInt();

                        hort = lista.stream().filter(x -> x.getCodigo() == cod).findFirst().orElse(null);

                        if(hort != null){
                            System.out.println("\n"+hort.getNomeProduto()+": R$"+hort.precoTotalProduto());
                        } else {
                            System.out.println("\nEsse codigo nao Existe!\n");
                        }
                        System.out.println();
                    break;

                    case 11: // Listar produtos

                        // Se a lista de produtos não estiver vazia
                        if(!lista.isEmpty()){

                            double soma = 0;

                            System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
                            
                            System.out.println("\nLISTA DE PRODUTOS COMERCIAL HORTIFRUT");
                            for(Hortifrut obj : lista){
                                System.out.println(obj);
                            }
                            System.out.println();

                            // Calculo do valor total de cada produto, litando o valor total em produtos do usuário
                            for(int i = 0; i < lista.size(); i++){
                                soma += lista.get(i).precoTotalProduto();
                            }
                            System.out.println("Valor total em produtos no seu estoque: "+String.format("%.2f", soma)+"\n");
                            
                            System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
                        } else {
                            System.out.println("\nSua lista de Produtos esta vazia!");
                        }
                        
                    break;

                    case 12:
                        System.out.println("\nVolte Sempre :)\n");
                    break;

                    default:
                        System.out.println("\nValor invalido!");
                    break;
    
                }
            }while(op != 12);

            ler.close();
        }
    }

    // Método para verificar se o id é repetido
    public static boolean hasCod(List<Hortifrut> lista,Integer cod){
        Hortifrut hort = lista.stream().filter(x -> x.getCodigo() == cod).findFirst().orElse(null);
        return hort != null; 
    }

    // Método para verificar se o nome de um produto é repetido
    public static boolean hasName(List<Hortifrut> lista, String nome){
        Hortifrut hort = lista.stream().filter(x -> x.getNomeProduto().equalsIgnoreCase(nome)).findFirst().orElse(null);

        return hort != null;
    }

}
