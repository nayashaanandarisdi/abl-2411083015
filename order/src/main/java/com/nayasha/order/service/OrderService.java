package com.nayasha.order.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.nayasha.order.entity.Order;
import com.nayasha.order.repository.OrderRepository;
import com.nayasha.order.vo.Pelanggan;
import com.nayasha.order.vo.Produk;
import com.nayasha.order.vo.ResponseTemplateVO;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    // Pastikan port ini sesuai dengan port di application.properties masing-masing project
    private final String URL_PELANGGAN = "http://localhost:8081/api/pelanggan/";
    private final String URL_PRODUK = "http://localhost:8080/api/produk/";

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    // 1. Modifikasi Method Save (Otomatis hitung total)
    public Order saveOrder(Order order) {
        // Ambil data produk berdasarkan produkId yang diinput user
        Produk produk = restTemplate.getForObject(URL_PRODUK + order.getProdukId(), Produk.class);

        // Jika produk ditemukan, hitung: Total = jumlah pesanan * harga produk
        if (produk != null) {
            double totalHarga = order.getJumlah() * produk.getHarga();
            order.setTotal(totalHarga);
        }

        return orderRepository.save(order);
    }

    // METHOD YANG HILANG: Update Order
    public Order updateOrder(Long id, Order order) {
        Order existing = orderRepository.findById(id).orElse(null);
        if (existing != null) {
            if (order.getProdukId() != null) {
                existing.setProdukId(order.getProdukId());
            }
            if (order.getPelangganId() != null) {
                existing.setPelangganId(order.getPelangganId());
            }
            if (order.getTglTran() != null) {
                existing.setTglTran(order.getTglTran());
            }
            if (order.getJumlah() != 0) {
                existing.setJumlah(order.getJumlah());
            }
            if (order.getTotal() != 0.0) {
                existing.setTotal(order.getTotal());
            }
            return orderRepository.save(existing);
        }
        return null;
    }

    // 2. Method Baru untuk merelasikan Order, Pelanggan, dan Produk
    public ResponseTemplateVO getOrderWithPelangganAndProduk(Long orderId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Order order = orderRepository.findById(orderId).orElse(null);

        if (order != null) {
            // Tarik data dari service lain menggunakan ID yang ada di tabel order
            Pelanggan pelanggan = restTemplate.getForObject(URL_PELANGGAN + order.getPelangganId(), Pelanggan.class);
            Produk produk = restTemplate.getForObject(URL_PRODUK + order.getProdukId(), Produk.class);

            // Masukkan ke dalam wadah VO
            vo.setOrder(order);
            vo.setPelanggan(pelanggan);
            vo.setProduk(produk);
        }
        return vo;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
    // Method Baru: Menampilkan SEMUA order beserta Pelanggan dan Produknya
    public java.util.List<ResponseTemplateVO> getAllOrderLengkap() {
        java.util.List<ResponseTemplateVO> voList = new java.util.ArrayList<>();
        java.util.List<Order> orders = orderRepository.findAll();

        for (Order order : orders) {
            ResponseTemplateVO vo = new ResponseTemplateVO();
            vo.setOrder(order);

            // Tarik data
            Pelanggan pelanggan = restTemplate.getForObject(URL_PELANGGAN + order.getPelangganId(), Pelanggan.class);
            Produk produk = restTemplate.getForObject(URL_PRODUK + order.getProdukId(), Produk.class);

            vo.setPelanggan(pelanggan);
            vo.setProduk(produk);
            voList.add(vo);
        }
        return voList;
    }
}