package io.getarrays.server.service;

import io.getarrays.server.model.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerService {

    Server createServer(Server server);
    Collection<Server> list(int limit);
    Server getServer(Long serverId);
    Server updateServer(Server server);
    Boolean deleteServer(Long serverId);
    Server pingServer(String ipAddress) throws IOException;
}
