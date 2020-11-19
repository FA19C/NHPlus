package model;

import utils.DateConverter;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Model class for the Treatments
 */
public class Treatment {
    private long tid;
    private long pid;
    private LocalDate date;
    private LocalTime begin;
    private LocalTime end;
    private String description;
    private String remarks;

    /**
     * Default constructor
     * @param pid
     * @param date
     * @param begin
     * @param end
     * @param description
     * @param remarks
     */
    public Treatment(long pid, LocalDate date, LocalTime begin,
                     LocalTime end, String description, String remarks) {
        this.pid = pid;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
    }

    /**
     * Default constructor
     * @param pid
     * @param date
     * @param begin
     * @param end
     * @param description
     * @param remarks
     */
    public Treatment(long tid, long pid, LocalDate date, LocalTime begin,
                     LocalTime end, String description, String remarks) {
        this.tid = tid;
        this.pid = pid;
        this.date = date;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
    }

    /**
     * Getter for the Treatment ID
     * @return
     */
    public long getTid() {
        return tid;
    }

    /**
     * Getter for the Patient ID
     * @return
     */
    public long getPid() {
        return this.pid;
    }

    /**
     * Getter for the Date
     * @return
     */
    public String getDate() {
        return date.toString();
    }

    /**
     * Getter for the Treatment begin
     * @return
     */
    public String getBegin() {
        return begin.toString();
    }

    /**
     * Getter for hte the Treatment end
     * @return
     */
    public String getEnd() {
        return end.toString();
    }

    /**
     * setter for the Treatment Date
     * @param s_date
     */
    public void setDate(String s_date) {
        LocalDate date = DateConverter.convertStringToLocalDate(s_date);
        this.date = date;
    }

    /**
     * setter for the Treatment beginning
     * @param begin
     */
    public void setBegin(String begin) {
        LocalTime time = DateConverter.convertStringToLocalTime(begin);
        this.begin = time;
    }

    /**
     * setter for the Treatment end
     */
    public void setEnd(String end) {
        LocalTime time = DateConverter.convertStringToLocalTime(end);
        this.end = time;
    }

    /**
     * Getter for the Treatment Description
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for hte the Treatment Description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for the Treatment Remarks
     * @return
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * Setter for the Treatment Remarks
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String toString() {
        return "\nBehandlung" + "\nTID: " + this.tid +
                "\nPID: " + this.pid +
                "\nDate: " + this.date +
                "\nBegin: " + this.begin +
                "\nEnd: " + this.end +
                "\nDescription: " + this.description +
                "\nRemarks: " + this.remarks + "\n";
    }
}