package org.taw.gestorbanco.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "cuenta_bancaria", schema = "gestor_banco", catalog = "")
public class CuentaBancariaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "saldo", nullable = false, precision = 0)
    private Double saldo;
    @Basic
    @Column(name = "moneda", nullable = false, length = 20)
    private String moneda;
    @Basic
    @Column(name = "sospechosa", nullable = false)
    private Byte sospechosa;
    @Basic
    @Column(name = "activo", nullable = false)
    private Byte activo;
    @OneToMany(mappedBy = "cuentaBancariaId")
    private Collection<AsignacionEntity> asignacionsById;
    @ManyToOne
    @JoinColumn(name = "divisa_id", referencedColumnName = "id", nullable = false)
    private DivisaEntity divisaByDivisaId;
    @OneToMany(mappedBy = "cuentaBancariaByIdCuentaOrigen")
    private Collection<OperacionBancariaEntity> operacionBancariasById;
    @OneToMany(mappedBy = "cuentaBancariaByIdCuentaDestino")
    private Collection<OperacionBancariaEntity> operacionBancariasById_0;
    @OneToMany(mappedBy = "cuentaBancariaByCuentaBancariaId")
    private Collection<SolicitudActivacionEntity> solicitudActivacionsById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Byte getSospechosa() {
        return sospechosa;
    }

    public void setSospechosa(Byte sospechosa) {
        this.sospechosa = sospechosa;
    }

    public Byte getActivo() {
        return activo;
    }

    public void setActivo(Byte activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaBancariaEntity that = (CuentaBancariaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(saldo, that.saldo) && Objects.equals(moneda, that.moneda) && Objects.equals(sospechosa, that.sospechosa) && Objects.equals(activo, that.activo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, saldo, moneda, sospechosa, activo);
    }

    public Collection<AsignacionEntity> getAsignacionsById() {
        return asignacionsById;
    }

    public void setAsignacionsById(Collection<AsignacionEntity> asignacionsById) {
        this.asignacionsById = asignacionsById;
    }

    public DivisaEntity getDivisaByDivisaId() {
        return divisaByDivisaId;
    }

    public void setDivisaByDivisaId(DivisaEntity divisaByDivisaId) {
        this.divisaByDivisaId = divisaByDivisaId;
    }

    public Collection<OperacionBancariaEntity> getOperacionBancariasById() {
        return operacionBancariasById;
    }

    public void setOperacionBancariasById(Collection<OperacionBancariaEntity> operacionBancariasById) {
        this.operacionBancariasById = operacionBancariasById;
    }

    public Collection<OperacionBancariaEntity> getOperacionBancariasById_0() {
        return operacionBancariasById_0;
    }

    public void setOperacionBancariasById_0(Collection<OperacionBancariaEntity> operacionBancariasById_0) {
        this.operacionBancariasById_0 = operacionBancariasById_0;
    }

    public Collection<SolicitudActivacionEntity> getSolicitudActivacionsById() {
        return solicitudActivacionsById;
    }

    public void setSolicitudActivacionsById(Collection<SolicitudActivacionEntity> solicitudActivacionsById) {
        this.solicitudActivacionsById = solicitudActivacionsById;
    }
}
