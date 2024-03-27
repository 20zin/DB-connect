package hello.itemservice.repository.mybatis;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper //xml에서 인식해줌
public interface ItemMapper {
    //구현체가 없는데 어떡해 동작하지? 인터페이스는 빈에 등록되지 않는데
    //Mybatis 스프링 연동 모듈
    void save(Item item);

    void update(@Param("id") Long id,
                @Param("updateParam") ItemUpdateDto updateParam);

    List<Item> findAll(ItemSearchCond itemSearch);

    Optional<Item> findById(Long id);
}
