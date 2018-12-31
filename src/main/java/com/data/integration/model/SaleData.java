package com.data.integration.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class SaleData implements Serializable {
    private final static long serialVersionUID = 1446924413947058634L;

    @NotNull
    @Max(8)
    private String book;
    @Max(8)
    private String page;
    private double docstamp_amount;
    private int derivedSalePriceFlorida;
    @NotNull
    private String sale_date;
    private String recorded_date;
    private String sale_instrument;
    private int total_parcel_count;
    private int tax_year;
    private ParcelMatchCardsComponent parcel_match_cards_component;
    private BuyerAddressComponent buyer_address_component;
    private List<BuyerNamesComponent> buyer_names_component = null;


    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public double getDocstamp_amount() {
        return docstamp_amount;
    }

    public void setDocstamp_amount(double docstamp_amount) {
        this.docstamp_amount = docstamp_amount;
    }

    public int getDerivedSalePriceFlorida() {
        return derivedSalePriceFlorida;
    }

    public void setDerivedSalePriceFlorida(int derivedSalePriceFlorida) {
        this.derivedSalePriceFlorida = derivedSalePriceFlorida;
    }

    public String getSale_date() {
        return sale_date;
    }

    public void setSale_date(String sale_date) {
        this.sale_date = sale_date;
    }

    public String getRecorded_date() {
        return recorded_date;
    }

    public void setRecorded_date(String recorded_date) {
        this.recorded_date = recorded_date;
    }

    public String getSale_instrument() {
        return sale_instrument;
    }

    public void setSale_instrument(String sale_instrument) {
        this.sale_instrument = sale_instrument;
    }

    public int getTotal_parcel_count() {
        return total_parcel_count;
    }

    public void setTotal_parcel_count(int total_parcel_count) {
        this.total_parcel_count = total_parcel_count;
    }

    public int getTax_year() {
        return tax_year;
    }

    public void setTax_year(int tax_year) {
        this.tax_year = tax_year;
    }

    public ParcelMatchCardsComponent getParcel_match_cards_component() {
        return parcel_match_cards_component;
    }

    public void setParcel_match_cards_component(ParcelMatchCardsComponent parcel_match_cards_component) {
        this.parcel_match_cards_component = parcel_match_cards_component;
    }

    public BuyerAddressComponent getBuyer_address_component() {
        return buyer_address_component;
    }

    public void setBuyer_address_component(BuyerAddressComponent buyer_address_component) {
        this.buyer_address_component = buyer_address_component;
    }

    public List<BuyerNamesComponent> getBuyer_names_component() {
        return buyer_names_component;
    }

    public void setBuyer_names_component(List<BuyerNamesComponent> buyer_names_component) {
        this.buyer_names_component = buyer_names_component;
    }

}