package tw.taichung.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tw.taichung.jpa.Sample;

@Stateless
@LocalBean
public class SampleService {

	@PersistenceContext(unitName="sample")
	private EntityManager em;
  
    public SampleService() {
        
    }

    
    public void add(String name){
    	Sample s=new Sample();
    	s.setName(name);
    	em.persist(s);
    }
    
    public Sample find(int id){    	
    	return em.find(Sample.class, id);
    }
}
