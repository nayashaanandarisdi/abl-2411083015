package com.nayasha.produk.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nayasha.produk.entity.JenisProduk;
import com.nayasha.produk.entity.Produk;
import com.nayasha.produk.service.JenisProdukService;
import com.nayasha.produk.service.ProdukService;

@RestController
@RequestMapping("/api/produk")
public class ProdukController {

    @Autowired
    private ProdukService produkService;

    @Autowired
    private JenisProdukService jenisProdukService;

    // --- ENDPOINT PRODUK --
    @GetMapping
    public List<Produk> getAllProduk(@RequestParam(value = "idjenis", required = false) Long idjenis) {
        if (idjenis != null) {
            return produkService.getAllBarangByIdJenis(idjenis);
        }
        return produkService.getAllProduk();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produk> getProdukById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(produkService.getProdukById(id));
    }

    @PostMapping
    public ResponseEntity<Produk> createProduk(@RequestBody Produk produk) {
        return ResponseEntity.ok(produkService.saveProduk(produk));
    }

    // --- TAMBAHAN UPDATE & DELETE PRODUK DI SINI ---
    @PutMapping("/{id}")
    public ResponseEntity<Produk> updateProduk(@PathVariable("id") Long id, @RequestBody Produk produk) {
        Produk updatedProduk = produkService.updateProduk(id, produk);
        if (updatedProduk != null) {
            return ResponseEntity.ok(updatedProduk);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduk(@PathVariable("id") Long id) {
        produkService.deleteProduk(id);
        return ResponseEntity.ok().build();
    }


    // --- ENDPOINT JENIS PRODUK ---
    @GetMapping("/jenis")
    public List<JenisProduk> getAllJenisProduk() {
        return jenisProdukService.getAllJenisProduk();
    }

    @GetMapping("/jenis/{id}")
    public ResponseEntity<JenisProduk> getJenisProdukById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(jenisProdukService.getJenisProdukById(id));
    }

    @PostMapping("/jenis")
    public ResponseEntity<JenisProduk> createJenisProduk(@RequestBody JenisProduk jenisProduk) {
        return ResponseEntity.ok(jenisProdukService.saveJenisProduk(jenisProduk));
    }

    // --- TAMBAHAN UPDATE & DELETE JENIS PRODUK DI SINI ---
    @PutMapping("/jenis/{id}")
    public ResponseEntity<JenisProduk> updateJenisProduk(@PathVariable("id") Long id, @RequestBody JenisProduk jenisProduk) {
        JenisProduk updatedJenisProduk = jenisProdukService.updateJenisProduk(id, jenisProduk);
        if (updatedJenisProduk != null) {
            return ResponseEntity.ok(updatedJenisProduk);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/jenis/{id}")
    public ResponseEntity<Void> deleteJenisProduk(@PathVariable("id") Long id) {
        jenisProdukService.deleteJenisProduk(id);
        return ResponseEntity.ok().build();
    }
}