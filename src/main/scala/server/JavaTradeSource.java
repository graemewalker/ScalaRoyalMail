package server;

import scala.Enumeration;
import scala.Function0;
import scala.actors.Actor$class;
import scala.collection.immutable.List;
import scala.runtime.BoxedUnit;
import server.model.*;

//import org.specs.collection.JavaCollectionsConversion;

import java.util.ArrayList;

public class JavaTradeSource extends Thread{
//
//    public JavaTradeSource(){
//        Enumeration.Value[] underlyings = new Enumeration.Value[]{
//            Underlying.HSBC()
//        };
//
//
//
//        List<Enumeration.Value> scalaUnderlyingsList = JavaCollectionsConversion.javaArrayToList(underlyings);
//
//        TradeInputActor.$bang
//                (new TradeMessage("JavaTradeSource", "J1", new BasketTrade("J11", scalaUnderlyingsList, 77.5)));

 /*       TradeInputActor.$bang
                (new TradeMessage("JavaTradeSource", "J1", new TradeWithKnockout("J11", scalaUnderlyingsList, 77.5)));

*/

//    }

    /*class TradeWithKnockout extends BasketTrade implements HasKnockOut, HasBarrier, BarrierListener{

        private boolean breached;

        public TradeWithKnockout(String id, List<Enumeration.Value> underlyings, double strikePrice) {
            super(id, underlyings, strikePrice);
        }

        @Override
        public void changed(double price){
            HasKnockOut$class.changed(this, price);
        }

        @Override
        public String description(){
            return HasKnockOut$class.description(this);
        }

        @Override
        public boolean breached(){
            return this.breached;
            //return HasBarrier$class.;
        }

        public void breached_$eq(boolean breached){
            this.breached = breached;
        }

        @Override
        public void act(){
            BarrierListener$class.act(this);
        }

        @Override
        public void onTerminate(Function0<BoxedUnit> function){
            Actor$class.onTerminate(this, function);
        }

    }*/

}
