package com.example.myaccuweather.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccuWeatherModel {

    @SerializedName("LocalObservationDateTime")
    @Expose
    private String localObservationDateTime;
    @SerializedName("EpochTime")
    @Expose
    private Integer epochTime;
    @SerializedName("WeatherText")
    @Expose
    private String weatherText;
    @SerializedName("WeatherIcon")
    @Expose
    private Integer weatherIcon;
    @SerializedName("HasPrecipitation")
    @Expose
    private Boolean hasPrecipitation;
    @SerializedName("PrecipitationType")
    @Expose
    private Object precipitationType;
    @SerializedName("IsDayTime")
    @Expose
    private Boolean isDayTime;
    @SerializedName("Temperature")
    @Expose
    private Temperature temperature;
    @SerializedName("MobileLink")
    @Expose
    private String mobileLink;
    @SerializedName("Link")
    @Expose
    private String link;

    public String getLocalObservationDateTime() {
        return localObservationDateTime;
    }

    public void setLocalObservationDateTime(String localObservationDateTime) {
        this.localObservationDateTime = localObservationDateTime;
    }

    public Integer getEpochTime() {
        return epochTime;
    }

    public void setEpochTime(Integer epochTime) {
        this.epochTime = epochTime;
    }

    public String getWeatherText() {
        return weatherText;
    }

    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

    public Integer getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(Integer weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public Boolean getHasPrecipitation() {
        return hasPrecipitation;
    }

    public void setHasPrecipitation(Boolean hasPrecipitation) {
        this.hasPrecipitation = hasPrecipitation;
    }

    public Object getPrecipitationType() {
        return precipitationType;
    }

    public void setPrecipitationType(Object precipitationType) {
        this.precipitationType = precipitationType;
    }

    public Boolean getIsDayTime() {
        return isDayTime;
    }

    public void setIsDayTime(Boolean isDayTime) {
        this.isDayTime = isDayTime;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public String getMobileLink() {
        return mobileLink;
    }

    public void setMobileLink(String mobileLink) {
        this.mobileLink = mobileLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public class Imperial {

        @SerializedName("Value")
        @Expose
        private Double value;
        @SerializedName("Unit")
        @Expose
        private String unit;
        @SerializedName("UnitType")
        @Expose
        private Integer unitType;

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Integer getUnitType() {
            return unitType;
        }

        public void setUnitType(Integer unitType) {
            this.unitType = unitType;
        }

    }

    public class Metric {

        @SerializedName("Value")
        @Expose
        private Double value;
        @SerializedName("Unit")
        @Expose
        private String unit;
        @SerializedName("UnitType")
        @Expose
        private Integer unitType;

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Integer getUnitType() {
            return unitType;
        }

        public void setUnitType(Integer unitType) {
            this.unitType = unitType;
        }

    }

    public class Temperature {

        @SerializedName("Metric")
        @Expose
        private Metric metric;
        @SerializedName("Imperial")
        @Expose
        private Imperial imperial;

        public Metric getMetric() {
            return metric;
        }

        public void setMetric(Metric metric) {
            this.metric = metric;
        }

        public Imperial getImperial() {
            return imperial;
        }

        public void setImperial(Imperial imperial) {
            this.imperial = imperial;
        }

    }
}