package com.nayasha.produk.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nayasha.produk.entity.JenisProduk;
@Repository
public interface JenisProdukRepository extends JpaRepository<JenisProduk, Long> {
}