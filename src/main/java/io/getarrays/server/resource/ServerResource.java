package io.getarrays.server.resource;

import io.getarrays.server.enumeration.Status;
import io.getarrays.server.model.Response;
import io.getarrays.server.model.Server;
import io.getarrays.server.service.implementation.ServerServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/server")
public class ServerResource {

    @Autowired
    private ServerServiceImplementation serverService;

    @GetMapping("/list")
    public ResponseEntity<Response> getServers(){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .successMessage("Successfully fetched servers")
                        .data(Map.of("servers", serverService.list(10)))
                        .build()
        );
    }

    @GetMapping("/pretty")
    public ResponseEntity<Response> getServersPretty(){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .successMessage("Successfully fetched servers")
                        .data(Map.of("servers", serverService.prettyList(10)))
                        .build()
        );
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable String ipAddress) throws IOException {
        Server server = serverService.pingServer(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .successMessage(server.getStatus() == Status.SERVER_UP ? "Server is up" : "Server is down")
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .data(Map.of("server", server))
                        .build()
        );
    }

    @PostMapping("/create")
    public ResponseEntity<Response> createServer(@RequestBody @Valid Server server) {

        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .successMessage(
                                server.getStatus() == Status.SERVER_UP ?
                                        "Successfully created server" : "Failed to create server"
                        )
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .data(Map.of("server", serverService.createServer(server)))
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .successMessage("Successfully fetched server")
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .data(Map.of("server", serverService.getServer(id)))
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .successMessage("Successfully deleted server")
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .data(Map.of("server", serverService.deleteServer(id)))
                        .build()
        );
    }

    @GetMapping(path = "/image/{fileName}", produces = "image/png")
    public byte[] getServerImage(@PathVariable String fileName) throws IOException {
        // Return image bytes
        return Files.readAllBytes(
                Paths.get(System.getProperty("user.home") + "/Java/images/" + fileName)
        );

    }
}
