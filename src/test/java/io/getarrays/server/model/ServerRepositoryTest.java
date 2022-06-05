package io.getarrays.server.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class ServerRepositoryTest {

    @Autowired
    private ServerRepository serverRepository;

    @Test
    public void fetchAllServers() throws Exception {
        List<Server> servers = serverRepository.findAll();
        servers.forEach(System.out::println);
    }

    @Test
    public void testFindByIpAddress() {
        Server server = serverRepository.findByIpAddress("192.168.0.1");
        assert server != null;
    }
}