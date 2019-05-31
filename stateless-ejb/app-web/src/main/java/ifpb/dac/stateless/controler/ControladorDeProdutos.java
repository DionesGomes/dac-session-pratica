package ifpb.dac.stateless.controler;


import ifpb.dac.domain.Produto;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@RequestScoped
@Named
public class ControladorDeProdutos implements Serializable {

    private Produto produto;

    

}
