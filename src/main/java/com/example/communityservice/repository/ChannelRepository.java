package com.example.communityservice.repository;

import com.example.communityservice.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
    List<Channel> findAllByCommunity_Id(Long id);

}
