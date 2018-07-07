package org.launchcode.models;

public class JobField {

    private String value;
    private int id;
    private static int nextId = 1;

    public JobField() {
        id = nextId;
        nextId++;
    }

    public JobField(String aValue) {
        this();
        value = aValue;
    }

    public boolean contains(String value) {
        return this. value.toLowerCase().contains(value.toLowerCase());
    }

    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        JobField jobField = (JobField) o;

        return id == jobField.getId();
    }

    @Override
    public int hashCode() {
        return id;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
