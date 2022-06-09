package io.getarrays.server.service.implementation;

import io.getarrays.server.model.Server;
import io.getarrays.server.model.ServerRepository;
import io.getarrays.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static io.getarrays.server.enumeration.Status.SERVER_DOWN;
import static io.getarrays.server.enumeration.Status.SERVER_UP;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImplementation implements ServerService {

    @Autowired
    private final ServerRepository serverRepository;

    @Override
    public Server createServer(Server server) {
        log.info("Creating server: {}\nWith name {}", server, server.getHostname());
        server.setImageUrl(setServerImageUrl());
        serverRepository.save(server);
        return server;
    }


    @Override
    public Collection<Server> list(int limit) {
        log.info("Listing servers");
        return serverRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public List<String> prettyList(int limit) {
        log.info("Listing servers pretty");
        List<Server> servers = serverRepository.findAll(PageRequest.of(0, limit)).toList();
        List<String> prettyServers = new ArrayList<String>();
        servers.forEach(server -> {
            server.toString();
            JSONObject serverJSON = new JSONObject(server);
            prettyServers.add(serverJSON.toString(4));
        });
        return prettyServers;
    }

    @Override
    public Server getServer(Long id) {
        log.info("Getting server with id: {}", id);
        return serverRepository.findById(id).orElse(null);
    }

    @Override
    public Server updateServer(Server server) {
        log.info("Updating server: {}\nWith name {}", server, server.getHostname());
        return serverRepository.save(server);
    }

    @Override
    public Boolean deleteServer(Long id) {
        log.info("Deleting server with id: {}", id);
        serverRepository.deleteById(id);
        return true;
    }

    @Override
    public Server pingServer(String ipAddress) throws IOException {
        log.info("Pinging server with ip address: {}", ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(server.getIpAddress());
        server.setStatus(address.isReachable(3000) ? SERVER_UP: SERVER_DOWN);
        serverRepository.save(server);
        return server;
    }

    // Utility method to set the image url
    public String setServerImageUrl() {
        String[] imageNames = {"server1.png", "server2.png", "server3.png", "server4.png", "server5.png"};

        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("server/images/" + imageNames[new Random().nextInt(imageNames.length)])
                .toUriString();
    }
}
