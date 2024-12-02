package com.shop.shop.polylab.repoziory;

import com.shop.shop.polylab.models.post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepozitory extends CrudRepository<post, Long> {
}
