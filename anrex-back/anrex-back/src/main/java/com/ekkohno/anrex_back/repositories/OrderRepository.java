package com.ekkohno.anrex_back.repositories;

import com.ekkohno.anrex_back.auth.entities.User;
import com.ekkohno.anrex_back.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findByUser(User user);
}