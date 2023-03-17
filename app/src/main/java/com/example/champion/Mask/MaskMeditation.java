package com.example.champion.Mask;

import android.os.Parcel;
import android.os.Parcelable;

public class MaskMeditation implements Parcelable {

    private int id;
    private String title;
    private String image;
    private String description;

    public MaskMeditation(int id, String title, String image, String description) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.description = description;
    }

    protected MaskMeditation(Parcel in) {
        id = in.readInt();
        title = in.readString();
        image = in.readString();
        description = in.readString();
    }

    public static final Creator<MaskMeditation> CREATOR = new Creator<MaskMeditation>() {
        @Override
        public MaskMeditation createFromParcel(Parcel in) {
            return new MaskMeditation(in);
        }

        @Override
        public MaskMeditation[] newArray(int size) {
            return new MaskMeditation[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(description);
    }
}
