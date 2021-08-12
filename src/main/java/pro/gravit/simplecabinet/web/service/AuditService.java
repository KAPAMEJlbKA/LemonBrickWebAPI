package pro.gravit.simplecabinet.web.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pro.gravit.simplecabinet.web.model.AuditEntity;
import pro.gravit.simplecabinet.web.model.User;
import pro.gravit.simplecabinet.web.repository.AuditRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuditService {
    private AuditRepository repository;

    public Optional<AuditEntity> findById(Long aLong) {
        return repository.findById(aLong);
    }

    public List<AuditEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public AuditEntity create(AuditEntity.AuditType type, User user, User target, String arg1, String arg2, String ip) {
        AuditEntity entity = new AuditEntity();
        entity.setTime(LocalDateTime.now());
        entity.setType(type);
        entity.setUser(user);
        entity.setTarget(target);
        entity.setArg1(arg1);
        entity.setArg2(arg2);
        entity.setIp(ip);
        repository.save(entity);
        return entity;
    }

    public AuditEntity create(AuditEntity.AuditType type) {
        return create(type, null, null, null, null, null);
    }

    public AuditEntity create(AuditEntity.AuditType type, User user) {
        return create(type, user, null, null, null, null);
    }

    public AuditEntity create(AuditEntity.AuditType type, User user, String arg1) {
        return create(type, user, null, arg1, null, null);
    }
}
