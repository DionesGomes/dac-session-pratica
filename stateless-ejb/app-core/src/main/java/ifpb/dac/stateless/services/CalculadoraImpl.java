package ifpb.dac.stateless.services;

import ifpb.dac.stateless.IFCalculadora;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * @author Ricardo Job
 */
@Stateless
@Remote(IFCalculadora.class)
public class CalculadoraImpl implements IFCalculadora{

    @Override
    public int somar(int a, int b) {
        return a+b;
    }

}
