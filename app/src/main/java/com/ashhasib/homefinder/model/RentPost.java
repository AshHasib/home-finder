package com.ashhasib.homefinder.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RentPost {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("rent")
    @Expose
    private String rent;
    @SerializedName("num_bedrooms")
    @Expose
    private String numBedrooms;
    @SerializedName("num_bathrooms")
    @Expose
    private String numBathrooms;
    @SerializedName("num_floor")
    @Expose
    private String numFloor;
    @SerializedName("image_ref")
    @Expose
    private String imageRef;

    /**
     * No args constructor for use in serialization
     *
     */
    public RentPost() {
    }

    /**
     *
     * @param area
     * @param numFloor
     * @param numBathrooms
     * @param numBedrooms
     * @param description
     * @param imageRef
     * @param rent
     * @param username
     */
    public RentPost(String username, String description, String area, String rent, String numBedrooms, String numBathrooms, String numFloor, String imageRef) {
        super();
        this.username = username;
        this.description = description;
        this.area = area;
        this.rent = rent;
        this.numBedrooms = numBedrooms;
        this.numBathrooms = numBathrooms;
        this.numFloor = numFloor;
        this.imageRef = imageRef;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getNumBedrooms() {
        return numBedrooms;
    }

    public void setNumBedrooms(String numBedrooms) {
        this.numBedrooms = numBedrooms;
    }

    public String getNumBathrooms() {
        return numBathrooms;
    }

    public void setNumBathrooms(String numBathrooms) {
        this.numBathrooms = numBathrooms;
    }

    public String getNumFloor() {
        return numFloor;
    }

    public void setNumFloor(String numFloor) {
        this.numFloor = numFloor;
    }

    public String getImageRef() {
        return imageRef;
    }

    public void setImageRef(String imageRef) {
        this.imageRef = imageRef;
    }


    public boolean isEmpty() {
        return (this.username.isEmpty() || this.description.isEmpty() || this.area.isEmpty()
        || this.rent.isEmpty() || this.numBedrooms.isEmpty() || this.numBathrooms.isEmpty() || this.numFloor.isEmpty()||this.imageRef.isEmpty());
    }
}