package data.entity;

import util.ResourceManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Felony {
    private String desc;
    private Date date;
    private Sentence sentence;

    static SimpleDateFormat format = new SimpleDateFormat( ResourceManager.getProperty("date_read") );
    static SimpleDateFormat wformat = new SimpleDateFormat( ResourceManager.getProperty("date_write") );

    public Felony(String desc, int minJail, int maxJail, int minFine, int maxFine, String update){
        this.desc = desc;

        this.sentence = new Sentence(minJail, maxJail, minFine, maxFine);

        try {
            this.date = format.parse(update);
        } catch (ParseException e) {
            e.printStackTrace();
            this.date = new Date();
        }
    }

    public Felony(String desc, int minJail, int maxJail, int minFine, int maxFine, Date update){
        this(desc, minJail, maxJail, minFine,maxFine, format.format(update));
    }

    public Felony(){
        this("", 0, 0, 0, 0, format.format(new Date()));
    }

    public Felony(String desc, int minJail, int maxJail, int minFine, int maxFine){
        this( desc, minJail, maxJail, minFine, maxFine, format.format(new Date()));
    }

    public Felony(Felony original){
        this( original.desc, original.getMinJail(), original.getMaxJail(), original.getMinFine(), original.getMaxFine(), original.date);
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setMinJail(int jail) {
        sentence.setMinJail(jail);
    }

    public void setMaxJail(int jail) {
        sentence.setMaxJail(jail);
    }

    public void setMinFine(int fine) {
        sentence.setMinFine(fine);
    }

    public void setMaxFine(int fine) {
        sentence.setMaxFine(fine);
    }

    public void setDate(String date){
        try {
            this.date = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            this.date = new Date();
        }
    }

    public void setDate(Date date){
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public int getMinJail() {
        return sentence.getMinJail();
    }

    public int getMaxJail() {
        return sentence.getMaxJail();
    }

    public int getMinFine() {
        return sentence.getMinFine();
    }

    public int getMaxFine() {
        return sentence.getMaxFine();
    }

    public String getDate(){
        return wformat.format(date);
    }

    public String toString(){
        return desc;
    }

    public Sentence getSentence(){
        return sentence;
    }

    public boolean contains(String substring){
        return desc.toLowerCase().contains( substring.toLowerCase() );
    }

    public void setSentence(Sentence sentence) {
        this.sentence = sentence;
    }

    public void update(){
        sentence.order();
    }
}
