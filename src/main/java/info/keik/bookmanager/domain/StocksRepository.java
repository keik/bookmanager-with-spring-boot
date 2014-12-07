package info.keik.bookmanager.domain;

import info.keik.bookmanager.model.Stock;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StocksRepository extends JpaRepository<Stock, Integer> {

    @Transactional
    @Query(value = "SELECT s FROM Stock s WHERE s.item.name LIKE %:name%")
    List<Stock> findByNameContainsInItem(@Param("name") String name);

    @Transactional
    List<Stock> deleteByIdIn(List<Integer> ids);

}
