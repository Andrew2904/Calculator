package entity;

public class Circumstance {
    private boolean fixed, aggro;
    public int minJailn, minJaild;
    public int maxJailn, maxJaild;
    public int minFinen, minFined;
    public int maxFinen, maxFined;
    private String desc;

    public Circumstance(){
        fixed = true;
        aggro = false;

        minJailn=maxJailn=minFinen=maxFinen=0;
        minJaild=maxJaild=minFined=maxFined=1;
    }

    public void setFixed(boolean fixed){
        this.fixed=fixed;

        if(fixed){
            minJaild=maxJaild=minFined=maxFined=1;
        }
    }

    public void setAggro(boolean aggro) { this.aggro = aggro; }

    public void setMinJail(int num, int den){
        minJailn = num;
        minJaild = den;
    }

    public void setMaxJail(int num, int den){
        maxJailn = num;
        maxJaild = den; }

    public void setMinFine(int num, int den){
        minFinen = num;
        minFined = den;
    }

    public void setMaxFine(int num, int den){
        maxFinen = num;
        maxFined = den;
    }

    public float getMinFine() {
        if(fixed)
            return minFinen;
        else
            return (float) minFinen/minFined;
    }

    public float getMaxFine() {
        if(fixed)
            return maxFinen;
        else
            return (float) maxFinen/maxFined;
    }

    public float getMaxJail() {
        if(fixed)
            return maxJailn;
        else
        return (float) maxJailn/maxJaild;
    }

    public float getMinJail() {
        if(fixed)
            return minJailn;
        else
            return (float) minJailn/minJaild;
    }

    public float getMinJailRatio(){
        if(minJaild==0)
            return 1;
        if(aggro)
            return 1+getMinJail();
        else
            return 1-getMinJail();
    }

    public float getMaxJailRatio(){
        if(maxJaild==0)
            return 1;
        if(aggro)
            return 1+getMaxJail();
        else
            return 1-getMaxJail();
    }

    public float getMinFineRatio(){
        if(minFined==0)
            return 1;
        if(aggro)
            return 1+getMinFine();
        else
            return 1-getMinFine();
    }

    public float getMaxFineRatio(){
        if(maxFined==0)
            return 1;
        if(aggro)
            return 1+getMaxFine();
        else
            return 1-getMaxFine();
    }

    public void setDesc(String desc){ this.desc = desc; }

    public String getDesc(){ return desc; }

    public boolean isFixed(){ return fixed; }

    public boolean isAggro() {
        return aggro;
    }

    public int getMaxFined() {
        return maxFined;
    }

    public int getMaxFinen() {
        return maxFinen;
    }

    public int getMaxJaild() {
        return maxJaild;
    }

    public int getMaxJailn() {
        return maxJailn;
    }

    public int getMinFined() {
        return minFined;
    }

    public int getMinFinen() {
        return minFinen;
    }

    public int getMinJaild() {
        return minJaild;
    }

    public int getMinJailn() {
        return minJailn;
    }
}
