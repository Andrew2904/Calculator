package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Felony {
    private String desc;
    private String date;
    private Sentence sentence;

    static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    public Felony(String desc, int minJail, int maxJail, int minFine, int maxFine, String update){
        this.desc = desc;

        this.sentence = new Sentence(minJail, maxJail, minFine, maxFine);

        this.date = update;
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
        this.date = date;
    }

    public void setDate(Date date){
        this.date = format.format(date);
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
        return date;
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

    public static void setFormat(String newFormat){
        format = new SimpleDateFormat(newFormat);
    }

    public static String format(Date date){
        return format.format(date);
    }

    public void setSentence(Sentence sentence) {
        this.sentence = sentence;
    }

    public void update(){
        sentence.order();
    }
}
