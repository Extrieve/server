package io.getarrays.server.model;

import io.getarrays.server.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long serverId;
    @Column(unique = true)
    @NotEmpty(message = "IP address cannot be empty")
    private String ipAddress;
    private String hostname;
    private String memory;
    private String type;
    private String imageUrl;
    private Status status;
}
