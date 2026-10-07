package com.nayasha.order.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nayasha.order.entity.Order;
import com.nayasha.order.service.OrderService;
import com.nayasha.order.vo.ResponseTemplateVO;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    // Menggunakan Constructor Injection sebagai best practice (menggantikan @Autowired pada field)
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Endpoint untuk melihat SEMUA data lengkap
    @GetMapping("/lengkap")
    public ResponseEntity<List<ResponseTemplateVO>> getAllOrderLengkap() {
        return ResponseEntity.ok(orderService.getAllOrderLengkap());
    }
    // Mengambil semua data order
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrder() {
        return ResponseEntity.ok(orderService.getAllOrder());
    }

    // Mengambil data order standar berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    // Menyimpan order baru (Mengembalikan status 201 Created)
    @PostMapping
    public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
        Order savedOrder = orderService.saveOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    // Menghapus order berdasarkan ID (Mengembalikan status 204 No Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    // Method baru untuk menampilkan relasi lengkap (Order + Pelanggan + Produk)
    @GetMapping("/lengkap/{id}")
    public ResponseEntity<ResponseTemplateVO> getOrderLengkap(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.getOrderWithPelangganAndProduk(id));
    }
}