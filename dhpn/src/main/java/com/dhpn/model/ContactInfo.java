package com.dhpn.model;

import com.dhpn.enums.ContactType;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by prafulmantale on 3/4/15.
 */
@Field("Contact")
public class ContactInfo {

    @Field(value = "type")
    @JsonProperty("type")
    private ContactType type;

    @Field(value = "number")
    @JsonProperty("number")
    private String number;

    @Field(value = "ext")
    @JsonProperty("ext")
    private String extension;

    public ContactInfo() {
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactInfo that = (ContactInfo) o;

        if (extension != null ? !extension.equals(that.extension) : that.extension != null) return false;
        if (!number.equals(that.number)) return false;
        if (type != that.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "type=" + type +
                ", number='" + number + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}
