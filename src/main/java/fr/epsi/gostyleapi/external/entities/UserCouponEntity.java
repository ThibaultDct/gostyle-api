package fr.epsi.gostyleapi.external.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "user_coupon", schema = "public")
public class UserCouponEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID userId;
    private UUID couponId;
    private Date validity;
    private boolean is_used;

    @Id
    @Column(name = "id")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "coupon_id")
    public UUID getCouponId() {
        return couponId;
    }

    public void setCouponId(UUID couponId) {
        this.couponId = couponId;
    }

    @Basic
    @Column(name = "validity")
    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }

    @Basic
    @Column(name = "is_used")
    public boolean isIs_used() {
        return is_used;
    }

    public void setIs_used(boolean is_used) {
        this.is_used = is_used;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCouponEntity that = (UserCouponEntity) o;
        return isIs_used() == that.isIs_used() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getCouponId(), that.getCouponId()) &&
                Objects.equals(getValidity(), that.getValidity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getCouponId(), getValidity(), isIs_used());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ 'id': '");
        sb.append(this.getId());
        sb.append("', 'user_id': '");
        sb.append(this.getUserId());
        sb.append("', 'coupon_id': '");
        sb.append(this.getCouponId());
        sb.append("', 'validity': ");
        sb.append(this.getValidity());
        sb.append(", 'is_used': '");
        sb.append(this.isIs_used());
        sb.append(" }");
        return sb.toString();
    }
}
