package by.geekbrains.appnotes.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class NoteEntity implements Parcelable {

    private String title;
    private String description;
    private String date;

    public NoteEntity(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public NoteEntity() {
    }

    protected NoteEntity(Parcel in) {
        title = in.readString();
        description = in.readString();
        date = in.readString();
    }

    public static final Creator<NoteEntity> CREATOR = new Creator<NoteEntity>() {
        @Override
        public NoteEntity createFromParcel(Parcel in) {
            return new NoteEntity(in);
        }

        @Override
        public NoteEntity[] newArray(int size) {
            return new NoteEntity[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(date);
    }
}