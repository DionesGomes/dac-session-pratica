package ifpb.dac.domain;

import java.util.List;

public interface ClienteInterface {
    
    void adicionar(Cliente cliente);
    
    void remover(Cliente cliente);
    
    List<Cliente> todososclientes();
    
    Cliente buscarcpf(String cpf);
    
}
