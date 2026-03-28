package com.wassim.blog.domain.repositories.intefaces;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wassim.blog.domain.entities.Tag;

@Repository
public interface ITagRepository extends JpaRepository<Tag, UUID> {

    @Query("SELECT DISTINCT c FROM Tag c LEFT JOIN FETCH c.posts")
    List<Tag> findAllWithPostCount();

    List<Tag> findByNameIn(Set<String> names);

}
