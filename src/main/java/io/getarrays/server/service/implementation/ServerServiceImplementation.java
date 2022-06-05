package io.getarrays.server.service.implementation;

import io.getarrays.server.model.Server;
import io.getarrays.server.model.ServerRepository;
import io.getarrays.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;

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
        return null;
    }

    @Override
    public Server getServer(Long id) {
        return null;
    }

    @Override
    public Server updateServer(Server server) {
        return null;
    }

    @Override
    public Boolean deleteServer(Long id) {
        return null;
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
        return "OK";
    }
}
