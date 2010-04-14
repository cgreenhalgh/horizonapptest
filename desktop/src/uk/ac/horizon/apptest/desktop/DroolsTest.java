/**
 * 
 */
package uk.ac.horizon.apptest.desktop;

import java.io.ObjectInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import uk.ac.horizon.apptest.model.UserLocation;
import uk.ac.horizon.apptest.model.ContentMapping;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
//import org.drools.builder.KnowledgeBuilder;
//import org.drools.builder.KnowledgeBuilderFactory;
//import org.drools.builder.ResourceType;
import org.drools.common.DroolsObjectInputStream;
import org.drools.definition.KnowledgePackage;
import org.drools.event.rule.DebugAgendaEventListener;
import org.drools.event.rule.DebugWorkingMemoryEventListener;
//import org.drools.io.ResourceFactory;
//import org.drools.logger.KnowledgeRuntimeLogger;
//import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.rule.Package;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * @author cmg
 *
 */
public class DroolsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// force use of JANINO
//		System.setProperty("drools.dialect.java.compiler", "JANINO");

		Collection<KnowledgePackage> pkgs = new ArrayList<KnowledgePackage>();
		try {
			// read a rule set compiled by the rule compiler
			ObjectInputStream ois = new DroolsObjectInputStream(DroolsTest.class.getResourceAsStream("rules.ser"));
			
			Object o = ois.readObject();
			System.out.println("Found: ("+o.getClass()+") "+o.toString());
			
			// this doesn't match the documentation but is what I am getting for drools 5.0 compiler output
			Package p = (Package)o;
			pkgs.add(new org.drools.definitions.impl.KnowledgePackageImp(p));
			
		} 
		catch (Exception e) {
			System.out.println("Error reading rules: "+e);
			e.printStackTrace();
			throw new RuntimeException("Unable to read rules");
		}
/*		final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
		.newKnowledgeBuilder();
		
		// this will parse and compile in one step
		kbuilder.add(ResourceFactory.newClassPathResource("HelloWorld.drl",
				DroolsTest.class), ResourceType.DRL);

		// Check the builder for errors
		if (kbuilder.hasErrors()) {
			System.out.println(kbuilder.getErrors().toString());
			throw new RuntimeException("Unable to compile \"HelloWorld.drl\".");
		}

		// get the compiled packages (which are serializable)
		final Collection<KnowledgePackage> pkgs = kbuilder
		.getKnowledgePackages();
*/
		// add the packages to a knowledgebase (deploy the knowledge packages).
		final KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(pkgs);

		final StatefulKnowledgeSession ksession = kbase
		.newStatefulKnowledgeSession();
		//ksession.setGlobal("list", new ArrayList<Object>());

		ksession.addEventListener(new DebugAgendaEventListener());
		ksession.addEventListener(new DebugWorkingMemoryEventListener());

		// setup the audit logging
//		KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "log/helloworld");

/*		
		final Message message = new Message();
		message.setMessage("Hello World");
		message.setStatus(Message.HELLO);
		ksession.insert(message);
*/
		ContentMapping cm = new ContentMapping("C1", "R1");
		ksession.insert(cm);
		UserLocation ul = new UserLocation("U1","R1");
		ksession.insert(ul);
		
		ksession.fireAllRules();

//		logger.close();

		ksession.dispose();

	}
	public static class Message {
		public static final int HELLO = 0;
		public static final int GOODBYE = 1;

		private String message;

		private int status;

		public Message() {

		}

		public String getMessage() {
			return this.message;
		}

		public void setMessage(final String message) {
			this.message = message;
		}

		public int getStatus() {
			return this.status;
		}

		public void setStatus(final int status) {
			this.status = status;
		}

		public static Message doSomething(Message message) {
			return message;
		}

		public boolean isSomething(String msg, List<Object> list) {
			list.add(this);
			return this.message.equals(msg);
		}
	}

}
