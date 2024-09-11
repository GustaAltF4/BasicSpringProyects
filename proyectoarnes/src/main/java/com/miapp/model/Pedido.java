package com.miapp.model;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="usuario_id",nullable = false)
    private Usuarios usuario;

    @Column
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column
    private Estado estado;

    public enum Estado {
        PENDIENTE, COMPLETADO, CANCELADO
    }

    @Column
    @OneToMany(mappedBy = "pedido")
    private List<PedidoProducto> productos;


    public List<PedidoProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<PedidoProducto> productos) {
        this.productos = productos;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
