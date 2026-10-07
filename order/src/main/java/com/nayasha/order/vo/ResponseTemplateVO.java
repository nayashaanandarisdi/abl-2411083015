package com.nayasha.order.vo;

import com.nayasha.order.entity.Order;

public class ResponseTemplateVO {
    private Order order;
    private Pelanggan pelanggan;
    private Produk produk;

    // Getter dan Setter
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
    public Pelanggan getPelanggan() { return pelanggan; }
    public void setPelanggan(Pelanggan pelanggan) { this.pelanggan = pelanggan; }
    public Produk getProduk() { return produk; }
    public void setProduk(Produk produk) { this.produk = produk; }
}