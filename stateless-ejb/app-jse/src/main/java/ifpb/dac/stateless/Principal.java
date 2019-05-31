package ifpb.dac.stateless;


import ifpb.dac.domain.Cliente;
import ifpb.dac.domain.ClienteInterface;
import ifpb.dac.locator.ServiceLocator;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Principal extends javax.swing.JFrame {

    public static void main(String args[]) {
       
        String recursocliente = "java:global/sessionbeans-ejb-core/ClientesEmJDBC";
        
        Scanner ler = new Scanner(System.in);
        
        boolean menu = true;
        
        while(menu == true){
            
            System.out.println("Para ir para as opções de cliente digite 1: \n" + 
                                "Para ir para as opções de venda digite 2: \n"
                                +"Para sair digite " + Opcoes.SAIR.getId() + ":");
            
            Opcoes quant = Opcoes.fromId(ler.nextInt());
            
            switch(quant){
                case CLIENTES:
                    boolean menuc = true;
                    while(menuc == true){
                        ler.nextLine();
                        System.out.println("Para cadastrar os clientes digite 1: \n" + 
                                            "Para listar e buscar os clientes digite 2: \n"
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
                                    System.out.println("Para buscar os clientes digite 1: \n" + 
                                            "Para sair digite " + Opcoes.SAIR.getId() + ":");
                                    Opcoes quantclb = Opcoes.fromId(ler.nextInt());
                                    switch(quantclb){
                                        case BUSCARCLIENTE:
                                            ler.nextLine();
                                            buscarCliente(recursocliente);
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
        System.out.println("Insira o cpf para buscar: ");
        String cpf = ler.nextLine();
        ClienteInterface clientedao = new ServiceLocator().lookup(recurso, ClienteInterface.class);
        Cliente cliente = clientedao.buscarCpf(cpf);
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        boolean menu = true;
        while(menu == true){
            ler.nextLine();
            System.out.println("Para editar cliente digite 1: \n" + 
                                "Para excluir cliente digite 2: \n"
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
    CADCLIENTES(1),
    LISTBUSCCLIETES(2),
    BUSCARCLIENTE(1),
    EDITARCLIENTE(1),
    EXCLUIRCLIENTE(2),
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
