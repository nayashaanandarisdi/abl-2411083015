package com.nayasha.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long produkId;
    private Long pelangganId;
    private String tglTran;
    private int jumlah;
    private double total;

    public Order() {
    }

    public Order(Long id, Long produkId, Long pelangganId, String tglTran, int jumlah, double total) {
        this.id = id;
        this.produkId = produkId;
        this.pelangganId = pelangganId;
        this.tglTran = tglTran;
        this.jumlah = jumlah;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProdukId() {
        return produkId;
    }

    public void setProdukId(Long produkId) {
        this.produkId = produkId;
    }

    public Long getPelangganId() {
        return pelangganId;
    }

    public void setPelangganId(Long pelangganId) {
        this.pelangganId = pelangganId;
    }

    public String getTglTran() {
        return tglTran;
    }

    public void setTglTran(String tglTran) {
        this.tglTran = tglTran;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}