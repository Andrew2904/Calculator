package entity;

public class Sentence {
    private int minJail, maxJail;
    private int minFine, maxFine;

    public Sentence(int minJail, int maxJail, int minFine, int maxFine){
        this.minJail = minJail;
        this.maxJail = maxJail;
        this.minFine = minFine;
        this.maxFine = maxFine;
    }

    public Sentence(){
        this(-1, -1, -1, -1);
    }

    public Sentence(Sentence original){
        this( original.minJail, original.maxJail, original.minFine, original.maxFine);
    }

    public void setMinJail(int jail) {
        if(this.minJail>=0 && jail>this.maxJail){
            this.minJail = this.maxJail;
            this.maxJail = jail;
        }
        else
            this.minJail = jail;
    }

    public void setMaxJail(int jail) {
        if(jail<this.minJail){
            this.maxJail = this.minJail;
            this.minJail = jail;
        }
        else
            this.maxJail = jail;
    }

    public void setMinFine(int fine) {
        if(this.minFine>=0 && fine>this.maxFine){
            this.minFine = this.maxFine;
            this.minFine = fine;
        }
        else
            this.minFine = fine;
    }

    public void setMaxFine(int fine) {
        if(fine<this.minFine){
            this.maxFine = this.minFine;
            this.minFine = fine;
        }
        else
            this.maxFine = fine;
    }

    public int getMaxFine() {
        return maxFine;
    }

    public int getMinFine() {
        return minFine;
    }

    public int getMaxJail() {
        return maxJail;
    }

    public int getMinJail() {
        return minJail;
    }

    public void add(Sentence s){
        minFine += s.getMinFine();
        maxFine += s.getMaxFine();

        minJail += s.getMinJail();
        maxJail += s.getMaxJail();
    }


}
