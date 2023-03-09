package org.lilyhe.admin;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * @author Lily H.
 * this was a little over my head, from what I understand...mvcConfig allows you to
 * manage and update static assets separately from application code.
 *
 * This code is basically telling the Spring web application to look for static files (like images, CSS files, or
 * JavaScript files) in a specific directory on the computer. It does this by creating a custom "resource handler"
 * that tells the application where to find these files.
 *
 * Normally, Spring looks for static files in a specific folder inside the application's code. But with this code,
 * you can tell Spring to look for static files in a different folder on the computer instead.
 */



@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory("../product-images", registry);
    }

    private void exposeDirectory(String pathPattern, ResourceHandlerRegistry registry) {
        Path path = Paths.get(pathPattern);
        String absolutePath = path.toFile().getAbsolutePath();

        String logicalPath = pathPattern.replace("../", "") + "/**";

        registry.addResourceHandler(logicalPath)
                .addResourceLocations("file:/" + absolutePath + "/");
    }
}
