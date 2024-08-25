package com.winnie.ecserviceorder.repository;

import com.winnie.ecserviceorder.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findByName(String name);

    @Query(value = "SELECT * FROM orders WHERE id = ?1", nativeQuery = true)
    Order findByIdNative(Integer id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM orders WHERE name LIKE '%Temp%'", nativeQuery = true)
    void removeTempOrder();

    @Query(value = "SELECT * FROM orders WHERE LIKE CONCAT('%', ?1, '%') ", nativeQuery = true)
    Order findByNameWildCard(String name);

}
