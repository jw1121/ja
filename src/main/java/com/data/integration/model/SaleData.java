package com.data.integration.model;

import com.data.integration.utility.StringConvert;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SaleData implements Serializable {
    private final static long serialVersionUID = 1446924413947058634L;

    @NotNull(message = "40001")
    @Size(max = 8, message = "40003")
    private String book;
    @Size(max = 8, message = "40003")
    private String page;
    private double docstampAmount;
    private int derivedSalePriceFlorida;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "40001")
    private String saleDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String recordedDate;
    private String saleInstrument;
    private int totalParcelCount;
    @NotNull(message = "40001")
    @Range(min = 1900, max = 2100, message = "40005")
    private int camaTaxYear;
    @NotNull(message = "40001")
    @Valid
    private ParcelMatchCardsComponent parcelMatchCardsComponent;
    @NotNull(message = "40001")
    private BuyerAddressComponent buyerAddressComponent;
    private List<BuyerNamesComponent> buyerNamesComponent = null;
    private VacantOrImprovedLandTable vacantOrImprovedLandTable;

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = StringConvert.toUpper(book);
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = StringConvert.toUpper(page);
    }

    public double getDocstampAmount() {
        return docstampAmount;
    }

    public void setDocstampAmount(double docstampAmount) {
        this.docstampAmount = docstampAmount;
    }

    public int getDerivedSalePriceFlorida() {
        return derivedSalePriceFlorida;
    }

    public void setDerivedSalePriceFlorida(int derivedSalePriceFlorida) {
        this.derivedSalePriceFlorida = derivedSalePriceFlorida;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = StringConvert.toUpper(saleDate);
    }

    public String getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(String recordedDate) {
        this.recordedDate = StringConvert.toUpper(recordedDate);
    }

    public String getSaleInstrument() {
        return saleInstrument;
    }

    public void setSaleInstrument(String saleInstrument) {
        this.saleInstrument = StringConvert.toUpper(saleInstrument);
    }

    public int getTotalParcelCount() {
        return totalParcelCount;
    }

    public void setTotalParcelCount(int totalParcelCount) {
        this.totalParcelCount = totalParcelCount;
    }

    public int getCamaTaxYear() { return camaTaxYear; }

    public void setCamaTaxYear(int camaTaxYear) { this.camaTaxYear = camaTaxYear; }

    public ParcelMatchCardsComponent getParcelMatchCardsComponent() {
        return parcelMatchCardsComponent;
    }

    public void setParcelMatchCardsComponent(ParcelMatchCardsComponent parcelMatchCardsComponent) {
        this.parcelMatchCardsComponent = parcelMatchCardsComponent;
    }

    public BuyerAddressComponent getBuyerAddressComponent() {
        return buyerAddressComponent;
    }

    public void setBuyerAddressComponent(BuyerAddressComponent buyerAddressComponent) {
        this.buyerAddressComponent = buyerAddressComponent;
    }

    public List<BuyerNamesComponent> getBuyerNamesComponent() {
        return buyerNamesComponent;
    }

    public void setBuyerNamesComponent(List<BuyerNamesComponent> buyerNamesComponent) {
        this.buyerNamesComponent = buyerNamesComponent;
    }

    public VacantOrImprovedLandTable getVacantOrImprovedLandTable() {
        return vacantOrImprovedLandTable;
    }

    public void setVacantOrImprovedLandTable(VacantOrImprovedLandTable vacantOrImprovedLandTable) {
        this.vacantOrImprovedLandTable = vacantOrImprovedLandTable;
    }
}