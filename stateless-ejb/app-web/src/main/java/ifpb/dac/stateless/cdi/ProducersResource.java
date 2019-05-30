package ifpb.dac.stateless.cdi;

import ifpb.dac.stateless.IFCalculadora;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * @author Ricardo Job
 */
@ApplicationScoped
public class ProducersResource {
    
    @Produces 
    @Resource(lookup = "java:global/stateless-ejb-core/CalculadoraImpl")
    private IFCalculadora calculadora;

}
