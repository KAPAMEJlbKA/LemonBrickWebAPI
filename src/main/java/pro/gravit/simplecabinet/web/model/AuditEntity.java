package pro.gravit.simplecabinet.web.model;

import jakarta.persistence.*;
import pro.gravit.simplecabinet.web.model.user.User;

import java.time.LocalDateTime;

@Entity(name = "Audit")
@Table(name = "audit_log")
public class AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audit_log_generator")
    @SequenceGenerator(name = "audit_log_generator", sequenceName = "audit_log_seq", allocationSize = 1)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_user_id")
    private User target;
    private AuditType type;
    private LocalDateTime time;
    private String arg1;
    private String arg2;
    private String ip;

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    public AuditType getType() {
        return type;
    }

    public void setType(AuditType type) {
        this.type = type;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public enum AuditType {
        UNKNOWN, CHANGE_PASSWORD, CHANGE_USERNAME, PASSWORD_RESET, CREATE_PRODUCT, DISABLE_2FA
    }

}
