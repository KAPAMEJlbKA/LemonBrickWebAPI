package pro.gravit.simplecabinet.web.service.shop.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.gravit.simplecabinet.web.model.shop.ItemOrder;
import pro.gravit.simplecabinet.web.model.shop.ItemProduct;
import pro.gravit.simplecabinet.web.model.user.User;
import pro.gravit.simplecabinet.web.repository.shop.ItemOrderRepository;
import pro.gravit.simplecabinet.web.repository.shop.ItemProductRepository;
import pro.gravit.simplecabinet.web.service.shop.ShopService;
import pro.gravit.simplecabinet.web.service.shop.item.delivery.ItemDeliveryService;

import java.util.Optional;

@Service
public class ItemProductService {
    @Autowired
    private ItemProductRepository repository;
    @Autowired
    private ItemOrderRepository orderRepository;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ItemDeliveryService deliveryService;

    public <S extends ItemProduct> S save(S entity) {
        return repository.save(entity);
    }

    public Optional<ItemProduct> findById(Long aLong) {
        return repository.findById(aLong);
    }

    public Page<ItemProduct> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<ItemProduct> findAllAvailable(Pageable pageable) {
        return repository.findAllByAvailable(pageable, true);
    }

    @Transactional
    public ItemOrder createItemOrder(ItemProduct product, long quantity, User user) {
        var order = new ItemOrder();
        shopService.fillBasicOrderProperties(order, quantity, user);
        order.setProduct(product);
        shopService.makeTransaction(order, product);
        orderRepository.save(order);
        return order;
    }

    public void delivery(ItemOrder order) {
        shopService.fillProcessDeliveryOrderProperties(order);
        orderRepository.save(order);
        deliveryService.delivery(order);
    }
}
