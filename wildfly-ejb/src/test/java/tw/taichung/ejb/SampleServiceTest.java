package tw.taichung.ejb;

import static org.junit.Assert.*;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import tw.taichung.jpa.Sample;

@RunWith(Arquillian.class)
public class SampleServiceTest {

	@Deployment
	public static Archive<?>  createDeployment() {
		return ShrinkWrap.create(WebArchive.class)	
				.addPackage("tw.taichung.ejb")
				.addPackage("tw.taichung.jpa")
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource("test-ds.xml", "test-ds.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@EJB
	SampleService service;
	
	@Test
	@InSequence(1)
	public void testAdd() {
		service.add("TEST");
		assertTrue(true);
	}

	@Test
	@InSequence(2)
	public void testFind() {
		Sample s=service.find(1);		
		assertNotNull(s);
		
	}

}
