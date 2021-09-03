package es.golemdr.wittytool.config;
import java.util.Locale;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.springframework.web.util.UrlPathHelper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.golemdr.wittytool.controller.constantes.UrlConstants;
import es.golemdr.wittytool.ext.exceptions.resolver.CustomExceptionResolver;


import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.MapperFeature.SORT_PROPERTIES_ALPHABETICALLY;



// DispatcherServlet context: defines Spring MVC infrastructure
// and web application components

@Configuration
@ComponentScan(basePackages = "es.golemdr.wittytool")
@EnableWebMvc
@EnableScheduling
@EnableTransactionManagement
@EnableJpaRepositories("es.golemdr.wittytool.repository")
@PropertySource("classpath:application.properties")
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Autowired
	private Environment env;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//registry.addViewController("/").setViewName("Login");
		// Para redirigir a una accion
		registry.addRedirectViewController("/", UrlConstants.URL_ENTRADA_APLICACION);
	}



	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		UrlPathHelper pathHelper = new UrlPathHelper();
		pathHelper.setRemoveSemicolonContent(false); // For @MatrixVariable's
		configurer.setUrlPathHelper(pathHelper);
	}

	
	public MultipartResolver multipartResolver() {
	
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(8388608); // 8MB
		
		return multipartResolver;
		
	}
	
	//----------------------------- Añado todo lo que sigue
	

    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions("classpath:tiles.xml");
        tilesConfigurer.setCheckRefresh(true);
        
        return tilesConfigurer;
    }
    
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		
		BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
		beanNameViewResolver.setOrder(0);
		
        TilesViewResolver tilesViewResolver = new TilesViewResolver();
        tilesViewResolver.setOrder(1);
        
        registry.viewResolver(beanNameViewResolver);
        registry.viewResolver(tilesViewResolver);
        
	}
	
	// Si definimos un locale por defecto el usuario tendrá que cambiar de idioma en caso de que esté disponible.
	// Si no lo definimos y el idioma está disponible, el usuario entra directamente en su idioma
	@Bean
	public LocaleResolver localeResolver() {
	    SessionLocaleResolver slr = new SessionLocaleResolver();
	    slr.setDefaultLocale(new Locale("es"));
	    return slr;
	}
	
    @Bean  
    public MessageSource messageSource() {  
    	ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    	messageSource.setBasename("classpath:es/golemdr/wittytool/messages/ApplicationResources");
    	// Para ver el código del mensaje en lugar de que salte una excepción de tipo "NoSuchMessageException"
    	// PRO: Nunca va a fallar
    	// CONTRA: Se pueden colar mensaje no internacionalizados en Producción 
        messageSource.setUseCodeAsDefaultMessage(false);
    	// Si no se encuentra un locale, se utiliza el del sistema por defecto
        // Si definimos un LocaleResolver en la configuración, el fichero por defecto será el de ese locale.
        // Si no hay LocaleResolver definido va al fichero del locale correspondiente, o si no al ApplicationResources (es una opción para no obligar al usuario
        // a cambiar de locale y poder navegar en el que tenga definido en su navegador
        messageSource.setFallbackToSystemLocale(false);
        // messageSource.setDefaultEncoding("UTF-8");  -- Por defecto es ISO-8859-1
        messageSource.setCacheSeconds(0);
        return messageSource;  
    }
    
   
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
      
      SimpleMappingExceptionResolver exceptionResolver = new CustomExceptionResolver();

      Properties mappings = new Properties();
      mappings.setProperty("Exception", "ErrorGenerico");

      exceptionResolver.setExceptionMappings(mappings);  // None by default

      return exceptionResolver;
    }
    
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);

        return mapper;
    }
    
    // Configuracion para acceso a BBDD
    @Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean em 
	        = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(dataSource());
	      em.setPackagesToScan(new String[] { "es.golemdr.wittytool.domain" });
	 
	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      em.setJpaProperties(additionalProperties());
	 
	      return em;
	}
    
    @Bean    
	public DataSource dataSource(){
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    
	    String driver = env.getProperty("datasource.driver");
	    
	    if(driver != null) {
	    	dataSource.setDriverClassName(driver);	
	    }

	    dataSource.setUrl(env.getProperty("datasource.url"));
	    dataSource.setUsername(env.getProperty("datasource.username"));
	    dataSource.setPassword(env.getProperty("datasource.password"));
	    return dataSource;
	}
    
	@Bean
	public PlatformTransactionManager transactionManager() {
	    JpaTransactionManager transactionManager = new JpaTransactionManager();
	    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
	 
	    return transactionManager;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	    return new PersistenceExceptionTranslationPostProcessor();
	}
	 
	Properties additionalProperties() {
	    Properties properties = new Properties();
	    properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
	    properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
	    properties.setProperty("hibernate.enable_lazy_load_no_trans", env.getProperty("hibernate.enable_lazy_load_no_trans"));
	    // Estas clases solo están con Spring Boot - Se podrían implementar en la app (copiandolas)
//	    properties.setProperty("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
//      properties.setProperty("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
	       
	    return properties;
	}    
    
    

}
