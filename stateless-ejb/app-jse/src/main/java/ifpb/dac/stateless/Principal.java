package ifpb.dac.stateless;


import ifpb.dac.domain.Cliente;
import ifpb.dac.locator.ServiceLocator;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Principal extends javax.swing.JFrame {

    public static void main(String args[]) {
       
        String nome = "java:global/sessionbeans-ejb-core/ClientesEmJDBC";
        
        Cliente cliente = new ServiceLocator().lookup(nome, Cliente.class);
        
        Scanner ler = new Scanner(System.in);
        
        boolean menu = true;
        
        while(menu == true){
            
            System.out.println("Para ir para as opções de cliente digite 1: \n" + 
                                "Para ir para as opções de venda digite 2: \n"
                                + "Para sair digite 0:");
            
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
                                
                                break;
                            case LISTBUSCCLIETES:
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
  
}


enum Opcoes{
    CLIENTES(1),
    PRODUTOS(2),
    CADCLIENTES(1),
    LISTBUSCCLIETES(2),
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
