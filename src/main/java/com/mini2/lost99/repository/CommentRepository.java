package com.mini2.lost99.repository;

import com.mini2.lost99.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByContentsId(Long id);
}