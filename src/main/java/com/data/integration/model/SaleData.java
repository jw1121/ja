package com.data.integration.model;

import com.data.integration.utility.StringConvert;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @NotNull
    @Size(max = 8, message = "Invalid value length.")
    private String book;
    @Size(max = 8, message = "Invalid value length.")
    private String page;
    private double docstampAmount;
    private int derivedSalePriceFlorida;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    private String saleDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String recordedDate;
    private String saleInstrument;
    private int totalParcelCount;
    @Range(min = 1900, max = 2100, message = "Please select valid year")
    private int camaTaxYear;
    @NotNull
    @Valid
    private ParcelMatchCardsComponent parcelMatchCardsComponent;
    @NotNull
    private BuyerAddressComponent buyerAddressComponent;
    private List<BuyerNamesComponent> buyerNamesComponent = null;
    private VacantOrImprovedLandTable vacantOrImprovedLandTable;

    private String documentNotes;
    @JsonProperty("document_notes_2")
    private String documentNotes2;
    @JsonProperty("document_notes_3")
    private String documentNotes3;
    @JsonProperty("document_notes_4")
    private String documentNotes4;

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

    public String getDocumentNotes() {
        return documentNotes;
    }

    public void setDocumentNotes(String documentNotes) {
        this.documentNotes = StringConvert.toUpper(documentNotes);
    }

    public String getDocumentNotes2() {
        return documentNotes2;
    }

    public void setDocumentNotes2(String documentNotes2) {
        this.documentNotes2 = StringConvert.toUpper(documentNotes2);
    }

    public String getDocumentNotes3() {
        return documentNotes3;
    }

    public void setDocumentNotes3(String documentNotes3) {
        this.documentNotes3 = StringConvert.toUpper(documentNotes3);
    }

    public String getDocumentNotes4() {
        return documentNotes4;
    }

    public void setDocumentNotes4(String documentNotes4) {
        this.documentNotes4 = StringConvert.toUpper(documentNotes4);
    }

    @Override
    public String toString() {
        return "SaleData{" +
                "book='" + book + '\'' +
                ", page='" + page + '\'' +
                ", docstampAmount=" + docstampAmount +
                ", derivedSalePriceFlorida=" + derivedSalePriceFlorida +
                ", saleDate='" + saleDate + '\'' +
                ", recordedDate='" + recordedDate + '\'' +
                ", saleInstrument='" + saleInstrument + '\'' +
                ", totalParcelCount=" + totalParcelCount +
                ", camaTaxYear=" + camaTaxYear +
                ", parcelMatchCardsComponent=" + parcelMatchCardsComponent +
                ", buyerAddressComponent=" + buyerAddressComponent +
                ", buyerNamesComponent=" + buyerNamesComponent +
                ", vacantOrImprovedLandTable=" + vacantOrImprovedLandTable +
                ", documentNotes='" + documentNotes + '\'' +
                ", documentNotes2='" + documentNotes2 + '\'' +
                ", documentNotes3='" + documentNotes3 + '\'' +
                ", documentNotes4='" + documentNotes4 + '\'' +
                '}';
    }
}