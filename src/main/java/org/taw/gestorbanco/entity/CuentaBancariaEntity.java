package org.taw.gestorbanco.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "cuenta_bancaria", schema = "gestor_banco", catalog = "")
public class CuentaBancariaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;
    @Basic
    @Column(name = "moneda", nullable = false, length = 20)
    private String moneda;
    @Basic
    @Column(name = "saldo", nullable = false, precision = 0)
    private Double saldo;
    @Basic
    @Column(name = "sospechosa", nullable = false)
    private Byte sospechosa;
    @Basic
    @Column(name = "activo", nullable = false)
    private Byte activo;
    @ManyToOne
    @JoinColumn(name = "divisa_id", referencedColumnName = "id", nullable = false)
    private DivisaEntity divisaByDivisaId;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private UsuarioEntity usuarioByUsuarioId;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) return false;
        if (moneda != null ? !moneda.equals(that.moneda) : that.moneda != null) return false;
        if (saldo != null ? !saldo.equals(that.saldo) : that.saldo != null) return false;
        if (sospechosa != null ? !sospechosa.equals(that.sospechosa) : that.sospechosa != null) return false;
        if (activo != null ? !activo.equals(that.activo) : that.activo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (moneda != null ? moneda.hashCode() : 0);
        result = 31 * result + (saldo != null ? saldo.hashCode() : 0);
        result = 31 * result + (sospechosa != null ? sospechosa.hashCode() : 0);
        result = 31 * result + (activo != null ? activo.hashCode() : 0);
        return result;
    }

    public DivisaEntity getDivisaByDivisaId() {
        return divisaByDivisaId;
    }

    public void setDivisaByDivisaId(DivisaEntity divisaByDivisaId) {
        this.divisaByDivisaId = divisaByDivisaId;
    }

    public UsuarioEntity getUsuarioByUsuarioId() {
        return usuarioByUsuarioId;
    }

    public void setUsuarioByUsuarioId(UsuarioEntity usuarioByUsuarioId) {
        this.usuarioByUsuarioId = usuarioByUsuarioId;
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
