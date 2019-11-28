package gui.list;

import java.util.Date;

public class Count {
    String desc;
    int jail;
    float fine;
    Date update;

    public Count(String desc, int jail, float fine, Date update){
        this.desc = desc;
        this.jail = jail;
        this.fine = fine;
        this.update = update;
    }

    public Count(){
        this("", 0, 0, new Date());
    }

    public Count(String desc, int jail, float fine){
        this( desc, jail, fine, new Date());
    }

    public Count(Count original){
        this( original.desc, original.jail, original.fine, original.update);
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
}
