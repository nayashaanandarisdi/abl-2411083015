package com.nayasha.pelanggan.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nayasha.pelanggan.entity.Pelanggan;
import com.nayasha.pelanggan.service.PelangganService;

@RestController
@RequestMapping("/api/pelanggan")
public class PelangganController {

    @Autowired
    private PelangganService pelangganService;

    // Menampilkan semua data pelanggan (GET)
    @GetMapping
    public List<Pelanggan> getAllPelanggan() {
        return pelangganService.getAllPelanggan();
    }

    // Menampilkan data pelanggan berdasarkan ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Pelanggan> getPelangganById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(pelangganService.getPelangganById(id));
    }

    // Menambahkan data pelanggan baru (POST)
    @PostMapping
    public ResponseEntity<Pelanggan> createPelanggan(@RequestBody Pelanggan pelanggan) {
        return ResponseEntity.ok(pelangganService.savePelanggan(pelanggan));
    }

    // Memperbarui data pelanggan (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Pelanggan> updatePelanggan(@PathVariable("id") Long id, @RequestBody Pelanggan pelanggan) {
        return ResponseEntity.ok(pelangganService.updatePelanggan(id, pelanggan));
    }

    // Menghapus data pelanggan (DELETE)
    @DeleteMapping("/{id}")
    public void deletePelanggan(@PathVariable("id") Long id) {
        pelangganService.deletePelanggan(id);
    }
}