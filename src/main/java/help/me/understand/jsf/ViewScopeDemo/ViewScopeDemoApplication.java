package help.me.understand.jsf.ViewScopeDemo;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.HazelcastSessionRepository;
import org.springframework.session.hazelcast.PrincipalNameExtractor;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

@EnableHazelcastHttpSession
@SpringBootApplication
public class ViewScopeDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ViewScopeDemoApplication.class, args);
	}


	@Bean
	public HazelcastInstance hazelcastInstance(ApplicationContext context) {
		String groupName = "ViewScopeDemo_IMDG";

		//Create basic configuration with no enabled join methods (standalone mode).
		Config hzConfig = new Config();
		hzConfig.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(true);
		hzConfig.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(false);

		hzConfig.setGroupConfig(new GroupConfig(groupName));

		SerializerConfig serializer = new SerializerConfig()
				.setImplementation(new ObjectStreamSerializer())
				.setTypeClass(Object.class);

		hzConfig.getSerializationConfig()
				.addSerializerConfig(serializer);

		// Turn on session clustering via Spring-Session
		MapAttributeConfig attributeConfig = new MapAttributeConfig()
				.setName(HazelcastSessionRepository.PRINCIPAL_NAME_ATTRIBUTE)
				.setExtractor(PrincipalNameExtractor.class.getName());

		hzConfig.getMapConfig(HazelcastSessionRepository.DEFAULT_SESSION_MAP_NAME)
				.addMapAttributeConfig(attributeConfig)
				.addMapIndexConfig(new MapIndexConfig(
						HazelcastSessionRepository.PRINCIPAL_NAME_ATTRIBUTE, false));

		return Hazelcast.newHazelcastInstance(hzConfig);
	}

}
