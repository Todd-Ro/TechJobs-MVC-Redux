package org.launchcode.models.forms;

import org.launchcode.models.JobField;
import org.launchcode.models.JobFieldType;

public class SearchForm {

    // The search options
    private JobFieldType[] fields = JobFieldType.values();

    // The selected search options
    private JobFieldType searchField = JobFieldType.ALL;

    // The search string
    private String keyword;

    public JobFieldType[] getFields() {
        return fields;
    }

    public void setFields(JobFieldType[] fields) {
        this.fields = fields;
    }

    public JobFieldType getSearchField() {
        return searchField;
    }

    public void setSearchField(JobFieldType searchField) {
        this.searchField = searchField;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
