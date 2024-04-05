package hello.itemservice.repository.jpa;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@Transactional //jpa 모든 데이터변경은 transaction에서 이뤄진다
public class JpaItemRepository implements ItemRepository {

    private final EntityManager em;

    public JpaItemRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Item save(Item item) {
        em.persist(item);
        return item;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateDto) {
        Item findItem = em.find(Item.class, itemId);
        findItem.setItemName(updateDto.getItemName());
        findItem.setPrice(updateDto.getPrice());
        findItem.setQuantity(updateDto.getQuantity());
    }

    /**
     * jpa는 트랜잭션이 커밋되는 시점에 변경된 엔티티 객체가 있는지 확인한다
     * 특정 엔티티 객체가 변경된 경우엔 update sql을 실행한다
     */

    @Override
    public Optional<Item> findById(Long id) {
        Item item = em.find(Item.class,id);
        return Optional.ofNullable(item);
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String jpql = "select i from Item i";
        List<Item> result = em.createQuery(jpql, Item.class)
                .getResultList();
        return result;
    }
}
