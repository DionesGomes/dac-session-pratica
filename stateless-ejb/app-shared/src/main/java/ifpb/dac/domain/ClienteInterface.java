package ifpb.dac.domain;

import java.util.List;

public interface ClienteInterface {
    
    void adicionar(Cliente cliente);
    
    void remover(Cliente cliente);
    
    void atualizar(Cliente cliente);
    
    List<Cliente> todosOsClientes();
    
    Cliente buscarCpf(String cpf);
    
}
