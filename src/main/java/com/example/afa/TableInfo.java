package com.example.afa;

public class TableInfo {
    private String licensePlate;

    private String OwnerFirst_Name;

    private String OwnerLast_Name;

    private String VehicleType;

    private String VehicleYear;

    private String hasFine;

    public TableInfo(String licensePlate, String ownerFirst_Name, String ownerLast_Name, String vehicleType, String vehicleYear, String hasFine) {
        this.licensePlate = licensePlate;
        OwnerFirst_Name = ownerFirst_Name;
        OwnerLast_Name = ownerLast_Name;
        VehicleType = vehicleType;
        VehicleYear = vehicleYear;
        this.hasFine = hasFine;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setOwnerFirst_Name(String ownerFirst_Name) {
        OwnerFirst_Name = ownerFirst_Name;
    }

    public void setOwnerLast_Name(String ownerLast_Name) {
        OwnerLast_Name = ownerLast_Name;
    }

    public void setVehicleType(String vehicleType) {
        VehicleType = vehicleType;
    }

    public void setVehicleYear(String vehicleYear) {
        VehicleYear = vehicleYear;
    }

    public void setHasFine(String hasFine) {
        this.hasFine = hasFine;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getOwnerFirst_Name() {
        return OwnerFirst_Name;
    }

    public String getOwnerLast_Name() {
        return OwnerLast_Name;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public String getVehicleYear() {
        return VehicleYear;
    }

    public String getHasFine() {
        return hasFine;
    }
}