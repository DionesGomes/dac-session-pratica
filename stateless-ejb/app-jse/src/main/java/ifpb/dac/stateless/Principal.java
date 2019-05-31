package ifpb.dac.stateless;


import ifpb.dac.domain.Cliente;
import ifpb.dac.domain.ClienteInterface;
import ifpb.dac.locator.ServiceLocator;
import java.util.List;
import java.util.Scanner;

public class Principal extends javax.swing.JFrame {

    public static void main(String args[]) {
       
        String recursocliente = "java:global/stateless-ejb-core/ClientesEmJDBC!ifpb.dac.domain.ClienteInterface";
        String recursoproduto = "java:global/sessionbeans-ejb-core/ProdutosEmJDBC!ifpb.dac.domain.ProdutoInterface";
        String recursovenda = "java:global/sessionbeans-ejb-core/VendaEmJDBC!ifpb.dac.domain.VendaInterface";
        String recursocarrinho = "java:global/sessionbeans-ejb-core/CarrinhoDeCompras!ifpb.dac.domain.Carrinho";
        
        Scanner ler = new Scanner(System.in);
        
        boolean menu = true;
        
        while(menu == true){
            
            System.out.println("Para ir para as opções de cliente digite "+ Opcoes.CLIENTES.getId() +": \n" + 
                                "Para ir para as opções de venda digite "+ Opcoes.PRODUTOS.getId() +": \n"
                                +"Para sair digite " + Opcoes.SAIR.getId() + ":");
            
            Opcoes quant = Opcoes.fromId(ler.nextInt());
            
            switch(quant){
                case CLIENTES:
                    boolean menuc = true;
                    while(menuc == true){
                        ler.nextLine();
                        System.out.println("Para cadastrar os clientes digite "+ Opcoes.CADCLIENTES.getId() +" : \n" + 
                                            "Para listar e buscar os clientes digite "+ Opcoes.LISTBUSCCLIETES.getId() +": \n"
                                            + "Para sair digite " + Opcoes.SAIR.getId() + ":");
                        Opcoes quantc = Opcoes.fromId(ler.nextInt());
                        switch(quantc){
                            case CADCLIENTES:
                                ler.nextLine();
                                cadastrarClientes(recursocliente);
                                break;
                            case LISTBUSCCLIETES:
                                boolean menuclb = true;
                                while(menuclb == true){
                                    ler.nextLine();
                                    System.out.println("Para buscar um cliente digite "+ Opcoes.BUSCARCLIENTE.getId() +": \n" + 
                                            "Para listar os clientes digite "+ Opcoes.LISTARCLIENTE.getId() +": \n" +
                                            "Para sair digite " + Opcoes.SAIR.getId() + ":");
                                    Opcoes quantclb = Opcoes.fromId(ler.nextInt());
                                    switch(quantclb){
                                        case BUSCARCLIENTE:
                                            ler.nextLine();
                                            buscarCliente(recursocliente);
                                            break;
                                        case LISTARCLIENTE:
                                            listarClientes(recursocliente);
                                            break;
                                        case SAIR:
                                            menuclb = false;
                                            break;
                                        default:
                                            System.out.println("Numero errado!!!");
                                    }
                                }
                                break;
                            case SAIR:
                                menuc = false;
                                break;
                            default:
                                System.out.println("Numero errado!!!");
                        }
                    }
                    break;
                case PRODUTOS:
                     break;
                case SAIR:
                    menu = false;
                    break;
                default:
                    System.out.println("Numero errado!!!");
            }
        }
        
        ler.close();
        
    }
    
    public static void listarClientes(String recurso){
        
        ClienteInterface clientedao = new ServiceLocator().lookup(recurso, ClienteInterface.class);
        List<Cliente> clientes = clientedao.todosOsClientes();
        
        for(Cliente cliente : clientes){
            System.out.println(String.format("%20s \t| %20s \t|", "Nome", "CPF"));
            System.out.println(String.format("%20s \t| %20s \t|", cliente.getNome(), cliente.getCpf()));
        }
    }
    
    public static void cadastrarClientes(String recurso){
        
        ClienteInterface clientedao = new ServiceLocator().lookup(recurso, ClienteInterface.class);
        clientedao.adicionar(cadCliente());
        
    }
    
    public static Cliente cadCliente(){
        
        Cliente cliente = new Cliente();
        Scanner ler = new Scanner(System.in);
        System.out.println("Nome: ");
        String nome = ler.nextLine();
        ler.nextLine();
        System.out.println("CPF: ");
        String cpf = ler.nextLine();
        ler.nextLine();
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        ler.nextLine();
        return cliente;
    }
    
    public static void buscarCliente(String recurso){
        
        Scanner ler = new Scanner(System.in);
        System.out.println("Insira o cpf para buscar e: ");
        String cpf = ler.nextLine();
        ClienteInterface clientedao = new ServiceLocator().lookup(recurso, ClienteInterface.class);
        Cliente cliente = clientedao.buscarCpf(cpf);
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        boolean menu = true;
        while(menu == true){
            ler.nextLine();
            System.out.println("Para editar cliente digite "+ Opcoes.EDITARCLIENTE.getId() +": \n" + 
                                "Para excluir cliente digite "+ Opcoes.EXCLUIRCLIENTE.getId() +": \n"
                              + "Para sair digite " + Opcoes.SAIR.getId() + ":");
            Opcoes quant = Opcoes.fromId(ler.nextInt());
            switch(quant){
                case EDITARCLIENTE:
                    Cliente novocliente = cadCliente();
                    novocliente.setId(cliente.getId());
                    clientedao.atualizar(novocliente);
                    break;
                case EXCLUIRCLIENTE:
                    clientedao.remover(cliente);
                    break;
                case SAIR:
                    menu = false;
                    break;
                default:
                    System.out.println("Numero errado!!!");
            }
        }
    }
  
}


enum Opcoes{
    CLIENTES(1),
    PRODUTOS(2),
    CADCLIENTES(3),
    LISTBUSCCLIETES(4),
    BUSCARCLIENTE(5),
    LISTARCLIENTE(6),
    EDITARCLIENTE(7),
    EXCLUIRCLIENTE(8),
    SAIR(0);
    
    private int id;
    
    private Opcoes(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    public static Opcoes fromId(int id){
        for (Opcoes type : values()){
            if(type.getId() == id){
                return type;
            }
        }
        return null;
    }
    
}
