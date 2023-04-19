package com.example.afa;

public class FineInfoModel {
    private Integer ID;
    private String Fined_Plate;
    private String ammountFined;
    private String fineReason;
    private String dateFined;

    public FineInfoModel(Integer ID, String fined_Plate, String ammountFined, String fineReason, String dateFined) {
        this.ID = ID;
        Fined_Plate = fined_Plate;
        this.ammountFined = ammountFined;
        this.fineReason = fineReason;
        this.dateFined = dateFined;
    }

    public Integer getID() {
        return ID;
    }

    public String getFined_Plate() {
        return Fined_Plate;
    }

    public String getAmmountFined() {
        return ammountFined;
    }

    public String getFineReason() {
        return fineReason;
    }

    public String getDateFined() {
        return dateFined;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setFined_Plate(String fined_Plate) {
        Fined_Plate = fined_Plate;
    }

    public void setAmmountFined(String ammountFined) {
        this.ammountFined = ammountFined;
    }

    public void setFineReason(String fineReason) {
        this.fineReason = fineReason;
    }

    public void setDateFined(String dateFined) {
        this.dateFined = dateFined;
    }
}
