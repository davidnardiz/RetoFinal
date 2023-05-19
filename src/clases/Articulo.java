package clases;

import java.time.LocalDate;

/**
 *
 * @author Jason
 */
public class Articulo {

    private String id_articulo;
    private String descripcion;
    private float precio;
    private float peso;
    private LocalDate fechaSubida;
    private LocalDate fechaCompra;
    private String lugarEntrega;
    private String vendedor;
    private String imagen;
    private int valoracion;

    //Getters y setters
    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getImagen() {
        return imagen;
    }

    public String getId_articulo() {
        return id_articulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public float getPeso() {
        return peso;
    }

    public LocalDate getFechaSubida() {
        return fechaSubida;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public String getLugarEntrega() {
        return lugarEntrega;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setId_articulo(String id_articulo) {
        this.id_articulo = id_articulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setFechaSubida(LocalDate fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public void setLugarEntrega(String lugarEntrega) {
        this.lugarEntrega = lugarEntrega;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    /**
     *
     * @return Devuelve todos los atributos del articulo
     */
    @Override
    public String toString() {
        return "Articulo{" + "id_articulo=" + id_articulo + ", descripcion=" + descripcion + ", precio=" + precio + ", peso=" + peso + ", fechaSubida=" + fechaSubida + ", fechaCompra=" + fechaCompra + ", lugarEntrega=" + lugarEntrega + ", vendedor=" + vendedor + '}';
    }

}
