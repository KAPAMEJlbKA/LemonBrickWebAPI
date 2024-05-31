package pro.gravit.simplecabinet.web.service.shop.group.delivery;

import pro.gravit.simplecabinet.web.model.shop.GroupOrder;

import java.time.LocalDateTime;
import java.util.UUID;

public interface GroupDeliveryService {
    void delivery(GroupOrder order);

    void updatePrefix(String prefix, UUID userUUID, LocalDateTime endDate);

    boolean deletePrefix(UUID userUUID);
}
