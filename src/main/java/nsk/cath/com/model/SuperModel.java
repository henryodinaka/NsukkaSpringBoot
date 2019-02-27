package nsk.cath.com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import nsk.cath.com.utils.JsonDateSerializer;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

//@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class SuperModel implements Serializable {

    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonIgnore
//    @JsonProperty(Constants.CREATED_AT)
    @CreatedDate
    protected Date createdAt;

    @Column(name = "UpdatedAt")
    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSerializer.class)
//    @JsonProperty(Constants.UPDATED_AT)
    @LastModifiedDate
    protected Date updatedAt;


    public static int smallestInt(int[] a, int k) {

        int missing = 0;
        int[] values = Arrays.copyOf(a, k);
        Arrays.sort(values);
        int counter = 0;
        for (int i = k; i < a.length; i++) {
            if (a[i] < values[counter]) { // Found small value
                // Insert sorted
                for (int j = k - 1; j >= 0; j--) {
                    if (j == 0 || a[i] > values[j - 1]) { // Insert pos
                        // Move greater ones up.
                        for (int m = k - 1; m > j; m--) {
                            values[m] = values[m - 1];
                        }
                        values[j] = a[i]; // Store
                        break; // Done
                    }
                }
            }
        }
        return missing;
    }

    public static int returnSmall(int[] a, int k) {
        int small = 0;
        HashSet<Integer> hashSet = new HashSet<Integer>();
        int smallInt = 1;

        for (int i = 0; i < a.length; i++) {
            hashSet.add(a[i]);
        }

        while (hashSet.contains(smallInt)) {
            smallInt++;
        }

        return smallInt;
    }

    public static void main(String[] args) {

        System.out.println(returnSmall(new int[]{10,3, 60, 4, 1,9, 2,3,6}, 8));
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}