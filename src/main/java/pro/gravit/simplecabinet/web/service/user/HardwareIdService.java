package pro.gravit.simplecabinet.web.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pro.gravit.simplecabinet.web.controller.admin.AdminHardwareController;
import pro.gravit.simplecabinet.web.model.user.HardwareId;
import pro.gravit.simplecabinet.web.repository.user.HardwareIdRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class HardwareIdService {
    @Autowired
    private HardwareIdRepository repository;

    public <S extends HardwareId> S save(S entity) {
        return repository.save(entity);
    }

    public Optional<HardwareId> findById(Long aLong) {
        return repository.findById(aLong);
    }

    public HardwareId getById(Long aLong) {
        return repository.getById(aLong);
    }

    public Optional<HardwareId> findByHardware(AdminHardwareController.HardwareSearchRequest id) {
        return repository.findByHwDiskId(id.hwDiskId());
    }

    public Optional<HardwareId> findByPublicKey(byte[] publicKey) {
        return repository.findByPublicKey(publicKey);
    }

    public Page<HardwareId> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<HardwareId> findByUser(long userId) {
        return repository.findByUser(userId);
    }

    @Transactional
    public void banByUser(long userId) {
        var list = findByUser(userId);
        for (var e : list) {
            e.setBanned(true);
        }
        repository.saveAll(list);
    }

    @Transactional
    public void unbanByUser(long userId) {
        var list = findByUser(userId);
        for (var e : list) {
            e.setBanned(false);
        }
        repository.saveAll(list);
    }
}
