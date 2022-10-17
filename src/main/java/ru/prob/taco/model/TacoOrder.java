package ru.prob.taco.model;

import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class TacoOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date placeAt = new Date();
    @NotBlank(message = "Delivery name is required")
    private String deliveryName;
    @NotBlank(message = "Street is required")
    private String deliveryStreet;
    @NotBlank(message = "is required")
    private String deliveryCity;
    @NotBlank(message = "City is required")
    private String deliveryState;
    @NotBlank(message = "State is required")
    private String deliveryZip;
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
            message = "Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Taco> tacos = new ArrayList<>();

    @ManyToOne
    private UserU userU;

    public TacoOrder() {
    }

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }

    public Long getId() {
        return this.id;
    }

    public Date getPlaceAt() {
        return this.placeAt;
    }

    public @NotBlank(message = "Delivery name is required") String getDeliveryName() {
        return this.deliveryName;
    }

    public @NotBlank(message = "Street is required") String getDeliveryStreet() {
        return this.deliveryStreet;
    }

    public @NotBlank(message = "is required") String getDeliveryCity() {
        return this.deliveryCity;
    }

    public @NotBlank(message = "City is required") String getDeliveryState() {
        return this.deliveryState;
    }

    public @NotBlank(message = "State is required") String getDeliveryZip() {
        return this.deliveryZip;
    }

    public @CreditCardNumber(message = "Not a valid credit card number") String getCcNumber() {
        return this.ccNumber;
    }

    public @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
            message = "Must be formatted MM/YY") String getCcExpiration() {
        return this.ccExpiration;
    }

    public @Digits(integer = 3, fraction = 0, message = "Invalid CVV") String getCcCVV() {
        return this.ccCVV;
    }

    public List<Taco> getTacos() {
        return this.tacos;
    }

    public UserU getUserU() {
        return this.userU;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlaceAt(Date placeAt) {
        this.placeAt = placeAt;
    }

    public void setDeliveryName(@NotBlank(message = "Delivery name is required") String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public void setDeliveryStreet(@NotBlank(message = "Street is required") String deliveryStreet) {
        this.deliveryStreet = deliveryStreet;
    }

    public void setDeliveryCity(@NotBlank(message = "is required") String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public void setDeliveryState(@NotBlank(message = "City is required") String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public void setDeliveryZip(@NotBlank(message = "State is required") String deliveryZip) {
        this.deliveryZip = deliveryZip;
    }

    public void setCcNumber(@CreditCardNumber(message = "Not a valid credit card number") String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public void setCcExpiration(@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
            message = "Must be formatted MM/YY") String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    public void setCcCVV(@Digits(integer = 3, fraction = 0, message = "Invalid CVV") String ccCVV) {
        this.ccCVV = ccCVV;
    }

    public void setTacos(List<Taco> tacos) {
        this.tacos = tacos;
    }

    public void setUserU(UserU userU) {
        this.userU = userU;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TacoOrder)) return false;
        final TacoOrder other = (TacoOrder) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$placeAt = this.getPlaceAt();
        final Object other$placeAt = other.getPlaceAt();
        if (this$placeAt == null ? other$placeAt != null : !this$placeAt.equals(other$placeAt)) return false;
        final Object this$deliveryName = this.getDeliveryName();
        final Object other$deliveryName = other.getDeliveryName();
        if (this$deliveryName == null ? other$deliveryName != null : !this$deliveryName.equals(other$deliveryName))
            return false;
        final Object this$deliveryStreet = this.getDeliveryStreet();
        final Object other$deliveryStreet = other.getDeliveryStreet();
        if (this$deliveryStreet == null ? other$deliveryStreet != null : !this$deliveryStreet.equals(other$deliveryStreet))
            return false;
        final Object this$deliveryCity = this.getDeliveryCity();
        final Object other$deliveryCity = other.getDeliveryCity();
        if (this$deliveryCity == null ? other$deliveryCity != null : !this$deliveryCity.equals(other$deliveryCity))
            return false;
        final Object this$deliveryState = this.getDeliveryState();
        final Object other$deliveryState = other.getDeliveryState();
        if (this$deliveryState == null ? other$deliveryState != null : !this$deliveryState.equals(other$deliveryState))
            return false;
        final Object this$deliveryZip = this.getDeliveryZip();
        final Object other$deliveryZip = other.getDeliveryZip();
        if (this$deliveryZip == null ? other$deliveryZip != null : !this$deliveryZip.equals(other$deliveryZip))
            return false;
        final Object this$ccNumber = this.getCcNumber();
        final Object other$ccNumber = other.getCcNumber();
        if (this$ccNumber == null ? other$ccNumber != null : !this$ccNumber.equals(other$ccNumber)) return false;
        final Object this$ccExpiration = this.getCcExpiration();
        final Object other$ccExpiration = other.getCcExpiration();
        if (this$ccExpiration == null ? other$ccExpiration != null : !this$ccExpiration.equals(other$ccExpiration))
            return false;
        final Object this$ccCVV = this.getCcCVV();
        final Object other$ccCVV = other.getCcCVV();
        if (this$ccCVV == null ? other$ccCVV != null : !this$ccCVV.equals(other$ccCVV)) return false;
        final Object this$tacos = this.getTacos();
        final Object other$tacos = other.getTacos();
        if (this$tacos == null ? other$tacos != null : !this$tacos.equals(other$tacos)) return false;
        final Object this$userU = this.getUserU();
        final Object other$userU = other.getUserU();
        if (this$userU == null ? other$userU != null : !this$userU.equals(other$userU)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TacoOrder;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $placeAt = this.getPlaceAt();
        result = result * PRIME + ($placeAt == null ? 43 : $placeAt.hashCode());
        final Object $deliveryName = this.getDeliveryName();
        result = result * PRIME + ($deliveryName == null ? 43 : $deliveryName.hashCode());
        final Object $deliveryStreet = this.getDeliveryStreet();
        result = result * PRIME + ($deliveryStreet == null ? 43 : $deliveryStreet.hashCode());
        final Object $deliveryCity = this.getDeliveryCity();
        result = result * PRIME + ($deliveryCity == null ? 43 : $deliveryCity.hashCode());
        final Object $deliveryState = this.getDeliveryState();
        result = result * PRIME + ($deliveryState == null ? 43 : $deliveryState.hashCode());
        final Object $deliveryZip = this.getDeliveryZip();
        result = result * PRIME + ($deliveryZip == null ? 43 : $deliveryZip.hashCode());
        final Object $ccNumber = this.getCcNumber();
        result = result * PRIME + ($ccNumber == null ? 43 : $ccNumber.hashCode());
        final Object $ccExpiration = this.getCcExpiration();
        result = result * PRIME + ($ccExpiration == null ? 43 : $ccExpiration.hashCode());
        final Object $ccCVV = this.getCcCVV();
        result = result * PRIME + ($ccCVV == null ? 43 : $ccCVV.hashCode());
        final Object $tacos = this.getTacos();
        result = result * PRIME + ($tacos == null ? 43 : $tacos.hashCode());
        final Object $userU = this.getUserU();
        result = result * PRIME + ($userU == null ? 43 : $userU.hashCode());
        return result;
    }

    public String toString() {
        return "TacoOrder(id=" + this.getId() + ", placeAt=" + this.getPlaceAt() + ", deliveryName=" + this.getDeliveryName() + ", deliveryStreet=" + this.getDeliveryStreet() + ", deliveryCity=" + this.getDeliveryCity() + ", deliveryState=" + this.getDeliveryState() + ", deliveryZip=" + this.getDeliveryZip() + ", ccNumber=" + this.getCcNumber() + ", ccExpiration=" + this.getCcExpiration() + ", ccCVV=" + this.getCcCVV() + ", tacos=" + this.getTacos() + ", userU=" + this.getUserU() + ")";
    }
}
