package server;

import scala.Enumeration;
import scala.collection.immutable.List;
import server.model.Basket;
import server.model.Underlying;

import org.specs.collection.JavaCollectionsConversion;

import java.util.ArrayList;

public class JavaTradeSource extends Thread{

    public JavaTradeSource(){
        Enumeration.Value[] underlyings = new Enumeration.Value[]{
            Underlying.HSBC()
        };

        List<Enumeration.Value> scalaUnderlyingsList = JavaCollectionsConversion.javaArrayToList(underlyings);

        TradeInputActor.$bang
                (new TradeMessage("JavaTradeSource", "J1", new Basket("J11", scalaUnderlyingsList, 77.5)));
    }


}
