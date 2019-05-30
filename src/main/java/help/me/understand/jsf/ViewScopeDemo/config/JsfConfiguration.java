package help.me.understand.jsf.ViewScopeDemo.config;

import org.omnifaces.filter.FacesExceptionFilter;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class JsfConfiguration {
    private final String notFoundErrorPage = "/404.xhtml";
    private final String internalServerErrorPage = "/error.xhtml";

    //FilterRegisBean with FacesExceptionFilter
    @Bean
    public FilterRegistrationBean filterRegisBean() {
        final FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        filterRegBean.setFilter(new FacesExceptionFilter());
        filterRegBean.addUrlPatterns("/*");
        filterRegBean.setEnabled(Boolean.TRUE);
        return filterRegBean;
    }

    //ErrorPageRegistration, mapping method
    @Bean
    public ErrorPageRegistrar errorPageRegistrar() {
        return new ErrorPageRegistrar() {
            @Override
            public void registerErrorPages(ErrorPageRegistry registry) {
                registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, notFoundErrorPage));
                registry.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, internalServerErrorPage));
            }
        };
    }

    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return new ViewScopeBeanFactoryPostProcessor();
    }
}
