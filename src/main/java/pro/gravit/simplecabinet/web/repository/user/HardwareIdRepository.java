package pro.gravit.simplecabinet.web.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.gravit.simplecabinet.web.model.user.HardwareId;

import java.util.List;
import java.util.Optional;

public interface HardwareIdRepository extends JpaRepository<HardwareId, Long> {
    Optional<HardwareId> findByPublicKey(byte[] publicKey);

    Optional<HardwareId> findByHwDiskId(String hwDiskId);

    @Query("select hwid from HardwareId hwid, UserSession session, User u where u.id = :userId and session.user = u and session.hardwareId = hwid")
    List<HardwareId> findByUser(long userId);
}
