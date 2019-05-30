package ifpb.dac.domain;

import java.util.List;

public interface VendaInterface {
    
    void adicionar(Venda venda);
    
    List<Venda> todasvendas();
    
}
