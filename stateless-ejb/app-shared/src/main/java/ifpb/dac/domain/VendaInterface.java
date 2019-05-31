package ifpb.dac.domain;

import java.util.List;

public interface VendaInterface {

    public void adicionar(Venda venda);
    public void remover(Venda venda);
    public List<Venda> listar();
    public Venda buscar(int id);
}
