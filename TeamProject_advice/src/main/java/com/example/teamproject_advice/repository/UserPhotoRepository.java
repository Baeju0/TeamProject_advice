package com.example.teamproject_advice.repository;

import com.example.teamproject_advice.model.entity.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPhotoRepository extends JpaRepository<UserPhoto, Long> {
}
