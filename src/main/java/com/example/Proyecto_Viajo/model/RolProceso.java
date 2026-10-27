package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rol_proceso")
public class RolProceso {

  @Id
  @Column(name = "codigo", nullable = false, length = 20)
  private String codigo;

  @Column(name = "tipo", nullable = false, length = 30)
  private String tipo;

  @Column(name = "responsabilidad", nullable = false, length = 400)
  private String responsabilidad;

  public RolProceso() {
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getResponsabilidad() {
    return responsabilidad;
  }

  public void setResponsabilidad(String responsabilidad) {
    this.responsabilidad = responsabilidad;
  }
}
