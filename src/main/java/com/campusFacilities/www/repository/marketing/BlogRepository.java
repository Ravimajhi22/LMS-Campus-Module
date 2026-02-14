package com.campusFacilities.www.repository.marketing;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.marketing.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    // Find by slug
    Optional<Blog> findBySlug(String slug);

    // Find by status
    List<Blog> findByStatus(Blog.BlogStatus status);

    // Find featured blogs
    List<Blog> findByFeaturedTrue();

    // Check if slug exists
    boolean existsBySlug(String slug);
}
