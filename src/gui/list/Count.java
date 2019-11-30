package gui.list;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Count {
    String desc;
    int jail;
    float fine;
    String date;
    static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    public Count(String desc, int jail, float fine, String update){
        this.desc = desc;
        this.jail = jail;
        this.fine = fine;
        this.date = update;
    }

    public Count(String desc, int jail, float fine, Date update){
        this("", 0, 0, format.format(update));
    }

    public Count(){
        this("", 0, 0, format.format(new Date()));
    }

    public Count(String desc, int jail, float fine){
        this( desc, jail, fine, format.format(new Date()));
    }

    public Count(Count original){
        this( original.desc, original.jail, original.fine, original.date);
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setJail(int jail) {
        this.jail = jail;
    }

    public void setFine(float fine) {
        this.fine = fine;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public int getJail() {
        return jail;
    }

    public float getFine() {
        return fine;
    }

    public String toString(){
        return desc;
    }

    public boolean contains(String substring){
        return desc.toLowerCase().contains( substring.toLowerCase() );
    }

    public static void setFormat(String newFormat){
        format = new SimpleDateFormat(newFormat);
    }

    public static SimpleDateFormat getFormat(){
        return format;
    }
}
