package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.token.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
