package xyz.kajih.xplorer.web.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.kajih.xplorer.web.User;

/* http://localhost:8082/swagger-ui/index.html  */
/* https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations */

@Slf4j
@OpenAPIDefinition(
        info = @Info(
                title = "Xplorer API",
                version = "0.7",
                description = "Xplorer Experimental",
                license = @License(name = "MIT", url = "http://foo.bar")
        )
)
@RestController
@RequestMapping("/api")
public class MyController {

    @Operation(
            summary = "Get user by ID",
            description = "Retrieves user details based on the provided ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful Response",
                            content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "404", description = "User Not Found")
            }
    )
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return new User(id, "John Doe");
    }
}
