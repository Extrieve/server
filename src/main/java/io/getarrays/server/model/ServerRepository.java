package io.getarrays.server.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {

    public Server findByIpAddress(String ipAddress);
    public Server findByHostname(String hostname);
    public Server findByMemory(String memory);
}
