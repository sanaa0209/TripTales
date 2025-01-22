package com.unimib.triptales.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import android.net.Uri;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity(
        foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId", onDelete = CASCADE),
        indices = @Index("userId") // Add this line to index the userId column
)
public class Diary {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int userId; // ID dell'utente proprietario del diario

    @ColumnInfo(name = "diary_name")
    private String name;

    @ColumnInfo(name = "diary_start_date")
    private String startDate;

    @ColumnInfo(name = "diary_end_date")
    private String endDate;

    @ColumnInfo(name = "diary_photo_path")
    private String photoPath;

    @ColumnInfo(name = "diary_budget")
    private String budget;

    // Dati aggiuntivi dalla classe nell'adapter
    private boolean isSelected;
    private String country; // Aggiunto per il paese
    private Uri coverImageUri; // Per l'URI dell'immagine

    // Costruttore per la classe Diary che include sia i dati del database che i dati UI
    public Diary(String name, String startDate, String endDate, Uri coverImageUri, String country) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.photoPath = photoPath;
        this.budget = budget;
        this.coverImageUri = coverImageUri;
        this.isSelected = false;
        this.country = country;
    }

    // Getter e Setter per i campi del database
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    // Getter e Setter per i dati aggiuntivi per la UI
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Uri getCoverImageUri() {
        return coverImageUri;
    }

    public void setCoverImageUri(Uri coverImageUri) {
        this.coverImageUri = coverImageUri;
    }

    // Metodo per calcolare la durata del viaggio
    public int getTravelDuration() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);

            if (start != null && end != null && start.before(end)) {
                long diffInMillis = end.getTime() - start.getTime();
                long diffInDays = diffInMillis / (1000 * 60 * 60 * 24);
                return (int) diffInDays;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Metodo per ottenere l'abbreviazione del mese
    public String getStartMonthAbbreviation() {
        return getMonthAbbreviation(startDate);
    }

    // Metodo per estrarre l'abbreviazione del mese
    private String getMonthAbbreviation(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            Date date = sdf.parse(dateString);
            if (date != null) {
                SimpleDateFormat monthFormat = new SimpleDateFormat("MMM", Locale.getDefault());
                return monthFormat.format(date).toUpperCase();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
