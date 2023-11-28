package pro.gravit.simplecabinet.web.service.shop.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.gravit.simplecabinet.web.model.shop.ItemDelivery;
import pro.gravit.simplecabinet.web.model.user.User;
import pro.gravit.simplecabinet.web.repository.shop.ItemDeliveryRepository;

import java.util.Optional;

@Service
public class ItemDeliveryService {
    @Autowired
    private ItemDeliveryRepository repository;

    public <S extends ItemDelivery> S save(S entity) {
        return repository.save(entity);
    }

    public Optional<ItemDelivery> findById(Long aLong) {
        return repository.findById(aLong);
    }

    public Page<ItemDelivery> findAllByUser(User user, Pageable pageable) {
        return repository.findAllByUserAndCompleted(user, false, pageable);
    }

    @Transactional
    public void setPart(ItemDelivery itemDelivery, long deliveredPart) {
        var part = itemDelivery.getPart() - deliveredPart;
        itemDelivery.setPart(part);
        if (part <= 0) {
            itemDelivery.setCompleted(true);
        }
        repository.save(itemDelivery);
    }
}
