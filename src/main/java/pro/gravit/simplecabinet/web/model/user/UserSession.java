package pro.gravit.simplecabinet.web.model.user;

import io.hypersistence.utils.hibernate.type.basic.Inet;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "UserSession")
@Table(name = "sessions")
public class UserSession {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sessions_generator")
    @SequenceGenerator(name = "sessions_generator", sequenceName = "sessions_seq", allocationSize = 1)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "refresh_token")
    private String refreshToken;
    private Inet ip;
    private String client;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hwid_id")
    private HardwareId hardwareId;
    @Column(name = "server_id")
    private String serverId;
    private boolean deleted;
    private LocalDateTime createdAt;

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public HardwareId getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(HardwareId hardwareId) {
        this.hardwareId = hardwareId;
    }

    public Inet getIp() {
        return ip;
    }

    public void setIp(Inet ip) {
        this.ip = ip;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
