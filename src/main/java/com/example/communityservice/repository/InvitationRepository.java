package com.example.communityservice.repository;

import com.example.communityservice.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    Optional<Invitation> findByReceiver_IdAndInvitation_Link(Long invitedId, String invitationLink);
}
