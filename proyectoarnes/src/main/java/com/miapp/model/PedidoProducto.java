package com.miapp.model;

import jakarta.persistence.*;

@Entity
@Table(name="pedido_productos")
public class PedidoProducto {

    @EmbeddedId
    private PedidoProductoKey id;

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name="pedido_id")
    private Pedido pedido;

    @ManyToOne
    @MapsId("productoId")
    @JoinColumn(name="producto_id")
    private Productos producto;


    @Column
    private int cantidad;

    public PedidoProductoKey getId() {
        return id;
    }

    public void setId(PedidoProductoKey id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
