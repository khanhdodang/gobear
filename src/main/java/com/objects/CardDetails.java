package com.objects;

public class CardDetails {
    String price;
    String ownDamageAndTheft;
    String VTPLBodilyInjury;
    String VTPLPropertyDamage;
    String ActOfNature;
    String WhenSomeoneInYourCarGetsHurt;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOwnDamageAndTheft() {
        return ownDamageAndTheft;
    }

    public void setOwnDamageAndTheft(String ownDamageAndTheft) {
        this.ownDamageAndTheft = ownDamageAndTheft;
    }

    public String getVTPLBodilyInjury() {
        return VTPLBodilyInjury;
    }

    public void setVTPLBodilyInjury(String VTPLBodilyInjury) {
        this.VTPLBodilyInjury = VTPLBodilyInjury;
    }

    public String getVTPLPropertyDamage() {
        return VTPLPropertyDamage;
    }

    public void setVTPLPropertyDamage(String VTPLPropertyDamage) {
        this.VTPLPropertyDamage = VTPLPropertyDamage;
    }

    public String getActOfNature() {
        return ActOfNature;
    }

    public void setActOfNature(String actOfNature) {
        ActOfNature = actOfNature;
    }

    public String getWhenSomeoneInYourCarGetsHurt() {
        return WhenSomeoneInYourCarGetsHurt;
    }

    public void setWhenSomeoneInYourCarGetsHurt(String whenSomeoneInYourCarGetsHurt) {
        WhenSomeoneInYourCarGetsHurt = whenSomeoneInYourCarGetsHurt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardDetails cardDetails = (CardDetails) o;
        return ownDamageAndTheft.equals(cardDetails.ownDamageAndTheft) &&
                VTPLBodilyInjury.toLowerCase().equals(cardDetails.VTPLBodilyInjury.toLowerCase()) &&
                VTPLPropertyDamage.toLowerCase().equals(cardDetails.VTPLPropertyDamage.toLowerCase()) &&
                ActOfNature.toLowerCase().equals(cardDetails.ActOfNature.toLowerCase()) &&
                WhenSomeoneInYourCarGetsHurt.toLowerCase().equals(cardDetails.WhenSomeoneInYourCarGetsHurt.toLowerCase()) ;
    }

    @Override
    public String toString() {
        return String.format(ownDamageAndTheft + ";" + VTPLBodilyInjury + ";" + VTPLPropertyDamage
        + ";" + ActOfNature + ";" + WhenSomeoneInYourCarGetsHurt);
    }
}
