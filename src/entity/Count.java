package entity;

import java.util.ArrayList;
import java.util.List;

public class Count {
    Felony base;
    Sentence compound;
    List<Circumstance> circumstances;

    public Count(){
        base = new Felony();
        compound = base.getSentence();
        circumstances = new ArrayList<>();
    }

    public Count(Felony f){
        base = f;
        compound = base.getSentence();
        circumstances = new ArrayList<>();
    }

    public Sentence getBase() {
        return base.getSentence();
    }

    public Sentence getCompound(){
        compound.set( getBase() );

        for(int i=0;i<circumstances.size();i++)
            compound.addCircumstance( circumstances.get(i) );

        return compound;
    }

    public List<Circumstance> getCircumstances() {
        return circumstances;
    }

    public void setFelony(Felony base){
        this.base = base;
    }

    public void addCircumstance(Circumstance circumstance){
        if(!circumstances.contains(circumstance))
            circumstances.add(circumstance);
    }

    public void removeCircumstance(Circumstance circumstance){
        circumstances.remove(circumstance);
    }

    public String getDesc(){
        return base.getDesc();
    }

    public void setDesc(String desc){
        base.setDesc(desc);
    }

    public void setBase(Sentence base){
        this.base.setSentence(base);
    }

    public void setCircumstances(List<Circumstance> circumstances){
        this.circumstances = circumstances;
    }

    public void update(){
        base.update();
    }
}
