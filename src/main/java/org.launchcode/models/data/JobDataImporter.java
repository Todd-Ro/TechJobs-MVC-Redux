package org.launchcode.models.data;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.launchcode.models.*;

import java.io.IOException;
import java.util.List;

import static org.launchcode.models.JobData.getHeaders;
import static org.launchcode.models.JobData.getRecords;
import static org.launchcode.models.JobData.loadingParser;

public class JobDataImporter {

    private static final String DATA_FILE = "job_data.csv";
    private static boolean isDataLoaded = false; //Separate boolean from one in JobData?

    static void loadData(OOJobData jobData) {

        //only load data once
        if (isDataLoaded) {
            return;
        }

        try {

            // Open the CSV file and set up pull out column header info and records
            CSVParser parser = loadingParser();
            List<CSVRecord> records = getRecords(parser);
            String[] headers = getHeaders(parser, records);

            // Put the records into a more friendly format
            for (CSVRecord record : records) {


                String empStr = record.get("employer");
                String locStr = record.get("location");
                String coreCompStr = record.get("core competency");
                String posTypeStr = record.get("position type");

                Employer emp = jobData.getEmployers().findByValue(empStr);
                if (emp == null) {
                    emp = new Employer(empStr);
                    jobData.getEmployers().add(emp);
                }

                Location loc = jobData.getLocations().findByValue(locStr);
                if (loc == null) {
                    loc = new Location(locStr);
                    jobData.getLocations().add(loc);
                }

                PositionType posType = jobData.getPositionTypes().findByValue(posTypeStr);
                if (posType == null) {
                    posType = new PositionType(posTypeStr);
                    jobData.getPositionTypes().add(posType);
                }

                CoreCompetency coreComp = jobData.getCoreCompetencies().findByValue(coreCompStr);
                if (coreComp == null) {
                    coreComp = new CoreCompetency(coreCompStr);
                    jobData.getCoreCompetencies().add(coreComp);
                }

                Job newJob = new Job(record.get("name"), emp, loc, posType, coreComp);

                jobData.add(newJob);
            }

            isDataLoaded = true;

        } catch (Exception e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }

    }

}
