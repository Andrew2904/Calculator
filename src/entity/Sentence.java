package entity;

public class Sentence {
    private int minJail, maxJail;
    private int minFine, maxFine;

    private static int year_day=360, month_day=30;

    public Sentence(int minJail, int maxJail, int minFine, int maxFine){
        this.minJail = minJail;
        this.maxJail = maxJail;
        this.minFine = minFine;
        this.maxFine = maxFine;
    }

    public Sentence(){
        this(0, 0, 0, 0);
    }

    public Sentence(Sentence original){
        this( original.minJail, original.maxJail, original.minFine, original.maxFine);
    }

    public void setMinJail(int jail) {
        this.minJail = jail;
    }

    public void setMaxJail(int jail) {
        this.maxJail = jail;
    }

    public void setMinFine(int fine) {
        this.minFine = fine;
    }

    public void setMaxFine(int fine) {
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

        order();
    }

    public void add(Circumstance s){
        if(s.isAggro()) {
            minFine += s.getMinFine();
            maxFine += s.getMaxFine();

            minJail += s.getMinJail();
            maxJail += s.getMaxJail();
        }
        else{
            minFine -= s.getMinFine();
            maxFine -= s.getMaxFine();

            minJail -= s.getMinJail();
            maxJail -= s.getMaxJail();
        }

        order();
    }

    public void sub(Sentence s){
        minFine -= s.getMinFine();
        maxFine -= s.getMaxFine();

        minJail -= s.getMinJail();
        maxJail -= s.getMaxJail();

        order();
    }

    public void sub(Circumstance s){
        minFine -= s.getMinFine();
        maxFine -= s.getMaxFine();

        minJail -= s.getMinJail();
        maxJail -= s.getMaxJail();

        order();
    }

    public void addCircumstance(Circumstance circumstance){
        if(circumstance.isFixed())
            add(circumstance);
        else
            mul(circumstance);
    }

    public void mul(Circumstance s){
         minFine = (int) (minFine * s.getMinFineRatio());
         maxFine = (int) (maxFine * s.getMaxFineRatio());

         minJail = (int) (minJail * s.getMinJailRatio());
         maxJail = (int) (maxJail * s.getMaxJailRatio());

        order();
    }

    public void mul(float change){
        minFine = (int) (minFine * change);
        maxFine = (int) (maxFine * change);

        minJail = (int) (minJail * change);
        maxJail = (int) (maxJail * change);

        order();
    }

    public void set(Sentence s){
        minJail = s.minJail;
        maxJail = s.maxJail;
        minFine = s.minFine;
        maxFine = s.maxFine;

        order();
    }

    public void order(){
        int jail, fine;

        if(minJail>maxJail){
            jail = minJail;
            minJail = maxJail;
            maxJail = jail;
        }
        if(minFine>maxFine){
            fine = minFine;
            minFine = maxFine;
            maxFine = fine;
        }
    }

    public boolean isGreater(Sentence s){
        return maxJail>s.maxJail;
    }

    public static int getYear(int days){
        return days/year_day+days%year_day/month_day/12;
    }

    public static int getMonth(int days){
        return days%year_day/month_day%12;
    }

    public static int getDay(int days){
        return days%year_day%month_day;
    }

    public static int getDays(int year, int month, int day){
        return year*year_day+month*month_day+day;
    }
}
