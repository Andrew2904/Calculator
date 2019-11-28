package util;

import gui.list.Count;

import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    public static List<Count> read(String file){
        List<Count> data = new ArrayList<Count>();

        Count entry, base;
        String desc;

        base = new Count("", 100, 0);

        for(int i=0;i<30;i++){
            if(i%4==0)
                base.setDesc("Fapta "+i);
            else
            if(i%4==1)
                base.setDesc("Furt "+i);
            else
            if(i%4==2)
                base.setDesc("Santaj "+i);
            else
            if(i%4==3)
                base.setDesc("Mita "+i);

            entry = new Count(base);
            data.add(entry);
        }

        return data;
    }
}
