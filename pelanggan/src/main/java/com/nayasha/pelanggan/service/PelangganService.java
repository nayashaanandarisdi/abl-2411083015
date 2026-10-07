package com.nayasha.pelanggan.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nayasha.pelanggan.entity.Pelanggan;
import com.nayasha.pelanggan.repository.PelangganRepository;

@Service
public class PelangganService {

    @Autowired
    private PelangganRepository pelangganRepository;

    // Method untuk menampilkan semua data pelanggan
    public List<Pelanggan> getAllPelanggan() {
        return pelangganRepository.findAll();
    }

    // Method untuk menampilkan data pelanggan berdasarkan ID
    public Pelanggan getPelangganById(Long id) {
        return pelangganRepository.findById(id).orElse(null);
    }

    // Method untuk menyimpan data pelanggan baru
    public Pelanggan savePelanggan(Pelanggan pelanggan) {
        return pelangganRepository.save(pelanggan);
    }

    // Method untuk memperbarui data pelanggan
    public Pelanggan updatePelanggan(Long id, Pelanggan pelanggan) {
        Pelanggan existing = pelangganRepository.findById(id).orElse(null);

        // Memeriksa apakah data dengan id tersebut ada sebelum diperbarui
        if (existing != null) {
            existing.setNama(pelanggan.getNama());
            existing.setEmail(pelanggan.getEmail());
            existing.setNoTelepon(pelanggan.getNoTelepon());
            existing.setAlamat(pelanggan.getAlamat());

            // Menyimpan perubahan ke database
            return pelangganRepository.save(existing);
        }
        return null;
    }

    // Method untuk menghapus data pelanggan berdasarkan ID
    public void deletePelanggan(Long id) {
        pelangganRepository.deleteById(id);
    }
}