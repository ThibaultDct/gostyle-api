package fr.epsi.gostyleapi.external.dto;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class UserCouponDTO {

    private UUID user_id;
    private UUID coupon_id;
    private Date validity;
    private boolean is_used;

    public UserCouponDTO() {
    }

    public UserCouponDTO(UUID user_id, UUID coupon_id, Date validity, boolean is_used) {
        this.user_id = user_id;
        this.coupon_id = coupon_id;
        this.validity = validity;
        this.is_used = is_used;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public UUID getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(UUID coupon_id) {
        this.coupon_id = coupon_id;
    }

    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }

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
        UserCouponDTO that = (UserCouponDTO) o;
        return isIs_used() == that.isIs_used() &&
                Objects.equals(getUser_id(), that.getUser_id()) &&
                Objects.equals(getCoupon_id(), that.getCoupon_id()) &&
                Objects.equals(getValidity(), that.getValidity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser_id(), getCoupon_id(), getValidity(), isIs_used());
    }

    @Override
    public String toString() {
        return "UserCouponDTO{" +
                "user_id=" + user_id +
                ", coupon_id=" + coupon_id +
                ", validity=" + validity +
                ", is_used=" + is_used +
                '}';
    }
}
