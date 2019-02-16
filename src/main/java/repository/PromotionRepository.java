package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supermarket.demo.entity.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, String>{

}
