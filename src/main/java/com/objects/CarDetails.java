package com.objects;

public class CarDetails {
    String carYear;
    String carMake;
    String carModel;
    String carVariant;
    boolean stillPayingForCarLoan;
    String carUsage;
    String insuredFor;

    public String getCarYear() {
        return carYear;
    }

    public void setCarYear(String carYear) {
        this.carYear = carYear;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarVariant() {
        return carVariant;
    }

    public void setCarVariant(String carVariant) {
        this.carVariant = carVariant;
    }

    public boolean isStillPayingForCarLoan() {
        return stillPayingForCarLoan;
    }

    public void setStillPayingForCarLoan(boolean stillPayingForCarLoan) {
        this.stillPayingForCarLoan = stillPayingForCarLoan;
    }

    public String getCarUsage() {
        return carUsage;
    }

    public void setCarUsage(String carUsage) {
        this.carUsage = carUsage;
    }

    public String getInsuredFor() {
        return insuredFor;
    }

    public void setInsuredFor(String insuredFor) {
        this.insuredFor = insuredFor;
    }

    public CarDetails() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarDetails carDetails = (CarDetails) o;
        return carYear.equals(carDetails.carYear) &&
                carMake.toLowerCase().equals(carDetails.carMake.toLowerCase()) &&
                carModel.toLowerCase().equals(carDetails.carModel.toLowerCase()) &&
                carVariant.toLowerCase().equals(carDetails.carVariant.toLowerCase()) &&
                carUsage.toLowerCase().equals(carDetails.carUsage.toLowerCase()) &&
                insuredFor.equals(carDetails.insuredFor) &&
                stillPayingForCarLoan == carDetails.stillPayingForCarLoan;
    }
}
